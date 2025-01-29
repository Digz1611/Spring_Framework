import React, { useState, useEffect } from 'react';
import { addWatchlistItem, fetchWatchlist } from '../services/watchlistItemService';
import AddItem from '../components/Watchlist/AddItem';

const WatchlistPage = () => {
    const [items, setItems] = useState([]);
    const [userId, setUserId] = useState(localStorage.getItem('userId') || ''); // Assuming the user ID is stored in localStorage

    // Fetch watchlist items when the component mounts
    useEffect(() => {
        const fetchItems = async () => {
            if (userId) {
                const fetchedItems = await fetchWatchlist(userId);
                setItems(fetchedItems);
            }
        };

        fetchItems();
    }, [userId]);

    const handleAddItem = async (item) => {
        try {
            // Call the service function to add the item to the backend
            const newItem = await addWatchlistItem(item);

            // Log the original item and the new item returned from the backend
            console.log("Item to Add:", item);
            console.log("New Item from Backend:", newItem);

            // Log the ID of the new item
            console.log("New Item ID after adding:", newItem.id); // Log the ID of the newly added item

            // Update the state with the new item, ensuring the item ID is included
            setItems((prevItems) => [...prevItems, newItem]);
        } catch (error) {
            console.error("Error adding item to watchlist:", error);
        }
    };


    return (
        <div>
            <h1>My Watchlist</h1>
            <AddItem addItem={handleAddItem} /> {/* Pass the handleAddItem function to AddItem component */}

            {/* Display the current watchlist */}
            <ul>
                {items.map((item) => (
                    <li key={item.id}>
                        {item.name} ({item.releaseYear}) - {item.category}
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default WatchlistPage;
