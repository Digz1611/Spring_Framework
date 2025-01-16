import React, { useState } from 'react';
import '../../assets/AddItem.css';

const AddItem = ({ addItem }) => {
    const [formData, setFormData] = useState({
        name: '',
        category: '',
        releaseDate: '', // Year only
        description: '',
    });

    const handleSubmit = (e) => {
        e.preventDefault();

        // Validate all fields
        if (!formData.name || !formData.category || !formData.releaseDate || !formData.description) {
            alert("All fields are required.");
            return;
        }

        addItem(formData); // Call the provided addItem function with form data
        setFormData({ name: '', category: '', releaseDate: '', description: '' }); // Reset form
    };

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    return (
        <form className="add-item-form" onSubmit={handleSubmit}>
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
                name="releaseDate"
                placeholder="Release Year"
                value={formData.releaseDate}
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
            <button type="submit">Add Item</button>
        </form>
    );
};

export default AddItem;
