import React from "react";
import { useAuth } from "../context/AuthContext";
import { useNavigate } from "react-router-dom";
import "../assets/Profile.css";
import axios from "axios";

const API_BASE_URL = process.env.REACT_APP_API_BASE_URL; // Updated to use your .env value

const ProfilePage = () => {
    const { user, logout } = useAuth(); // Assuming token is available in useAuth
    const navigate = useNavigate();

    const handleLogout = () => {
        logout();
        navigate("/login"); // Redirect to login page after logout
    };

    const handleDeleteAccount = async () => {

        try {
            const response = await axios.delete(`${API_BASE_URL}/auth/delete`, {

                headers: {
                    Authorization: `Bearer ${user.token}`, // Attach the token for authentication
                },
            });
console.log("Test for this thingy");
            console.log(response)

            alert("Account deleted successfully!");
            logout(); // Log the user out after account deletion
            navigate("/signup"); // Redirect to signup page
        } catch (error) {
            console.error("Error deleting account:", error.message);
            alert(`Failed to delete account: ${error.message}`);
        }
    };

    return (
        <div className="profile-page">
            <h1>Profile</h1>
            {user ? (
                <>
                    <p>Welcome, {user.name || "User"}!</p>
                    <button onClick={handleLogout}>Logout</button>
                    <button onClick={handleDeleteAccount}>Delete Account</button>
                </>
            ) : (
                <p>You are not logged in. Please <a href="/login">login</a>.</p>
            )}
        </div>
    );
};

export default ProfilePage;
