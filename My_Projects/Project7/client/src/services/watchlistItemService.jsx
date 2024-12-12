import axios from "axios";

const API_BASE_URL = process.env.REACT_APP_API_BASE_URL;

export const fetchWatchlist = async (userId) => {
    const response = await axios.get(`${API_BASE_URL}/watchlist?userId=${userId}`);
    return response.data;
};

export const addWatchlistItem = async (userId, item) => {
    const response = await axios.post(`${API_BASE_URL}/watchlist/add?userId=${userId}`, item);
    return response.data;
};
