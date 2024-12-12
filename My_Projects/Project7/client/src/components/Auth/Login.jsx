import React, { useState } from "react";
import { login } from "../../services/authService";
import '../../assets/Login.css';

const Login = () => {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const data = await login(email, password);
            // Store the token received from the backend
            localStorage.setItem("authToken", data.token); // or use sessionStorage if preferred
            alert("Login successful!");
        } catch (error) {
            alert("Login failed!");
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <input
                type="email"
                placeholder="Email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
            />
            <input
                type="password"
                placeholder="Password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
            />
            <button type="submit">Login</button>
        </form>
    );
};

export default Login;



// import React, { useState } from "react";
// import { login } from "../../services/authService";
// import '../../assets/Login.css';
//
// const Login = () => {
//     const [email, setEmail] = useState("");
//     const [password, setPassword] = useState("");
//
//     const handleSubmit = async (e) => {
//         e.preventDefault();
//         try {
//             await login(email, password);
//             alert("Login successful!");
//         } catch (error) {
//             alert("Login failed!");
//         }
//     };
//
//     return (
//         <form onSubmit={handleSubmit}>
//             <input
//                 type="email"
//                 placeholder="Email"
//                 value={email}
//                 onChange={(e) => setEmail(e.target.value)}
//             />
//             <input
//                 type="password"
//                 placeholder="Password"
//                 value={password}
//                 onChange={(e) => setPassword(e.target.value)}
//             />
//             <button type="submit">Login</button>
//         </form>
//     );
// };
//
// export default Login;
