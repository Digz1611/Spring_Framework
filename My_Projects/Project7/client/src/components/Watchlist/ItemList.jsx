import React from 'react';
import '../../assets/ItemList.css';

const ItemList = ({ items, deleteItem }) => {
    return (
        <div>
            {items.map((item) => (
                <div key={item.id} style={{ border: '1px solid #ccc', padding: '10px', margin: '10px 0' }}>
                    <h3>{item.title}</h3>
                    <p>{item.description}</p>
                    <button onClick={() => deleteItem(item.id)} style={{ backgroundColor: 'red', color: 'white' }}>
                        Delete
                    </button>
                </div>
            ))}
        </div>
    );
};

export default ItemList;
