import React from "react";
import { useAuth } from "../../context/AuthContext";
import axios from "axios";

const API_BASE_URL = process.env.REACT_APP_API_BASE_URL; // Use your .env value

const DeleteAccount = () => {
    const { user, logout } = useAuth();

    const handleDeleteAccount = async () => {
        try {
            await axios.delete(`${API_BASE_URL}/auth/delete`, {
                headers: {
                    Authorization: `Bearer ${user.token}`, // Token for authentication
                },
            });

            alert("Account deleted successfully!");
            logout(); // Log the user out after account deletion
        } catch (error) {
            console.error("Error deleting account:", error.message);
            alert(`Failed to delete account: ${error.message}`);
        }
    };

    return (
        <button onClick={handleDeleteAccount}>Delete Account</button>
    );
};

export default DeleteAccount;
