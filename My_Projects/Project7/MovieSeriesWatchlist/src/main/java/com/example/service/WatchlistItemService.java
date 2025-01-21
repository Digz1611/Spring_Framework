package com.example.service;

import com.example.dto.WatchlistItemDTO;
import com.example.model.User;
import com.example.model.WatchlistItem;
import com.example.repository.UserRepository;
import com.example.repository.WatchlistItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public WatchlistItemDTO addWatchlistItem(String username, WatchlistItemDTO watchlistItemDTO) {
        Optional<User> user = userRepository.findByEmail(username);
        if (user.isPresent()) {
            WatchlistItem watchlistItem = new WatchlistItem();
            watchlistItem.setName(watchlistItemDTO.getName());
            watchlistItem.setCategory(watchlistItemDTO.getCategory());
            watchlistItem.setReleaseYear(watchlistItemDTO.getReleaseYear());
            watchlistItem.setDescription(watchlistItemDTO.getDescription());
            watchlistItem.setUser(user.get());
            watchlistItemRepository.save(watchlistItem);

            return new WatchlistItemDTO(
//                    savedItem.getId(),
                    watchlistItemDTO.getName(),
                    watchlistItemDTO.getCategory(),
                    watchlistItemDTO.getReleaseYear(),
                    watchlistItemDTO.getDescription()
            );
        } else {
            throw new RuntimeException("User not found");
        }
    }

    // Get all watchlist items for a user
    public List<WatchlistItemDTO> getWatchlistItems(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        List<WatchlistItem> items = watchlistItemRepository.findByUserId(user.getId());
        return items.stream().map(item -> new WatchlistItemDTO(
//                item.getId(),
                item.getName(),
                item.getCategory(),
                item.getReleaseYear(),
                item.getDescription()
        )).collect(Collectors.toList());
    }

    // Update a watchlist item
    public WatchlistItemDTO updateWatchlistItem(String username, Long itemId, WatchlistItemDTO updatedWatchlistItemDTO) {
        Optional<WatchlistItem> existingItem = watchlistItemRepository.findById(itemId);
        if (existingItem.isPresent() && existingItem.get().getUser().getUsername().equals(username)) {
            WatchlistItem updatedItem = existingItem.get();
            updatedItem.setName(updatedWatchlistItemDTO.getName());
            updatedItem.setCategory(updatedWatchlistItemDTO.getCategory());
            updatedItem.setReleaseYear(updatedWatchlistItemDTO.getReleaseYear());
            updatedItem.setDescription(updatedWatchlistItemDTO.getDescription());

            WatchlistItem savedItem = watchlistItemRepository.save(updatedItem);
            return new WatchlistItemDTO(
//                    savedItem.getId(),
                    savedItem.getName(),
                    savedItem.getCategory(),
                    savedItem.getReleaseYear(),
                    savedItem.getDescription()
            );
        }
        throw new RuntimeException("Watchlist item not found or user mismatch");
    }

    // Delete a specific watchlist item
    public void deleteWatchlistItem(String username, Long itemId) {
        Optional<WatchlistItem> existingItem = watchlistItemRepository.findById(itemId);
        if (existingItem.isPresent() && existingItem.get().getUser().getUsername().equals(username)) {
            watchlistItemRepository.delete(existingItem.get());
        } else {
            throw new RuntimeException("Watchlist item not found or user mismatch");
        }
    }
}
