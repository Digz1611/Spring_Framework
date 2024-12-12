import React from 'react';
import { Link } from 'react-router-dom';
import '../assets/Navbar.css';

const Navbar = () => {
    return (
        <nav style={{ padding: '10px', backgroundColor: '#007BFF', color: 'white' }}>
            <Link to="/" style={{ color: 'white', marginRight: '10px' }}>Home</Link>
            <Link to="/watchlist" style={{ color: 'white', marginRight: '10px' }}>Watchlist</Link>
            <Link to="/profile" style={{ color: 'white' }}>Profile</Link>
        </nav>
    );
};

export default Navbar;
