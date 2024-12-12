import React, { useState } from "react";
import { signup } from "../../services/authService";
import '../../assets/Signup.css';

const Signup = () => {
    const [username, setUsername] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const data = await signup(username, email, password);
            // Store the token received from the backend
            localStorage.setItem("authToken", data.token); // or use sessionStorage if preferred
            alert("Signup successful!");
        } catch (error) {
            alert("Signup failed!");
        }
    };

    return (
        <form onSubmit={handleSubmit}>
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
            <input
                type="password"
                placeholder="Password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
            />
            <button type="submit">Sign Up</button>
        </form>
    );
};

export default Signup;



// import React, { useState } from "react";
// import { signup } from "../../services/authService";
// import '../../assets/Signup.css';
//
// const Signup = () => {
//     const [username, setUsername] = useState("");
//     const [email, setEmail] = useState("");
//     const [password, setPassword] = useState("");
//
//     const handleSubmit = async (e) => {
//         e.preventDefault();
//         try {
//             await signup(username, email, password);
//             alert("Signup successful!");
//         } catch (error) {
//             alert("Signup failed!");
//         }
//     };
//
//     return (
//         <form onSubmit={handleSubmit}>
//             <input
//                 type="text"
//                 placeholder="Username"
//                 value={username}
//                 onChange={(e) => setUsername(e.target.value)}
//             />
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
//             <button type="submit">Sign Up</button>
//         </form>
//     );
// };
//
// export default Signup;
