import React, { useState } from 'react';
import '../../assets/EditItem.css';

const EditItem = ({ item, updateItem }) => {
    const [title, setTitle] = useState(item.title);
    const [description, setDescription] = useState(item.description);

    const handleSubmit = (e) => {
        e.preventDefault();
        updateItem({ ...item, title, description });
    };

    return (
        <form onSubmit={handleSubmit}>
            <input
                type="text"
                value={title}
                onChange={(e) => setTitle(e.target.value)}
                required
            />
            <textarea
                value={description}
                onChange={(e) => setDescription(e.target.value)}
                required
            />
            <button type="submit">Save Changes</button>
        </form>
    );
};

export default EditItem;
