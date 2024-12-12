import React from 'react';

const DeleteItem = ({ deleteItem, id }) => {
    const handleDelete = () => {
        if (window.confirm("Are you sure you want to delete this item?")) {
            deleteItem(id);
        }
    };

    return (
        <button onClick={handleDelete} style={{ backgroundColor: 'red', color: 'white' }}>
            Delete Item
        </button>
    );
};

export default DeleteItem;
