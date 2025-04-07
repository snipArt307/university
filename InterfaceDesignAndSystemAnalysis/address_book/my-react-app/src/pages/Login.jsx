import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import '../style/Login.css';

function Login({ setIsAuthenticated, setUsername, setRole }) {
  const [login, setLogin] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const [isLoading, setIsLoading] = useState(false);
  const navigate = useNavigate();

  const handleLogin = () => {
    setError('');
    const trimmedLogin = login.trim();
    const trimmedPassword = password.trim();

    if (!trimmedLogin || !trimmedPassword) {
      setError('Пожалуйста, заполните все поля.');
      return;
    }

    setIsLoading(true);
    const validLogin = 'admin';
    const validPassword = '12345';

    if (trimmedLogin === validLogin && trimmedPassword === validPassword) {
      setIsAuthenticated(true);
      setUsername(trimmedLogin);
      setRole('admin');
      navigate('/address-book');
    } else {
      setError('Неверный логин или пароль.');
    }
    setIsLoading(false);
  };

  const handleKeyDown = (e) => {
    if (e.key === 'Enter') {
      handleLogin();
    }
  };

  return (
    <div className="login-container">
      <h1>Авторизация</h1>
      <p>Введите логин и пароль</p>
      {error && <p className="error">{error}</p>}
      <div className="input-group">
        <label htmlFor="login">Логин:</label>
        <input
          id="login"
          type="text"
          value={login}
          onChange={(e) => setLogin(e.target.value)}
          onKeyDown={handleKeyDown}
          placeholder="Введите логин"
          aria-required="true"
        />
      </div>
      <div className="input-group">
        <label htmlFor="password">Пароль:</label>
        <input
          id="password"
          type="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          onKeyDown={handleKeyDown}
          placeholder="Введите пароль"
          aria-required="true"
        />
      </div>
      <button onClick={handleLogin} disabled={isLoading}>
        {isLoading ? 'Вход...' : 'Войти'}
      </button>
    </div>
  );
}

export default Login;