import axios from "axios";

const API_BASE_URL = process.env.REACT_APP_API_BASE_URL; // Updated to use your .env value

// Fetch watchlist items
export const fetchWatchlist = async () => {

    try {
        const response = await axios.get(`${API_BASE_URL}/watchlist/`, {
            headers: {
                Authorization: `Bearer ${localStorage.getItem("authToken")}`, // Include token in header
            },
        });
        console.log("Fetched Watchlist Items:", response.data); // Log to check the data
        return response.data; // Return the data from the response
    } catch (error) {
        console.error("Error fetching watchlist items:", error); // Log any errors
        throw error; // Throw error to handle it in the component
    }
};

// Add watchlist items
export const addWatchlistItem = async (item) => {
    console.log(localStorage.getItem("authToken"));

    try {
        const response = await axios.post(`${API_BASE_URL}/watchlist/add`, item, {
            headers: {
                Authorization: `Bearer ${localStorage.getItem("authToken")}`, // Include token in header
            },
        });
        return response.data; // Assuming the backend returns the full item, including the generated ID
    } catch (e) {
        console.log("ERROR", e);
        throw e; // Throw the error so it can be handled in the calling component
    }
};


// Update an existing watchlist item
export const updateWatchlistItem = async (updatedItem) => {
    try {
        const response = await fetch(`${process.env.REACT_APP_API_BASE_URL}/watchlist/update/{id}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(updatedItem),
        });

        if (!response.ok) {
            throw new Error("Failed to update item");
        }
        return await response.json(); // Return the updated item
    } catch (error) {
        console.error("Error updating item:", error);
        throw error;
    }
};

// Delete a watchlist item by ID
export const deleteWatchlistItem = async (id) => {
    try {
        const response = await axios.delete(`${API_BASE_URL}/watchlist/delete/${id}`, {
            headers: {
                Authorization: `Bearer ${localStorage.getItem("authToken")}`, // Include token in header
            },
        });

        if (!response.status === 200) {
            throw new Error("Failed to delete item");
        }
        return response.data; // Return the response after deletion
    } catch (error) {
        console.error("Error deleting item:", error);
        throw error; // Throw the error to handle it in the component
    }
};
