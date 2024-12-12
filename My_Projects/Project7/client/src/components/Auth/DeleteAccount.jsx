import '../../assets/DeleteAccount.css';
import React from 'react';
import { useAuth } from '../../useAuth/useAuth';

const DeleteAccount = () => {
    const { deleteAccount } = useAuth();

    const handleDelete = async () => {
        if (window.confirm('Are you sure you want to delete your account? This action cannot be undone.')) {
            await deleteAccount();
            alert('Your account has been deleted.');
        }
    };

    return (
        <div className="delete-account">
            <button onClick={handleDelete} className="delete-button">
                Delete Account
            </button>
        </div>
    );
};

export default DeleteAccount;
