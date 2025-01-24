import React, { useState } from "react";
import { useAuth } from '../../useAuth/useAuth';

const UserManagement = () => {
    const [userId, setUserId] = useState(""); // State to hold the user ID to delete
    const [message, setMessage] = useState(""); // State for feedback messages

    const { token } = useAuth(); // Destructure the token from useAuth

    const deleteUser = async (id) => {
        try {
            console.log("Attempting to delete user with ID:", id);

            const response = await fetch(`http://localhost:8080/api/auth//${id}`, {
                method: "DELETE",
                headers: {
                    "Content-Type": "application/json", // Optional for DELETE
                    Authorization: `Bearer ${token}`, // Use the token from useAuth
                },
            });

            if (!response.ok) {
                const errorMessage = await response.text();
                throw new Error(errorMessage || `HTTP error! status: ${response.status}`);
            }

            const data = await response.json(); // Optional: depends on backend response
            console.log("User deleted successfully:", data);
            setMessage("User deleted successfully.");
        } catch (error) {
            console.error("Error deleting user:", error.message);
            setMessage(`Failed to delete user: ${error.message}`);
        }
    };

    const handleDelete = () => {
        if (!userId) {
            setMessage("Please enter a valid user ID.");
            return;
        }
        deleteUser(userId);
    };

    return (
        <div>
            <h2>User Management</h2>
            <input
                type="text"
                placeholder="Enter User ID"
                value={userId}
                onChange={(e) => setUserId(e.target.value)}
            />
            <button onClick={handleDelete}>Delete User</button>
            {message && <p>{message}</p>}
        </div>
    );
};

export default UserManagement;
