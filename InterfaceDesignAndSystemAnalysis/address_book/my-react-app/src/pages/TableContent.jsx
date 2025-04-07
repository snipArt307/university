import React, { useState } from "react";
import { useLocation, useParams, useNavigate } from "react-router-dom";
import { FaSearch, FaPlus, FaFolder } from 'react-icons/fa';
import '../style/TableContent.css';

const TableContent = ({role}) => {
  const { tableId } = useParams();
  const { state } = useLocation();
  const navigate = useNavigate();
  const isAdmin = role === 'admin';

  const tableName = state?.tableName || "Неизвестная таблица";
  const initialContent = state?.content || [];

  const [tableData, setTableData] = useState(initialContent);

  const [editingRow, setEditingRow] = useState(null);
  const [editData, setEditData] = useState({});

  const [selectedRows, setSelectedRows] = useState([]);

  const handleEdit = (row) => {
    setEditingRow(row.id);
    setEditData({ ...row });
  };

  const handleSave = (rowId) => {
    const updatedData = tableData.map((row) =>
      row.id === rowId ? { ...row, ...editData } : row
    );
    setTableData(updatedData);
    setEditingRow(null);
  };

  const handleCancel = () => {
    setEditingRow(null);
  };

  const handleInputChange = (e, field) => {
    setEditData({ ...editData, [field]: e.target.value });
  };

  const handleRowSelect = (rowId) => {
    if (selectedRows.includes(rowId)) {
      setSelectedRows(selectedRows.filter((id) => id !== rowId));
    } else {
      setSelectedRows([...selectedRows, rowId]);
    }
  };

  const handleSelectAll = () => {
    if (selectedRows.length === tableData.length) {
      setSelectedRows([]);
    } else {
      setSelectedRows(tableData.map((row) => row.id));
    }
  };

  const handleBack = () => {
    navigate('/digest-book');
  };

  return (
    <div className="table-content-container">
      <h1>Адресная книга: «{tableName}»</h1>
      <div className="table-actions">
        <button className="action-button"> <FaSearch /> Поиск абонента</button>
        <button className="action-button" disabled={!isAdmin}> <FaPlus /> Добавить абонента</button>
        <button className="action-button" disabled={!isAdmin}> <FaPlus /> Добавить категорию</button>
        <button className="action-button" disabled={!isAdmin}> <FaFolder /> Добавить администратора</button>
        <button className="action-button" disabled={!isAdmin}>Редактировать</button>
        <button className="action-button" disabled={!isAdmin}>Удалить</button>
      </div>
      <div className="table-container">
        <table className="page-table">
          <thead>
            <tr>
              <th>
                <input
                  type="checkbox"
                  checked={selectedRows.length === tableData.length && tableData.length > 0}
                  onChange={handleSelectAll}
                />
              </th>
              <th>ФИО</th>
              <th>Псевдоним</th>
              <th>Категория Рождения</th>
              <th>Дата Рождения</th>
              <th>Пол</th>
              <th>Номер телефона</th>
              <th>Ссылки на социальные сети</th>
              <th>Электронная почта</th>
              <th>Адрес прописки</th>
              <th>Адрес работы/учёбы</th>
              <th>Комментарии</th>
            </tr>
          </thead>
          <tbody>
            {tableData.length > 0 ? (
              tableData.map((row) => (
                <tr key={row.id}>
                  <td>
                    <input
                      type="checkbox"
                      checked={selectedRows.includes(row.id)}
                      onChange={() => handleRowSelect(row.id)}
                    />
                  </td>
                  {editingRow === row.id ? (
                    <>
                      <td>
                        <input
                          type="text"
                          value={editData.fullName || ""}
                          onChange={(e) => handleInputChange(e, "fullName")}
                        />
                      </td>
                      <td>
                        <input
                          type="text"
                          value={editData.nickname || ""}
                          onChange={(e) => handleInputChange(e, "nickname")}
                        />
                      </td>
                      <td>
                        <input
                          type="text"
                          value={editData.birthCategory || ""}
                          onChange={(e) => handleInputChange(e, "birthCategory")}
                        />
                      </td>
                      <td>
                        <input
                          type="text"
                          value={editData.birthDate || ""}
                          onChange={(e) => handleInputChange(e, "birthDate")}
                        />
                      </td>
                      <td>
                        <input
                          type="text"
                          value={editData.gender || ""}
                          onChange={(e) => handleInputChange(e, "gender")}
                        />
                      </td>
                      <td>
                        <input
                          type="text"
                          value={editData.phone || ""}
                          onChange={(e) => handleInputChange(e, "phone")}
                        />
                      </td>
                      <td>
                        <input
                          type="text"
                          value={editData.socialLinks || ""}
                          onChange={(e) => handleInputChange(e, "socialLinks")}
                        />
                      </td>
                      <td>
                        <input
                          type="text"
                          value={editData.email || ""}
                          onChange={(e) => handleInputChange(e, "email")}
                        />
                      </td>
                      <td>
                        <input
                          type="text"
                          value={editData.address || ""}
                          onChange={(e) => handleInputChange(e, "address")}
                        />
                      </td>
                      <td>
                        <input
                          type="text"
                          value={editData.workStudyAddress || ""}
                          onChange={(e) => handleInputChange(e, "workStudyAddress")}
                        />
                      </td>
                      <td>
                        <input
                          type="text"
                          value={editData.comments || ""}
                          onChange={(e) => handleInputChange(e, "comments")}
                        />
                      </td>
                    </>
                  ) : (
                    <>
                      <td>{row.fullName}</td>
                      <td>{row.nickname}</td>
                      <td>{row.birthCategory}</td>
                      <td>{row.birthDate}</td>
                      <td>{row.gender}</td>
                      <td>{row.phone}</td>
                      <td>{row.socialLinks}</td>
                      <td>{row.email}</td>
                      <td>{row.address}</td>
                      <td>{row.workStudyAddress}</td>
                      <td>
                        {row.comments}
                        {editingRow !== row.id && (
                          <button
                            className="edit-button"
                            onClick={() => handleEdit(row)}
                          >
                            ✎
                          </button>
                        )}
                      </td>
                    </>
                  )}
                </tr>
              ))
            ) : (
              <tr>
                <td colSpan={12}>Нет данных для отображения</td>
              </tr>
            )}
            {editingRow && (
              <tr>
                <td colSpan={12} className="edit-actions">
                  <button onClick={() => handleSave(editingRow)}>Сохранить</button>
                  <button onClick={handleCancel}>Отмена</button>
                </td>
              </tr>
            )}
          </tbody>
        </table>
      </div>
      <div className="table-actions">
      <button className="action-button search-button" onClick={handleBack}>
          <span>←</span> Назад
        </button>
      </div>
    </div>
  );
};

export default TableContent;