import React, { useState, useEffect } from "react";
import { fetchWatchlist } from "../../services/watchlistItemService"; // Import the fetchWatchlist function
import '../../assets/Watchlist.css'

const Watchlist = () => {
    const [items, setItems] = useState([]);
    const [loading, setLoading] = useState(true); // Loading state
    const [error, setError] = useState(null); // Error state

    // Fetch watchlist items
    useEffect(() => {
        const getWatchlist = async () => {
            try {
                const fetchedItems = await fetchWatchlist(); // Fetch items from the service
                setItems(fetchedItems); // Update state with fetched items
            } catch (err) {
                console.error("Failed to fetch watchlist items:", err); // Log error
                setError("Failed to load watchlist items.");
            } finally {
                setLoading(false); // Stop loading state
            }
        };

        getWatchlist(); // Call the function to fetch data
    }, []); // Empty dependency array ensures it only runs once when the component mounts

    if (loading) {
        return <div>Loading...</div>; // Loading state
    }

    if (error) {
        return <div>{error}</div>; // Error state
    }

    return (
        <div className="watchlist-container">
            <h2 className="watchlist-title">Your Watchlist</h2>
            {items.length === 0 ? (
                <p>Your watchlist is empty.</p> // No items message
            ) : (
                <div className="watchlist-cards">
                    {items.map((item) => (
                        <div key={item.id} className="watchlist-card">
                            <div className="card-header">
                                <h3>{item.name}</h3> {/* Display item name */}
                                <span className="release-year">{item.releaseYear}</span> {/* Display item release year */}
                            </div>
                            <p className="category">{item.category}</p> {/* Display item category */}
                            <p className="description">{item.description}</p> {/* Display item description */}
                        </div>
                    ))}
                </div>
            )}
        </div>
    );
};

export default Watchlist;
