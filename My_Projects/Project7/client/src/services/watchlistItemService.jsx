import axios from "axios";

const API_BASE_URL = process.env.REACT_APP_API_BASE_URL; // Updated to use your .env value

export const fetchWatchlist = async (userId) => {
    const response = await axios.get(`${API_BASE_URL}/watchlist/${userId}`, {
        headers: {
            Authorization: `Bearer ${localStorage.getItem("authToken")}`, // Include token in header
        },
    });
    return response.data;
};

export const addWatchlistItem = async (item) => {
    console.log(localStorage.getItem("authToken"));
    console.log("final ", item);
    const response = await axios.post(`${API_BASE_URL}/watchlist/add`, item, {
        headers: {
            Authorization: `Bearer ${localStorage.getItem("authToken")}`, // Include token in header
        },
    }).catch((e) => console.log("ERROR", e));
    return response.data;
};

export const deleteWatchlistItem = async (userId, itemId) => {
    await axios.delete(`${API_BASE_URL}/watchlist/${userId}/delete/${itemId}`, {
        headers: {
            Authorization: `Bearer ${localStorage.getItem("authToken")}`, // Include token in header
        },
    });
};
