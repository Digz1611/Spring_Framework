package com.example.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "watchlist_items") // Table name
public class WatchlistItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Unique identifier for the watchlist item

    @Column(nullable = false)
    private String name; // Name of the item (e.g., movie/series title)

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoryType category; // Category type (MOVIE or SERIES)

    @Column(name = "release_date")
    private LocalDate releaseDate; // Release date of the movie or series

    @Column(columnDefinition = "TEXT") // Optional description
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // Foreign key connecting the user
    private User user; // Association with the user who added the item
}

// Enum embedded within the same file
enum CategoryType {
    MOVIE,  // Represents movies
    SERIES  // Represents series
}
