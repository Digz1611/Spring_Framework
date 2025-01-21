package com.example.controller;

import com.example.dto.WatchlistItemDTO;
import com.example.service.WatchlistItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@RestController
@RequestMapping("/api/watchlist")
@RequiredArgsConstructor
public class WatchlistItemController {

    private final WatchlistItemService watchlistItemService;

    @PostMapping("/add")
    public ResponseEntity<WatchlistItemDTO> addWatchlistItem(
            @RequestBody @Valid WatchlistItemDTO watchlistItemDTO,
            @AuthenticationPrincipal User user) {
        WatchlistItemDTO createdWatchlistItem = watchlistItemService.addWatchlistItem(user.getUsername(), watchlistItemDTO);
        return ResponseEntity.ok(createdWatchlistItem);
    }

    @GetMapping("/")
    public ResponseEntity<List<WatchlistItemDTO>> getWatchlistItems(@AuthenticationPrincipal User user) {
        List<WatchlistItemDTO> watchlistItems = watchlistItemService.getWatchlistItems(user.getUsername());
        return ResponseEntity.ok(watchlistItems);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<WatchlistItemDTO> updateWatchlistItem(
            @PathVariable Long id,
            @RequestBody @Valid WatchlistItemDTO updatedWatchlistItemDTO,
            @AuthenticationPrincipal User user) {
        WatchlistItemDTO updatedItem = watchlistItemService.updateWatchlistItem(user.getUsername(), id, updatedWatchlistItemDTO);
        return ResponseEntity.ok(updatedItem);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteWatchlistItem(
            @PathVariable Long id,
            @AuthenticationPrincipal User user) {
        watchlistItemService.deleteWatchlistItem(user.getUsername(), id);
        return ResponseEntity.noContent().build();
    }
}
