import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import { useState } from 'react';
import Login from './pages/Login';
import AddressBook from './pages/AddressBook';
import CreateNewAddressBook from './pages/CreateNewAddressBook';
import DigestAddressBook from './pages/DigestAddressBook';
import TableContent from './pages/TableContent';

function App() {
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const [username, setUsername] = useState('');
  const [role, setRole] = useState('');

  return (
    <Router>
      <div className="app">
        <Routes>
          <Route
            path="/"
            element={
              <Login 
                setIsAuthenticated={setIsAuthenticated} 
                setUsername={setUsername}
                setRole={setRole} 
              />
            }
          />
          <Route
            path="/address-book"
            element={isAuthenticated ? <AddressBook username={username} role={role} /> : <Navigate to="/" />}
          />
          <Route
            path="/create-book"
            element={isAuthenticated ? <CreateNewAddressBook /> : <Navigate to="/" />}
          />
          <Route
            path="/digest-book"
            element={isAuthenticated ? <DigestAddressBook /> : <Navigate to="/" />}
          />
          <Route
            path="/table-content/:tableId"
            element={isAuthenticated ? <TableContent role={role} /> : <Navigate to="/" />}
          />
        </Routes>
      </div>
    </Router>
  );
}

export default App;