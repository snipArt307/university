import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { FaFolder, FaPlus } from 'react-icons/fa';
import '../style/AddressBook.css';

function AddressBook({ username, role }) {
  const navigate = useNavigate();
  const [notification, setNotification] = useState('');
  const [isLoading, setIsLoading] = useState(false);

  const handleOpenBook = () => {
    setIsLoading(true);
    setNotification('Открываем готовую адресную книгу...');
    setTimeout(() => {
      setIsLoading(false);
      navigate('/digest-book');
    }, 1000);
  };

  const handleCreateBook = () => {
    setIsLoading(true);
    setNotification('Создаём новую адресную книгу...');
    setTimeout(() => {
      setIsLoading(false);
      navigate('/create-book');
    }, 1000);
  };

  return (
    <div className="address-book-container">
      <h1>Адресная книга</h1>
      <p>Выберите категорию</p>
      {notification && <p className="notification">{notification}</p>}
      <div className="button-group">
        <button onClick={handleOpenBook} className="icon-button" disabled={isLoading}>
          <FaFolder className="icon folder-icon" />
          Открыть готовую адресную книгу
        </button>
        <button onClick={handleCreateBook} className="icon-button" disabled={isLoading}>
          <FaPlus className="icon create-icon" />
          Создать новую адресную книгу
        </button>
      </div>
      <p className="username">УЧЕТНАЯ ЗАПИСЬ: {username || 'user@example.com'}</p>
      <p className="username">Роль: {role}</p>
    </div>
  );
}

export default AddressBook;