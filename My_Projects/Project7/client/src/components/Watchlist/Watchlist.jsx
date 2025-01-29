import React, { useState, useEffect } from "react";
import { fetchWatchlist } from "../../services/watchlistItemService"; // Import the fetchWatchlist function
import '../../assets/Watchlist.css';
import { MdOutlineDelete } from 'react-icons/md';
import { AiOutlineEdit } from 'react-icons/ai';
import { Link } from 'react-router-dom';

const Watchlist = () => {
    const [items, setItems] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    // Fetch watchlist items
    useEffect(() => {
        const getWatchlist = async () => {
            try {
                const fetchedItems = await fetchWatchlist();
                setItems(fetchedItems);
            } catch (err) {
                console.error("Failed to fetch watchlist items:", err);
                setError("Failed to load watchlist items.");
            } finally {
                setLoading(false);
            }
        };

        getWatchlist();
    }, []);

    if (loading) {
        return <div>Loading...</div>;
    }

    if (error) {
        return <div>{error}</div>;
    }

    const handleEdit = (id) => {
        console.log("abt time for a change");
    };

    const handleDelete = (id) => {
        console.log("and bam, dlted");
    };

    return (
        <div className="watchlist-container">
            <h2 className="watchlist-title">Your Watchlist</h2>
            {items.length === 0 ? (
                <p>Your watchlist is empty.</p>
            ) : (
                <div className="watchlist-cards">
                    {items.map((item) => (
                        <div key={item.id} className="watchlist-card">
                            <div className="card-header">
                                <h3>{item.name}</h3>
                                <span className="release-year">{item.releaseYear}</span>
                            </div>
                            <p className="category">{item.category}</p>
                            <p className="description">{item.description}</p>
                            <div className="card-footer flex justify-center gap-4 mt-4">
                                <Link to={`/watchlist`}>
                                    <AiOutlineEdit
                                        className='text-2xl text-yellow-300 hover:text-black'
                                        onClick={() => handleEdit(item.id)}
                                    />
                                </Link>
                                <button
                                    onClick={() => handleDelete(item.id)}
                                    className="text-2xl text-red-500 hover:text-black"
                                >
                                    <MdOutlineDelete />
                                </button>
                            </div>
                        </div>
                    ))}
                </div>
            )}
        </div>
    );
};

export default Watchlist;
