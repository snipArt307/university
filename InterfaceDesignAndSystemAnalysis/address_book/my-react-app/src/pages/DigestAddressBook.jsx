import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import PageTable from "../components/PageTable.jsx";
import '../style/DigestAddressBook.css';

function DigestAddressBook() {
    const [pages, setPages] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const navigate = useNavigate();

    const getPages = () => {
        setLoading(true);
        setError(null);
        setTimeout(() => {
            const hardcodedData = [
                { id: 1, page: "Бухгалтерия", rights: "Пользователь" },
                { id: 2, page: "Администрация", rights: "Пользователь" },
                { id: 3, page: "IT-отдел", rights: "Администратор" },
                { id: 4, page: "Куча", rights: "Пользователь" },
                { id: 5, page: "Бухгалтерия", rights: "Пользователь" },
                { id: 6, page: "Администрация", rights: "Пользователь" },
                { id: 7, page: "IT-отдел", rights: "Администратор" },
                { id: 8, page: "Куча", rights: "Пользователь" },
                { id: 9, page: "Бухгалтерия", rights: "Пользователь" },
                { id: 10, page: "Администрация", rights: "Пользователь" },
                { id: 11, page: "IT-отдел", rights: "Администратор" },
                { id: 12, page: "Куча", rights: "Пользователь" },
            ];
            setPages(hardcodedData);
            setLoading(false);
        }, 1000);
    };

    useEffect(() => {
        getPages();
    }, []);

    const handleBack = () => {
        navigate('/address-book');
    };

    return (
        <div className="digest-book">
            <h1>Адресная книга</h1>
            <p>Выберите готовую адресную базу</p>
            {loading && <p className="loading">Загрузка...</p>}
            {error && <p className="error">Ошибка: {error}</p>}
            {!loading && !error && <PageTable pages={pages} />}
            <div className="button-group">
                <button onClick={handleBack} className="icon-button">
                    Назад
                </button>
                <button onClick={getPages} className="icon-button" disabled={loading}>
                    {loading ? 'Обновление...' : 'Обновить'}
                </button>
            </div>
        </div>
    );
}

export default DigestAddressBook;