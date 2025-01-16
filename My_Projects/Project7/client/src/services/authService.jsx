import axios from "axios";

const API_BASE_URL = process.env.REACT_APP_API_BASE_URL;

const getAuthHeader = () => {
    const token = localStorage.getItem("authToken");
    if (token) {
        return { Authorization: `Bearer ${token}` };
    }
    return {};
};

export const login = async (email, password) => {
    const response = await axios.post(`${API_BASE_URL}/auth/login`, {
        email,
        password,
    });
    localStorage.setItem("authToken", response.data.token); // Store token in localStorage
    return response.data;
};

export const signup = async (username, email, password) => {
    const response = await axios.post(`${API_BASE_URL}/auth/signup`, {
        username,
        email,
        password,
    });
    return response.data;
};

export const getWatchlist = async () => {
    const response = await axios.get(`${API_BASE_URL}/watchlist`, {
        headers: getAuthHeader(), // Include JWT token in the request header
    });
    return response.data;
};



// import axios from "axios";
//
// const API_BASE_URL = process.env.REACT_APP_API_BASE_URL;
//
// const getAuthHeader = () => {
//     const token = localStorage.getItem("authToken");
//     if (token) {
//         return { Authorization: `Bearer ${token}` };
//     }
//     return {};
// };
//
// export const login = async (email, password) => {
//     const response = await axios.post(`${API_BASE_URL}/auth/login`, {
//         email,
//         password,
//     });
//     return response.data; // Ensure 'token' is part of the response
// };
//
// export const signup = async (username, email, password) => {
//     const response = await axios.post(`${API_BASE_URL}/auth/signup`, {
//         username,
//         email,
//         password,
//     });
//     return response.data; // Ensure 'token' is part of the response
// };
//
// export const getWatchlist = async () => {
//     const response = await axios.get(`${API_BASE_URL}/watchlist`, {
//         headers: getAuthHeader(), // Include JWT token in the request header
//     });
//     return response.data;
// };
