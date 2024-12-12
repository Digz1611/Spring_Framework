import axios from "axios";

const API_BASE_URL = process.env.REACT_APP_API_BASE_URL;

// Fetch watchlist items for a user
export const fetchWatchlist = async (userId) => {
    const response = await axios.get(`${API_BASE_URL}/watchlist?userId=${userId}`);
    return response.data;
};

// Add an item to the watchlist
export const addWatchlistItem = async (userId, item) => {
    const response = await axios.post(`${API_BASE_URL}/watchlist/add?userId=${userId}`, item);
    return response.data;
};

// Delete an item from the watchlist
export const deleteWatchlistItem = async (userId, itemId) => {
    const response = await axios.delete(`${API_BASE_URL}/watchlist/delete?userId=${userId}&itemId=${itemId}`);
    return response.data;
};
