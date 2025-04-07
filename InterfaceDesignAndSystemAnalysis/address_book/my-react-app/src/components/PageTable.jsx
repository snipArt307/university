import React from "react";
import { useNavigate } from "react-router-dom";

const PageTable = ({ pages }) => {
  const navigate = useNavigate();

  const tableContents = {
    1: [
      {
        id: 1,
        fullName: "Иван Иванов",
        nickname: "ivan123",
        birthCategory: "Взрослый",
        birthDate: "01.01.1990",
        gender: "Мужской",
        phone: "+7 (123) 456-78-90",
        socialLinks: "vk.com/ivan",
        email: "ivan@example.com",
        address: "г. Москва, ул. Ленина, д. 1",
        workStudyAddress: "ООО Бухгалтерия",
        comments: "Главный бухгалтер",
      },
      {
        id: 2,
        fullName: "Мария Петрова",
        nickname: "masha",
        birthCategory: "Взрослый",
        birthDate: "15.05.1985",
        gender: "Женский",
        phone: "+7 (987) 654-32-10",
        socialLinks: "instagram.com/masha",
        email: "masha@example.com",
        address: "г. Москва, ул. Пушкина, д. 2",
        workStudyAddress: "ООО Бухгалтерия",
        comments: "Ассистент",
      },
    ],
    2: [
      {
        id: 1,
        fullName: "Алексей Смирнов",
        nickname: "alex",
        birthCategory: "Взрослый",
        birthDate: "20.03.1980",
        gender: "Мужской",
        phone: "+7 (111) 222-33-44",
        socialLinks: "facebook.com/alex",
        email: "alex@example.com",
        address: "г. Москва, ул. Гагарина, д. 3",
        workStudyAddress: "Администрация",
        comments: "Администратор",
      },
    ],
    3: [
      {
        id: 1,
        fullName: "Сергей Попов",
        nickname: "serg",
        birthCategory: "Взрослый",
        birthDate: "10.07.1995",
        gender: "Мужской",
        phone: "+7 (555) 666-77-88",
        socialLinks: "twitter.com/serg",
        email: "serg@example.com",
        address: "г. Москва, ул. Чехова, д. 4",
        workStudyAddress: "IT-отдел",
        comments: "Разработчик",
      },
    ],
    4: [
      {
        id: 1,
        fullName: "Ольга Сидорова",
        nickname: "olga",
        birthCategory: "Взрослый",
        birthDate: "25.12.1992",
        gender: "Женский",
        phone: "+7 (999) 000-11-22",
        socialLinks: "linkedin.com/olga",
        email: "olga@example.com",
        address: "г. Москва, ул. Толстого, д. 5",
        workStudyAddress: "Куча",
        comments: "Менеджер",
      },
    ],
  };

  const handleTableClick = (tableId, tableName) => {
    const content = tableContents[tableId] || [];
    navigate(`/table-content/${tableId}`, {
      state: { tableName, content },
    });
  };

  return (
    <div className="table-container">
      <table className="address-table">
        <thead>
          <tr>
            <th>Название адресной книги</th>
            <th>Права</th>
          </tr>
        </thead>
        <tbody>
          {pages?.map((item) => (
            <tr key={item.id}>
              <td
                onClick={() => handleTableClick(item.id, item.page)}
                style={{ cursor: "pointer", color: "#3498db" }}
              >
                {item.page || "Нет данных"}
              </td>
              <td>{item.rights || "Нет данных"}</td>
            </tr>
          )) || <tr><td colSpan={2}>Нет данных для отображения</td></tr>}
        </tbody>
      </table>
    </div>
  );
};

export default PageTable;