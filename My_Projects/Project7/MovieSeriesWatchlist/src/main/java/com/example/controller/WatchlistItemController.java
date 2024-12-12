package com.example.controller;

import com.example.dto.WatchlistItemDTO;
import com.example.service.WatchlistItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/watchlist")
@RequiredArgsConstructor
public class WatchlistItemController {

    private final WatchlistItemService watchlistItemService;

    /**
     * Add a new watchlist item for a user.
     *
     * @param watchlistItemDTO The watchlist item to add.
     * @param userId           The user ID to associate with the item.
     * @return The created watchlist item.
     */
    @PostMapping("/add")
    public ResponseEntity<WatchlistItemDTO> addWatchlistItem(
            @RequestBody @Valid WatchlistItemDTO watchlistItemDTO,
            @RequestParam Long userId) {
        WatchlistItemDTO createdWatchlistItem = watchlistItemService.addWatchlistItem(userId, watchlistItemDTO);
        return ResponseEntity.ok(createdWatchlistItem);
    }

    /**
     * Get all watchlist items for a specific user.
     *
     * @param userId The user ID.
     * @return A list of watchlist items.
     */
    @GetMapping("/")
    public ResponseEntity<List<WatchlistItemDTO>> getWatchlistItems(@RequestParam Long userId) {
        List<WatchlistItemDTO> watchlistItems = watchlistItemService.getWatchlistItems(userId);
        return ResponseEntity.ok(watchlistItems);
    }

    /**
     * Update an existing watchlist item.
     *
     * @param id                  The ID of the watchlist item to update.
     * @param userId              The user ID.
     * @param updatedWatchlistItemDTO The updated watchlist item.
     * @return The updated watchlist item.
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<WatchlistItemDTO> updateWatchlistItem(
            @PathVariable Long id,
            @RequestParam Long userId,
            @RequestBody @Valid WatchlistItemDTO updatedWatchlistItemDTO) {
        WatchlistItemDTO updatedItem = watchlistItemService.updateWatchlistItem(userId, id, updatedWatchlistItemDTO);
        return ResponseEntity.ok(updatedItem);
    }

    /**
     * Delete a specific watchlist item.
     *
     * @param id     The ID of the watchlist item to delete.
     * @param userId The user ID.
     * @return A success message.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteWatchlistItem(
            @PathVariable Long id,
            @RequestParam Long userId) {
        watchlistItemService.deleteWatchlistItem(userId, id);
        return ResponseEntity.ok("Watchlist item deleted successfully!");
    }
}
