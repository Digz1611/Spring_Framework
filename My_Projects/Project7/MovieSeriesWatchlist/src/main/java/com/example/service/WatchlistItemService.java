package com.example.service;

import com.example.dto.WatchlistItemDTO;
import com.example.model.User;
import com.example.model.WatchlistItem;
import com.example.repository.UserRepository;
import com.example.repository.WatchlistItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WatchlistItemService {

    private final WatchlistItemRepository watchlistItemRepository;
    private final UserRepository userRepository;

    // Add a new watchlist item
    public WatchlistItemDTO addWatchlistItem(Long userId, WatchlistItemDTO watchlistItemDTO) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            WatchlistItem watchlistItem = new WatchlistItem();
            watchlistItem.setName(watchlistItemDTO.getName());
            watchlistItem.setCategory(watchlistItemDTO.getCategory());
            watchlistItem.setReleaseDate(watchlistItemDTO.getReleaseDate());
            watchlistItem.setDescription(watchlistItemDTO.getDescription());
            watchlistItem.setUser(user.get());

            WatchlistItem savedItem = watchlistItemRepository.save(watchlistItem);
            return new WatchlistItemDTO(
                    savedItem.getId(),
                    savedItem.getName(),
                    savedItem.getCategory(),
                    savedItem.getReleaseDate(),
                    savedItem.getDescription()
            );
        } else {
            throw new RuntimeException("User not found");
        }
    }

    // Get all watchlist items for a user
    public List<WatchlistItemDTO> getWatchlistItems(Long userId) {
        List<WatchlistItem> items = watchlistItemRepository.findByUserId(userId);
        return items.stream().map(item -> new WatchlistItemDTO(
                item.getId(),
                item.getName(),
                item.getCategory(),
                item.getReleaseDate(),
                item.getDescription()
        )).collect(Collectors.toList());
    }

    // Update a watchlist item
    public WatchlistItemDTO updateWatchlistItem(Long userId, Long itemId, WatchlistItemDTO updatedWatchlistItemDTO) {
        Optional<WatchlistItem> existingItem = watchlistItemRepository.findById(itemId);
        if (existingItem.isPresent() && existingItem.get().getUser().getId().equals(userId)) {
            WatchlistItem updatedItem = existingItem.get();
            updatedItem.setName(updatedWatchlistItemDTO.getName());
            updatedItem.setCategory(updatedWatchlistItemDTO.getCategory());
            updatedItem.setReleaseDate(updatedWatchlistItemDTO.getReleaseDate());
            updatedItem.setDescription(updatedWatchlistItemDTO.getDescription());

            WatchlistItem savedItem = watchlistItemRepository.save(updatedItem);
            return new WatchlistItemDTO(
                    savedItem.getId(),
                    savedItem.getName(),
                    savedItem.getCategory(),
                    savedItem.getReleaseDate(),
                    savedItem.getDescription()
            );
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
