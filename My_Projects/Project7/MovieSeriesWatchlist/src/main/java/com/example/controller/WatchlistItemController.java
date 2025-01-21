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





//package com.example.controller;
//
//import com.example.dto.WatchlistItemDTO;
//import com.example.service.WatchlistItemService;
//import com.example.jwt.JwtUtil;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import jakarta.validation.Valid;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.core.userdetails.User;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/watchlist")
//@RequiredArgsConstructor
//public class WatchlistItemController {
//
//    private final WatchlistItemService watchlistItemService;
//    private final JwtUtil jwtUtil;
//
//    /**
//     * Add a new watchlist item for the authenticated user.
//     *
//     * @param watchlistItemDTO The watchlist item to add.
//     * @param user The authenticated user from the JWT token.
//     * @return The created watchlist item.
//     */
//    @PostMapping("/add")
//    public ResponseEntity<WatchlistItemDTO> addWatchlistItem(
//            @RequestBody @Valid WatchlistItemDTO watchlistItemDTO,
//            @AuthenticationPrincipal User user) {
//        WatchlistItemDTO createdWatchlistItem = watchlistItemService.addWatchlistItem(user.getUsername(), watchlistItemDTO);
//        return ResponseEntity.ok(createdWatchlistItem);
//    }
//
//    /**
//     * Get all watchlist items for the authenticated user.
//     *
//     * @param user The authenticated user from the JWT token.
//     * @return A list of watchlist items.
//     */
//    @GetMapping("/")
//    public ResponseEntity<List<WatchlistItemDTO>> getWatchlistItems(@AuthenticationPrincipal User user) {
//        List<WatchlistItemDTO> watchlistItems = watchlistItemService.getWatchlistItems(user.getUsername());
//        return ResponseEntity.ok(watchlistItems);
//    }
//
//    /**
//     * Update an existing watchlist item for the authenticated user.
//     *
//     * @param id The ID of the watchlist item to update.
//     * @param updatedWatchlistItemDTO The updated watchlist item.
//     * @param user The authenticated user from the JWT token.
//     * @return The updated watchlist item.
//     */
//    @PutMapping("/update/{id}")
//    public ResponseEntity<WatchlistItemDTO> updateWatchlistItem(
//            @PathVariable Long id,
//            @RequestBody @Valid WatchlistItemDTO updatedWatchlistItemDTO,
//            @AuthenticationPrincipal User user) {
//        WatchlistItemDTO updatedItem = watchlistItemService.updateWatchlistItem(user.getUsername(), id, updatedWatchlistItemDTO);
//        return ResponseEntity.ok(updatedItem);
//    }
//
//    /**
//     * Delete a specific watchlist item for the authenticated user.
//     *
//     * @param id The ID of the watchlist item to delete.
//     * @param user The authenticated user from the JWT token.
//     * @return A success message.
//     */
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<String> deleteWatchlistItem(
//            @PathVariable Long id,
//            @AuthenticationPrincipal User user) {
//        watchlistItemService.deleteWatchlistItem(user.getUsername(), id);
//        return ResponseEntity.ok("Watchlist item deleted successfully!");
//    }
//}







//package com.example.controller;
//
//import com.example.dto.WatchlistItemDTO;
//import com.example.service.WatchlistItemService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import jakarta.validation.Valid;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/watchlist")
//@RequiredArgsConstructor
//public class WatchlistItemController {
//
//    private final WatchlistItemService watchlistItemService;
//
//    /**
//     * Add a new watchlist item for a user.
//     *
//     * @param watchlistItemDTO The watchlist item to add.
//     * @param userId           The user ID to associate with the item.
//     * @return The created watchlist item.
//     */
//    @PostMapping("/add")
//    public ResponseEntity<WatchlistItemDTO> addWatchlistItem(
//            @RequestBody @Valid WatchlistItemDTO watchlistItemDTO,
//            @RequestParam Long userId) {
//        WatchlistItemDTO createdWatchlistItem = watchlistItemService.addWatchlistItem(userId, watchlistItemDTO);
//        return ResponseEntity.ok(createdWatchlistItem);
//    }
//
//    /**
//     * Get all watchlist items for a specific user.
//     *
//     * @param userId The user ID.
//     * @return A list of watchlist items.
//     */
//    @GetMapping("/")
//    public ResponseEntity<List<WatchlistItemDTO>> getWatchlistItems(@RequestParam Long userId) {
//        List<WatchlistItemDTO> watchlistItems = watchlistItemService.getWatchlistItems(userId);
//        return ResponseEntity.ok(watchlistItems);
//    }
//
//    /**
//     * Update an existing watchlist item.
//     *
//     * @param id                  The ID of the watchlist item to update.
//     * @param userId              The user ID.
//     * @param updatedWatchlistItemDTO The updated watchlist item.
//     * @return The updated watchlist item.
//     */
//    @PutMapping("/update/{id}")
//    public ResponseEntity<WatchlistItemDTO> updateWatchlistItem(
//            @PathVariable Long id,
//            @RequestParam Long userId,
//            @RequestBody @Valid WatchlistItemDTO updatedWatchlistItemDTO) {
//        WatchlistItemDTO updatedItem = watchlistItemService.updateWatchlistItem(userId, id, updatedWatchlistItemDTO);
//        return ResponseEntity.ok(updatedItem);
//    }
//
//    /**
//     * Delete a specific watchlist item.
//     *
//     * @param id     The ID of the watchlist item to delete.
//     * @param userId The user ID.
//     * @return A success message.
//     */
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<String> deleteWatchlistItem(
//            @PathVariable Long id,
//            @RequestParam Long userId) {
//        watchlistItemService.deleteWatchlistItem(userId, id);
//        return ResponseEntity.ok("Watchlist item deleted successfully!");
//    }
//}
