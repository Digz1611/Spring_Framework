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

    // Function to handle adding a new item to the watchlist
    const handleAddItem = async (item) => {

        try {
            // Call the service function to add the item to the backend
            const newItem = await addWatchlistItem(item);
            console.log("other" + item);
            console.log("newitem" + newItem);
            setItems((prevItems) => [...prevItems, newItem]); // Update state with the newly added item
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
