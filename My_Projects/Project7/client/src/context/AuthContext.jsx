import React, { createContext, useContext, useState, useEffect } from 'react';

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
    const [user, setUser] = useState(null);

    useEffect(() => {
        const token = localStorage.getItem("authToken");
        if (token) {
            // Optionally decode or validate token here
            setUser({ token });
        }
    }, []);

    const login = (userData) => setUser(userData);
    const logout = () => setUser(null);
    const deleteUser = () => {
        // Add backend API call for deleting user
        setUser(null);
    };

    return (
        <AuthContext.Provider value={{ user, login, logout, deleteUser }}>
            {children}
        </AuthContext.Provider>
    );
};

export const useAuth = () => useContext(AuthContext);
