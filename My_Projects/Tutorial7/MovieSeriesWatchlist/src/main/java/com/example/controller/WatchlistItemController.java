package com.example.controller;

import com.example.model.WatchlistItem;
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
     * @param watchlistItem The watchlist item to add.
     * @param userId        The user ID to associate with the item.
     * @return The created watchlist item.
     */
    @PostMapping("/add")
    public ResponseEntity<WatchlistItem> addWatchlistItem(
            @RequestBody @Valid WatchlistItem watchlistItem,
            @RequestParam Long userId) {
        WatchlistItem createdWatchlistItem = watchlistItemService.addWatchlistItem(userId, watchlistItem);
        return ResponseEntity.ok(createdWatchlistItem);
    }

    /**
     * Get all watchlist items for a specific user.
     *
     * @param userId The user ID.
     * @return A list of watchlist items.
     */
    @GetMapping("/")
    public ResponseEntity<List<WatchlistItem>> getWatchlistItems(@RequestParam Long userId) {
        List<WatchlistItem> watchlistItems = watchlistItemService.getWatchlistItems(userId);
        return ResponseEntity.ok(watchlistItems);
    }

    /**
     * Get all watchlist items for a specific user filtered by category.
     *
     * @param userId   The user ID.
     * @param category The category to filter by.
     * @return A list of filtered watchlist items.
     */
    @GetMapping("/category")
    public ResponseEntity<List<WatchlistItem>> getWatchlistItemsByCategory(
            @RequestParam Long userId,
            @RequestParam String category) {
        List<WatchlistItem> watchlistItems = watchlistItemService.getWatchlistItemsByCategory(userId, category);
        return ResponseEntity.ok(watchlistItems);
    }

    /**
     * Update an existing watchlist item.
     *
     * @param id                  The ID of the watchlist item to update.
     * @param userId              The user ID.
     * @param updatedWatchlistItem The updated watchlist item.
     * @return The updated watchlist item.
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<WatchlistItem> updateWatchlistItem(
            @PathVariable Long id,
            @RequestParam Long userId,
            @RequestBody @Valid WatchlistItem updatedWatchlistItem) {
        WatchlistItem updatedItem = watchlistItemService.updateWatchlistItem(userId, id, updatedWatchlistItem);
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

    /**
     * Delete all watchlist items for a specific user.
     *
     * @param userId The user ID.
     * @return A success message.
     */
    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAllWatchlistItems(@RequestParam Long userId) {
        watchlistItemService.deleteAllWatchlistItems(userId);
        return ResponseEntity.ok("All watchlist items deleted successfully!");
    }
}
