package com.example.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.Builder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class WatchlistItemDTO {

    private Long id;  // The ID is included here

    @NotBlank(message = "Name cannot be blank.")
    @Size(max = 100, message = "Name must not exceed 100 characters.")
    private String name;

    @NotBlank(message = "Category cannot be blank.")
    @Size(max = 50, message = "Category must not exceed 50 characters.")
    private String category;

    @NotBlank(message = "Release year cannot be blank.")
    @Size(min = 4, max = 4, message = "Release year must be a 4-digit number.")
    private String releaseYear;

    @NotBlank(message = "Description cannot be blank.")
    @Size(max = 200, message = "Description must not exceed 200 characters.")
    private String description;

    // Add this constructor explicitly
    public WatchlistItemDTO(Long id, String name, String category, String releaseYear, String description) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.releaseYear = releaseYear;
        this.description = description;
    }
}