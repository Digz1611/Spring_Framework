import axios from "axios";

const API_BASE_URL = process.env.REACT_APP_API_BASE_URL;

// Helper to get the authorization header
const getAuthHeader = () => {
    const token = localStorage.getItem("authToken");
    if (token) {
        return { Authorization: `Bearer ${token}` };
    }
    return {};
};

// Login function
export const login = async (email, password) => {
    try {
        const response = await axios.post(`${API_BASE_URL}/auth/login`, {
            email,
            password,
        });
        console.log("Raw Response:", response); // Log the raw response object
        const token = response.data;
        console.log(response);

        localStorage.setItem("authToken", token); // Store token in localStorage
        console.log("JWT Token:", token); // Log the token for debugging
        return response.data;
    } catch (error) {
        // Log error details for debugging
        if (error.response) {
            console.error("Server Error:", error.response.data);
        } else if (error.request) {
            console.error("No Response Received:", error.request);
        } else {
            console.error("Error Setting Up Request:", error.message);
        }
        throw error; // Re-throw the error to handle it in the caller
    }
};

// Signup function
export const signup = async (username, email, password) => {
    try {
        const response = await axios.post(`${API_BASE_URL}/auth/signup`, {
            username,
            email,
            password,
        });
        console.log("Signup Response:", response); // Log the raw response
        return response.data;
    } catch (error) {
        console.error("Signup Error:", error.response?.data || error.message);
        throw error;
    }
};

// Get watchlist function
export const getWatchlist = async () => {
    try {
        const response = await axios.get(`${API_BASE_URL}/watchlist`, {
            headers: getAuthHeader(), // Include JWT token in the request header
        });
        console.log("Watchlist Response:", response); // Log the raw response
        return response.data;
    } catch (error) {
        console.error("Get Watchlist Error:", error.response?.data || error.message);
        throw error;
    }
};
