import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { login } from "../../services/authService";
import "../../assets/Login.css";

const Login = () => {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [passwordVisible, setPasswordVisible] = useState(false);
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await login(email, password);
            alert("Login successful!");
            navigate("/profile");
        } catch (error) {
            alert("Login failed!");
        }
    };

    const handleSignupRedirect = () => {
        navigate("/signup");
    };

    return (
        <div className="login-container">
            <div className="login-form-container">
                <form onSubmit={handleSubmit} className="login-form">
                    <h2>Login</h2>
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
                    <button type="submit">Login</button>
                </form>
                <div className="signup-redirect">
                    <p>Don't have an account?</p>
                    <button onClick={handleSignupRedirect}>Sign Up</button>
                </div>
            </div>
        </div>
    );
};

export default Login;



// import React, { useState } from "react";
// import { useNavigate } from "react-router-dom";
// import { login } from "../../services/authService";
// import "../../assets/Login.css";
//
// const Login = () => {
//     const [email, setEmail] = useState("");
//     const [password, setPassword] = useState("");
//     const [passwordVisible, setPasswordVisible] = useState(false); // For toggling password visibility
//     const navigate = useNavigate();
//
//     const handleSubmit = async (e) => {
//         e.preventDefault();
//         try {
//             const data = await login(email, password);
//             alert("Login successful!");
//             navigate("/profile");
//         } catch (error) {
//             alert("Login failed!");
//         }
//     };
//
//     const handleSignupRedirect = () => {
//         navigate("/signup");
//     };
//
//     return (
//         <div className="login-container">
//             <form onSubmit={handleSubmit}>
//                 <input
//                     type="email"
//                     placeholder="Email"
//                     value={email}
//                     onChange={(e) => setEmail(e.target.value)}
//                 />
//                 <div className="password-field">
//                     <input
//                         type={passwordVisible ? "text" : "password"}
//                         placeholder="Password"
//                         value={password}
//                         onChange={(e) => setPassword(e.target.value)}
//                     />
//                     <button
//                         type="button"
//                         onClick={() => setPasswordVisible(!passwordVisible)}
//                         className="toggle-password"
//                     >
//                         {passwordVisible ? "Hide" : "Show"}
//                     </button>
//                 </div>
//                 <button type="submit">Login</button>
//             </form>
//             <div className="signup-redirect">
//                 <p>Don't have an account?</p>
//                 <button onClick={handleSignupRedirect}>Sign Up</button>
//             </div>
//         </div>
//     );
// };
//
// export default Login;


// import React, { useState } from "react";
// import { useNavigate } from "react-router-dom";
// import { login } from "../../services/authService";
// import "../../assets/Login.css";
//
// const Login = () => {
//     const [email, setEmail] = useState("");
//     const [password, setPassword] = useState("");
//     const navigate = useNavigate();
//
//     const handleSubmit = async (e) => {
//         e.preventDefault();
//         try {
//             const data = await login(email, password);
//             alert("Login successful!");
//             navigate("/profile");
//         } catch (error) {
//             alert("Login failed!");
//         }
//     };
//
//     const handleSignupRedirect = () => {
//         navigate("/signup");
//     };
//
//     return (
//         <div className="login-container">
//             <form onSubmit={handleSubmit}>
//                 <input
//                     type="email"
//                     placeholder="Email"
//                     value={email}
//                     onChange={(e) => setEmail(e.target.value)}
//                 />
//                 <input
//                     type="password"
//                     placeholder="Password"
//                     value={password}
//                     onChange={(e) => setPassword(e.target.value)}
//                 />
//                 <button type="submit">Login</button>
//             </form>
//             <div className="signup-redirect">
//                 <p>Don't have an account?</p>
//                 <button onClick={handleSignupRedirect}>Sign Up</button>
//             </div>
//         </div>
//     );
// };
//
// export default Login;
