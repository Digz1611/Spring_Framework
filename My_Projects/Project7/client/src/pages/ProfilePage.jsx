import React from "react";
import { useAuth } from "../context/AuthContext";
import { useNavigate } from "react-router-dom";
import "../assets/Profile.css";

const ProfilePage = () => {
    const { user, logout, deleteUser } = useAuth();
    const navigate = useNavigate();

    const handleLogout = () => {
        logout();
        navigate("/login"); // Redirect to login page after logout
    };

    const handleDeleteAccount = () => {
        deleteUser();
        navigate("/signup"); // Redirect to signup page after account deletion
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
