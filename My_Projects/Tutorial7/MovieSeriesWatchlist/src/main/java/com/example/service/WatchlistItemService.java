package com.example.service;

import com.example.model.User;
import com.example.model.WatchlistItem;
import com.example.repository.UserRepository;
import com.example.repository.WatchlistItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WatchlistItemService {

    private final WatchlistItemRepository watchlistItemRepository;
    private final UserRepository userRepository;

    // Add a new watchlist item
    public WatchlistItem addWatchlistItem(Long userId, WatchlistItem watchlistItem) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            watchlistItem.setUser(user.get());
            return watchlistItemRepository.save(watchlistItem);
        } else {
            throw new RuntimeException("User not found");
        }
    }

    // Get all watchlist items for a user
    public List<WatchlistItem> getWatchlistItems(Long userId) {
        return watchlistItemRepository.findByUserId(userId);
    }

    // Get all watchlist items for a user filtered by category
    public List<WatchlistItem> getWatchlistItemsByCategory(Long userId, String category) {
        return watchlistItemRepository.findByUserIdAndCategory(userId, category);
    }

    // Update a watchlist item
    public WatchlistItem updateWatchlistItem(Long userId, Long itemId, WatchlistItem updatedWatchlistItem) {
        Optional<WatchlistItem> existingItem = watchlistItemRepository.findById(itemId);
        if (existingItem.isPresent() && existingItem.get().getUser().getId().equals(userId)) {
            updatedWatchlistItem.setId(itemId);
            return watchlistItemRepository.save(updatedWatchlistItem);
        }
        throw new RuntimeException("Watchlist item not found or user mismatch");
    }

    // Delete a specific watchlist item
    public void deleteWatchlistItem(Long userId, Long itemId) {
        Optional<WatchlistItem> existingItem = watchlistItemRepository.findById(itemId);
        if (existingItem.isPresent() && existingItem.get().getUser().getId().equals(userId)) {
            watchlistItemRepository.delete(existingItem.get());
        } else {
            throw new RuntimeException("Watchlist item not found or user mismatch");
        }
    }

}
