package org.example;

import model.TRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TRecordTest {

    private TRecord record;

    @BeforeEach
    void setUp() {
        // Создаем новый объект TRecord перед каждым тестом
        record = new TRecord("John Doe", "123-456-7890");
    }

    @Test
    void testConstructor() {
        // Проверяем, что конструктор правильно инициализирует объект
        assertNotNull(record, "TRecord object should not be null");
        assertEquals("John Doe", record.getName(), "Name should be 'John Doe'");
        assertEquals("123-456-7890", record.getPhoneNumber(), "Phone number should be '123-456-7890'");
    }

    @Test
    void testGettersAndSetters() {
        // Проверяем работу геттеров и сеттеров
        record.setName("Jane Doe");
        record.setPhoneNumber("098-765-4321");

        assertEquals("Jane Doe", record.getName(), "Name should be 'Jane Doe' after setting");
        assertEquals("098-765-4321", record.getPhoneNumber(), "Phone number should be '098-765-4321' after setting");
    }

    @Test
    void testEqualsSameObject() {
        // Проверяем, что объект равен самому себе
        TRecord sameRecord = record;
        assertTrue(record.equals(sameRecord), "Object should be equal to itself");
    }

    @Test
    void testEqualsDifferentObjectSameValues() {
        // Проверяем, что два объекта с одинаковыми значениями равны
        TRecord anotherRecord = new TRecord("John Doe", "123-456-7890");
        assertTrue(record.equals(anotherRecord), "Objects with the same values should be equal");
    }

    @Test
    void testEqualsDifferentName() {
        // Проверяем, что объекты с разными именами не равны
        TRecord anotherRecord = new TRecord("Jane Doe", "123-456-7890");
        assertFalse(record.equals(anotherRecord), "Objects with different names should not be equal");
    }

    @Test
    void testEqualsDifferentPhoneNumber() {
        // Проверяем, что объекты с разными номерами телефонов не равны
        TRecord anotherRecord = new TRecord("John Doe", "098-765-4321");
        assertFalse(record.equals(anotherRecord), "Objects with different phone numbers should not be equal");
    }

    @Test
    void testHashCode() {
        // Проверяем, что hashCode для одинаковых объектов одинаков
        TRecord anotherRecord = new TRecord("John Doe", "123-456-7890");
        assertEquals(record.hashCode(), anotherRecord.hashCode(), "Hashcodes should be the same for equal objects");
    }

    @Test
    void testToString() {
        // Проверяем, что метод toString возвращает правильную строку
        String expected = "Имя: John Doe \t т-ф: 123-456-7890";
        assertEquals(expected, record.toString(), "toString should return a string with correct name and phone number");
    }
}
