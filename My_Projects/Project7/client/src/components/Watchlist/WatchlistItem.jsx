import React from "react";

const WatchlistItem = ({ title, description }) => {
    return (
        <div style={{ border: "1px solid #ccc", padding: "10px", margin: "10px 0" }}>
            <h2>{title}</h2>
            <p>{description}</p>
        </div>
    );
};

export default WatchlistItem;
