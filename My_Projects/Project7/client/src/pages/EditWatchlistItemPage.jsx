import React, { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { fetchWatchlist, updateWatchlistItem } from "../services/watchlistItemService"; // Ensure these methods exist
import '../assets/AddItem.css'

const EditWatchlistItemPage = () => {
    const { id } = useParams(); // Get the id from URL params
    const [item, setItem] = useState(null);
    const [name, setName] = useState("");
    const [releaseYear, setReleaseYear] = useState("");
    const [category, setCategory] = useState("");
    const [description, setDescription] = useState("");
    const navigate = useNavigate(); // Hook to navigate after save/update

    // Fetch the current item details
    useEffect(() => {
        const getItem = async () => {
            try {
                const fetchedItem = await fetchWatchlist(id); // Fetch the item by id
                setItem(fetchedItem);
                setName(fetchedItem.name);
                setReleaseYear(fetchedItem.releaseYear);
                setCategory(fetchedItem.category);
                setDescription(fetchedItem.description);
            } catch (error) {
                console.error("Error fetching item:", error);
            }
        };

        getItem();
    }, [id]);

    const handleSave = async () => {
        const updatedItem = { id, name, releaseYear, category }; // Updated item data

        try {
            await updateWatchlistItem(updatedItem); // Update the item in backend
            navigate("/watchlist"); // Redirect to watchlist after saving
        } catch (error) {
            console.error("Error updating item:", error);
        }
    };

    if (!item) {
        return <div>Loading...</div>;
    }

    return (
        <div>
            <h1>Edit Item</h1>
            <form onSubmit={handleSave}>
                <label>Name:</label>
                <input
                    type="text"
                    value={name}
                    onChange={(e) => setName(e.target.value)}
                />
                <label>Release Year:</label>
                <input
                    type="number"
                    value={releaseYear}
                    onChange={(e) => setReleaseYear(e.target.value)}
                />
                <label>Category:</label>
                <input
                    type="text"
                    value={category}
                    onChange={(e) => setCategory(e.target.value)}
                />
                <label>Description:</label>
                <input
                    type="text"
                    value={description}
                    onChange={(e) => setDescription(e.target.value)}
                />
                <button type="submit">Edit Item</button>
            </form>
        </div>
    );
};

export default EditWatchlistItemPage;
