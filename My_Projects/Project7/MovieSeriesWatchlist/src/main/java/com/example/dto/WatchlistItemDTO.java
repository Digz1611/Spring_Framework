package com.example.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor // Add this to generate an all-args constructor
@Builder
public class WatchlistItemDTO {

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
    @Size(max = 1000, message = "Description must not exceed 1000 characters.")
    private String description;
}
