package com.example.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WatchlistItemDTO {

    private Long id;                // ID of the watchlist item
    private Long categoryId;        // Category ID for association
    private String name;            // Name of the watchlist item
    private String description;     // Description of the watchlist item
}
