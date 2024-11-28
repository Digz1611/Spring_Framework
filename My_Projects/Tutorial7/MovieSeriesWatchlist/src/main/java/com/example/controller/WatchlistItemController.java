package com.example.controller;

import com.example.model.WatchlistItem;
import com.example.service.WatchlistItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/watchlist")
@RequiredArgsConstructor
public class WatchlistItemController {

    private final WatchlistItemService watchlistItemService;

    // Add a new watchlist item
    @PostMapping("/add")
    public ResponseEntity<WatchlistItem> addWatchlistItem(@RequestBody WatchlistItem watchlistItem,
                                                          @RequestParam Long userId) {
        WatchlistItem createdWatchlistItem = watchlistItemService.addWatchlistItem(userId, watchlistItem);
        return ResponseEntity.ok(createdWatchlistItem);
    }

    // Get all watchlist items for a user
    @GetMapping("/")
    public ResponseEntity<List<WatchlistItem>> getWatchlistItems(@RequestParam Long userId) {
        List<WatchlistItem> watchlistItems = watchlistItemService.getWatchlistItems(userId);
        return ResponseEntity.ok(watchlistItems);
    }

    // Get all watchlist items for a user filtered by category
    @GetMapping("/category")
    public ResponseEntity<List<WatchlistItem>> getWatchlistItemsByCategory(@RequestParam Long userId,
                                                                           @RequestParam String category) {
        List<WatchlistItem> watchlistItems = watchlistItemService.getWatchlistItemsByCategory(userId, category);
        return ResponseEntity.ok(watchlistItems);
    }

    // Update a watchlist item
    @PutMapping("/update/{id}")
    public ResponseEntity<WatchlistItem> updateWatchlistItem(@PathVariable Long id,
                                                             @RequestParam Long userId,
                                                             @RequestBody WatchlistItem updatedWatchlistItem) {
        WatchlistItem updatedItem = watchlistItemService.updateWatchlistItem(userId, id, updatedWatchlistItem);
        return ResponseEntity.ok(updatedItem);
    }

    // Delete a specific watchlist item
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteWatchlistItem(@PathVariable Long id, @RequestParam Long userId) {
        watchlistItemService.deleteWatchlistItem(userId, id);
        return ResponseEntity.ok("Watchlist item deleted successfully!");
    }

    // Delete all watchlist items for a user
    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAllWatchlistItems(@RequestParam Long userId) {
        watchlistItemService.deleteAllWatchlistItems(userId);
        return ResponseEntity.ok("All watchlist items deleted successfully!");
    }
}
