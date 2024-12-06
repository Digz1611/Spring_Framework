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

    private Long id;
    private String name;
    private Long category;
    private String releaseDate;
    private String description;
}
