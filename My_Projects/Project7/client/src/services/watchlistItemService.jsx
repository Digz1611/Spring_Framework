import axios from "axios";

const API_BASE_URL = process.env.REACT_APP_API_URL || "http://localhost:5000"; // Use environment variable for the backend URL

export const fetchWatchlist = async (userId) => {
    const response = await axios.get(`${API_BASE_URL}/watchlist/${userId}`);
    return response.data; // Ensure this matches your backend response format
};

export const addWatchlistItem = async (userId, item) => {
    const response = await axios.post(`${API_BASE_URL}/watchlist/${userId}/add`, item);
    return response.data; // Ensure this matches your backend response format
};

export const deleteWatchlistItem = async (userId, itemId) => {
    await axios.delete(`${API_BASE_URL}/watchlist/${userId}/delete/${itemId}`);
};


// import axios from "axios";
//
// const API_BASE_URL = process.env.REACT_APP_API_BASE_URL;
//
// // Fetch watchlist items for a user
// export const fetchWatchlist = async (userId) => {
//     const response = await axios.get(`${API_BASE_URL}/watchlist?userId=${userId}`);
//     return response.data;
// };
//
// // Add an item to the watchlist
// export const addWatchlistItem = async (userId, item) => {
//     const response = await axios.post(`${API_BASE_URL}/watchlist/add?userId=${userId}`, item);
//     return response.data;
// };
//
// // Delete an item from the watchlist
// export const deleteWatchlistItem = async (userId, itemId) => {
//     const response = await axios.delete(`${API_BASE_URL}/watchlist/delete?userId=${userId}&itemId=${itemId}`);
//     return response.data;
// };
