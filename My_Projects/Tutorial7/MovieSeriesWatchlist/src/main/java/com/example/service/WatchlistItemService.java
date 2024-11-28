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
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        watchlistItem.setUser(user);
        return watchlistItemRepository.save(watchlistItem);
    }

    // Get all watchlist items for a user
    public List<WatchlistItem> getWatchlistItems(Long userId) {
        return watchlistItemRepository.findByUserId(userId);
    }

    // Get watchlist items by category for a user
    public List<WatchlistItem> getWatchlistItemsByCategory(Long userId, String category) {
        return watchlistItemRepository.findByUserIdAndCategory(userId, category);
    }

    // Update a watchlist item
    public WatchlistItem updateWatchlistItem(Long userId, Long id, WatchlistItem updatedWatchlistItem) {
        Optional<WatchlistItem> existingItem = watchlistItemRepository.findByIdAndUserId(id, userId);
        if (existingItem.isPresent()) {
            WatchlistItem item = existingItem.get();
            item.setName(updatedWatchlistItem.getName());
            item.setCategory(updatedWatchlistItem.getCategory());
            item.setReleaseDate(updatedWatchlistItem.getReleaseDate());
            item.setDescription(updatedWatchlistItem.getDescription());
            return watchlistItemRepository.save(item);
        }
        return null;
    }

    // Delete a specific watchlist item
    public void deleteWatchlistItem(Long userId, Long id) {
        watchlistItemRepository.deleteByIdAndUserId(id, userId);
    }

    // Delete all watchlist items for a user
    public void deleteAllWatchlistItems(Long userId) {
        watchlistItemRepository.deleteAllByUserId(userId);
    }
}
