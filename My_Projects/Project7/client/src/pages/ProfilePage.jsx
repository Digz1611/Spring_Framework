import React from "react";
import { useAuth } from "../context/AuthContext";
import "../assets/Profile.css";
import DeleteAccount from "../components/Auth/DeleteAccount";

const ProfilePage = () => {
    const { user, logout } = useAuth();

    const handleLogout = () => {
        logout();
    };

    return (
        <div className="profile-page">
            <h1>Profile</h1>
            {user ? (
                <>
                    <p>Welcome, {user.username || "User"}!</p>
                    <button onClick={handleLogout}>Logout</button>
                    {/* Delete button triggers account deletion */}
                    <DeleteAccount />
                </>
            ) : (
                <p>You are not logged in. Please <a href="/login">login</a>.</p>
            )}
        </div>
    );
};

export default ProfilePage;
