import React, { useState, useEffect } from "react";
import { getWatchlist } from "../services/authService"; // Make sure this points to the correct service

const WatchlistPage = () => {
    const [watchlist, setWatchlist] = useState([]);

    useEffect(() => {
        const fetchWatchlist = async () => {
            try {
                const data = await getWatchlist();
                setWatchlist(data);
            } catch (error) {
                console.error("Failed to fetch watchlist:", error);
            }
        };

        fetchWatchlist();
    }, []);

    return (
        <div>
            <h1>Your Watchlist</h1>
            <ul>
                {watchlist.map((item) => (
                    <li key={item.id}>{item.name}</li>
                ))}
            </ul>
        </div>
    );
};

export default WatchlistPage;



// import React, { useEffect, useState } from "react";
// import ItemList from "../components/Watchlist/ItemList";
// import { fetchWatchlist, addWatchlistItem } from "../services/watchlistItemService"; // Import your service
// import { deleteWatchlistItem } from "../services/watchlistItemService"; // Assuming you have this function for item deletion
//
// const WatchlistPage = () => {
//     const [items, setItems] = useState([]);
//
//     useEffect(() => {
//         const userId = localStorage.getItem("userId"); // Modify this as needed (e.g., context or props)
//         const loadWatchlist = async () => {
//             const fetchedItems = await fetchWatchlist(userId);
//             setItems(fetchedItems);
//         };
//         loadWatchlist();
//     }, []);
//
//     const deleteItem = async (itemId) => {
//         const userId = localStorage.getItem("userId"); // Modify this accordingly
//         await deleteWatchlistItem(userId, itemId); // Assume API for deleting the item exists
//         setItems(items.filter((item) => item.id !== itemId)); // Remove item from state after deletion
//     };
//
//     const addItemToWatchlist = async (item) => {
//         const userId = localStorage.getItem("userId"); // Modify this accordingly
//         const newItem = await addWatchlistItem(userId, item); // Add item to backend
//         setItems([...items, newItem]); // Update state with new item
//     };
//
//     return (
//         <div>
//             <h1>Your Watchlist</h1>
//             {/* Pass items and deleteItem as props */}
//             <ItemList items={items} deleteItem={deleteItem} />
//             {/* Example to add an item, modify as per your form */}
//             <button onClick={() => addItemToWatchlist({ title: "New Item", description: "Item Description" })}>
//                 Add Item to Watchlist
//             </button>
//         </div>
//     );
// };
//
// export default WatchlistPage;
