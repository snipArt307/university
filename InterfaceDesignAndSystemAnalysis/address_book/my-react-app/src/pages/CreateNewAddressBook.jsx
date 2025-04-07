import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import '../style/CreateNewAddressBook.css';

function CreateNewAddressBook() {
    const [nameNewTabel, setNameNewTabel] = useState('');
    const [notification, setNotification] = useState('');
    const [isLoading, setIsLoading] = useState(false);
    const navigate = useNavigate();

    const handleClickCreateTable = (tableName) => {
        const content = [];
        navigate(`/table-content/${tableName}`, {
          state: { tableName, content },
        });
    };

    const handleCreateNewAddressBook = () => {
        setNotification('');
        if (nameNewTabel.trim()) {
            setIsLoading(true);
            setNotification('Новая таблица создана!');
            setTimeout(() => {
                setIsLoading(false);
                handleClickCreateTable(nameNewTabel);
            }, 1000);
        } else {
            setNotification('Пожалуйста, введите название таблицы!');
        }
    };

    const handleToBack = () => {
        navigate('/address-book');
    };

    const handleKeyDown = (e) => {
        if (e.key === 'Enter') {
            handleCreateNewAddressBook();
        }
    };

    return (
        <div className="create-container">
            <h1>Адресная книга</h1>
            <p>Введите название новой адресной книги</p>
            {notification && <p className="notification">{notification}</p>}
            <div className="input-group">
                <label htmlFor="name">Название:</label>
                <input
                    id="name"
                    type="text"
                    value={nameNewTabel}
                    onChange={(e) => setNameNewTabel(e.target.value)}
                    onKeyDown={handleKeyDown}
                    placeholder="Введите название"
                    aria-required="true"
                />
            </div>
            <div className="button-group">
                <button onClick={handleToBack} className="icon-button">
                    Назад
                </button>
                <button onClick={handleCreateNewAddressBook} className="icon-button" disabled={isLoading}>
                    {isLoading ? 'Создание...' : 'Создать'}
                </button>
            </div>
        </div>
    );
}

export default CreateNewAddressBook;