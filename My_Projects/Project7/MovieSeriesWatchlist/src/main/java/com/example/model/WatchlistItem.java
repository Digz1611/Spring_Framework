package com.example.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "watchlist_items")
public class WatchlistItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name cannot be blank.")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Category cannot be blank.")
    @Column(nullable = false)
    private String category;

    @NotBlank(message = "Release year cannot be blank.")
    @Column(name = "release_year", nullable = false)
    private String releaseYear;

    @NotBlank(message = "Description cannot be blank.")
    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull(message = "User cannot be null.")
    private User user;
}
