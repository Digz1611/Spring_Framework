import React, { useState, useEffect } from 'react';
import { useParams, useHistory } from 'react-router-dom';
import { updateWatchlistItem } from '../../services/watchlistItemService'; // Importing the update function
import axios from "axios";
import '../../assets/AddItem.css';

const EditItem = () => {
    const { id } = useParams(); // Get item ID from URL
    const history = useHistory(); // To navigate back after updating the item
    const [formData, setFormData] = useState({
        name: '',
        category: '',
        releaseYear: '',
        description: '',
    });

    // Fetch the item details when the component mounts
    useEffect(() => {
        const fetchItem = async () => {
            try {
                const response = await axios.get(`${process.env.REACT_APP_API_BASE_URL}/watchlist/${id}`, {
                    headers: {
                        Authorization: `Bearer ${localStorage.getItem("authToken")}`,
                    },
                });
                setFormData(response.data); // Pre-fill the form with the fetched data
            } catch (error) {
                console.error('Error fetching item:', error);
            }
        };

        fetchItem();
    }, [id]);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData((prevFormData) => ({
            ...prevFormData,
            [name]: value,
        }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        if (!formData.name || !formData.category || !formData.releaseYear || !formData.description) {
            alert("All fields are required.");
            return;
        }

        try {
            await updateWatchlistItem(id, formData); // Call the update function
            alert('Item updated successfully!');
            history.push('/watchlist'); // Navigate back to watchlist page after update
        } catch (error) {
            console.error('Error updating item:', error);
            alert('Failed to update item.');
        }
    };

    return (
        <div className="edit-item-container">
            <div className="edit-item-form-container">
                <form className="edit-item-form" onSubmit={handleSubmit}>
                    <h2>Edit Item</h2>
                    <input
                        type="text"
                        name="name"
                        placeholder="Name"
                        value={formData.name}
                        onChange={handleChange}
                        required
                    />
                    <input
                        type="text"
                        name="category"
                        placeholder="Category (e.g Movie or Series)"
                        value={formData.category}
                        onChange={handleChange}
                        required
                    />
                    <input
                        type="number"
                        name="releaseYear"
                        placeholder="Release Year"
                        value={parseInt(formData.releaseYear)}
                        onChange={handleChange}
                        min="1900"
                        max={new Date().getFullYear()}
                        required
                    />
                    <textarea
                        name="description"
                        placeholder="Description"
                        value={formData.description}
                        onChange={handleChange}
                        required
                    />
                    <button type="submit">Update Item</button>
                </form>
            </div>
        </div>
    );
};

export default EditItem;
