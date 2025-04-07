package org.example;

import model.TAbonent;
import model.TObjectList;
import model.TRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TObjectListTest {

    private TObjectList objectList;
    private TAbonent abonent1;
    private TAbonent abonent2;

    @BeforeEach
    void setUp() {
        // Создаем новый объект TObjectList перед каждым тестом
        objectList = new TObjectList();
        abonent1 = new TAbonent(new TRecord("John Doe", "123-456-7890"));
        abonent2 = new TAbonent(new TRecord("Jane Doe", "098-765-4321"));

        // Добавляем абонентов в список
        objectList.addRecord(abonent1);
        objectList.addRecord(abonent2);
    }

    @Test
    void testAddRecord() {
        // Проверяем, что запись была добавлена
        assertEquals(2, objectList.countRecords(), "The record count should be 2 after adding two records");
    }

    @Test
    void testReadRecord() {
        // Проверяем, что чтение записи по индексу работает правильно
        TAbonent readAbonent = objectList.readRecord(0);
        assertEquals(abonent1, readAbonent, "The first abonent should be equal to abonent1");

        readAbonent = objectList.readRecord(1);
        assertEquals(abonent2, readAbonent, "The second abonent should be equal to abonent2");
    }

    @Test
    void testCountRecords() {
        // Проверяем, что количество записей правильно возвращается
        assertEquals(2, objectList.countRecords(), "The record count should be 2");
    }

    @Test
    void testFindRecord() {
        // Проверяем, что метод findRecord находит правильный индекс
        int index = objectList.findRecord(abonent1);
        assertEquals(0, index, "The index of abonent1 should be 0");

        index = objectList.findRecord(abonent2);
        assertEquals(1, index, "The index of abonent2 should be 1");

        // Проверяем, что метод findRecord возвращает -1, если запись не найдена
        TAbonent abonent3 = new TAbonent(new TRecord("Non Existent", "000-000-0000"));
        index = objectList.findRecord(abonent3);
        assertEquals(-1, index, "The index of a non-existent abonent should be -1");
    }

    @Test
    void testRemoveRecord() {
        // Проверяем, что удаление записи работает корректно
        objectList.removeRecord(abonent1);
        assertEquals(1, objectList.countRecords(), "The record count should be 1 after removing one record");

        // Проверяем, что удаленная запись больше не существует в списке
        int index = objectList.findRecord(abonent1);
        assertEquals(-1, index, "abonent1 should no longer be found in the list");
    }

    @Test
    void testRemoveHighlightedEntryRecord() {
        // Удаление записи по индексу
        objectList.removeHighlightedEntryRecord(0);
        assertEquals(1, objectList.countRecords(), "The record count should be 1 after removing one record by index");

        // Проверяем, что запись с индексом 0 была удалена
        TAbonent readAbonent = objectList.readRecord(0);
        assertEquals(abonent2, readAbonent, "The first record after removal should be abonent2");
    }

    @Test
    void testClearBook() {
        // Проверяем, что метод очистки работает корректно
        objectList.clearBook();
        assertEquals(0, objectList.countRecords(), "The record count should be 0 after clearing the list");
    }

    @Test
    void testToString() {
        // Проверяем метод toString
        String expected = "Имя: John Doe \t т-ф: 123-456-7890\nИмя: Jane Doe \t т-ф: 098-765-4321";
        assertEquals(expected, objectList.toString(), "toString should return the correct string representation of the object list");
    }
}
