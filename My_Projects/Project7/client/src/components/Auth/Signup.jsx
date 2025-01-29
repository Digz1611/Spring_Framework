import React, { useState } from "react";
import { signup } from "../../services/authService";
import { useNavigate } from "react-router-dom"; // Import useNavigate
import "../../assets/Auth.css";

const Signup = () => {
    const [username, setUsername] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [passwordVisible, setPasswordVisible] = useState(false);

    const navigate = useNavigate(); // Initialize useNavigate

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const data = await signup(username, email, password);
            localStorage.setItem("authToken", data.token);
            alert("Signup successful!");
            navigate("/login"); // Redirect to /login
        } catch (error) {
            alert("Signup failed!");
        }
    };

    return (
        <div className="login-container">
            <div className="login-form-container">
                <form onSubmit={handleSubmit} className="login-form">
                    <h2>Sign Up</h2>
                    <input
                        type="text"
                        placeholder="Username"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                    />
                    <input
                        type="email"
                        placeholder="Email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                    />
                    <div className="password-field">
                        <input
                            type={passwordVisible ? "text" : "password"}
                            placeholder="Password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                        />
                        <button
                            type="button"
                            onClick={() => setPasswordVisible(!passwordVisible)}
                            className="toggle-password"
                        >
                            {passwordVisible ? "Hide" : "Show"}
                        </button>
                    </div>
                    <button type="submit">Sign Up</button>
                </form>
            </div>
        </div>
    );
};

export default Signup;
