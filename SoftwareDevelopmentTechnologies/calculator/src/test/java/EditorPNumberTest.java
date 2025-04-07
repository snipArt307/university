import static org.junit.jupiter.api.Assertions.*;

import model.EditorPNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EditorPNumberTest {

    private EditorPNumber editorPNumber;

    // Метод для инициализации объекта перед каждым тестом
    @BeforeEach
    public void setUp() {
        editorPNumber = new EditorPNumber(10); // Базовое основание 10 (десятичная система)
    }

    // Тестирование конструктора с правильным основанием
    @Test
    public void testConstructorValidBase() {
        assertEquals(10, editorPNumber.getBase()); // Проверка установленного основания
        assertEquals("0", editorPNumber.getNumber()); // Изначально число равно 0
    }

    // Тестирование конструктора с неправильным основанием
    @Test
    public void testConstructorInvalidBase() {
        assertThrows(IllegalArgumentException.class, () -> new EditorPNumber(1)); // Основание меньше 2
        assertThrows(IllegalArgumentException.class, () -> new EditorPNumber(17)); // Основание больше 16
    }

    // Тестирование метода addSign()
    @Test
    public void testAddSign() {
        editorPNumber.addSign(); // Изменяем знак
        assertEquals("-0", editorPNumber.getNumber());

        editorPNumber.addSign(); // Снова изменяем знак
        assertEquals("0", editorPNumber.getNumber());
    }

    // Тестирование метода addPDigit()
    @Test
    public void testAddPDigit() {
        editorPNumber.addPDigit(5); // Добавляем цифру 5
        assertEquals("5", editorPNumber.getNumber());

        editorPNumber.addPDigit(3); // Добавляем цифру 3
        assertEquals("53", editorPNumber.getNumber());
    }

    // Тестирование метода addPDigit() с некорректной цифрой
    @Test
    public void testAddPDigitInvalid() {
        assertThrows(IllegalArgumentException.class, () -> editorPNumber.addPDigit(10)); // Цифра вне диапазона для 10-ой системы счисления
    }

    // Тестирование метода addZero()
    @Test
    public void testAddZero() {
        editorPNumber.addZero(); // Добавляем ноль
        assertEquals("0", editorPNumber.getNumber());

        editorPNumber.addPDigit(5); // Добавляем цифру 5
        editorPNumber.addZero(); // Добавляем ноль
        assertEquals("50", editorPNumber.getNumber());
    }

    // Тестирование метода deleteLastSymbol()
    @Test
    public void testDeleteLastSymbol() {
        editorPNumber.addPDigit(5); // Добавляем цифру 5
        editorPNumber.addPDigit(3); // Добавляем цифру 3
        editorPNumber.deleteLastSymbol(); // Удаляем последний символ
        assertEquals("5", editorPNumber.getNumber());

        editorPNumber.deleteLastSymbol(); // Еще раз удаляем
        assertEquals("0", editorPNumber.getNumber());
    }

    // Тестирование метода clear()
    @Test
    public void testClear() {
        editorPNumber.addPDigit(5); // Добавляем цифру 5
        editorPNumber.clear(); // Очищаем число
        assertEquals("0", editorPNumber.getNumber());
        assertTrue(editorPNumber.isZero()); // Проверка, что число стало 0
    }

    // Тестирование метода setNumberFromString()
    @Test
    public void testSetNumberFromString() {
        editorPNumber.setNumberFromString("123"); // Устанавливаем число через строку
        assertEquals("123", editorPNumber.getNumber());
    }

    // Тестирование метода setBase()
    @Test
    public void testSetBaseValid() {
        editorPNumber.setBase(16); // Меняем основание на 16
        assertEquals(16, editorPNumber.getBase());
    }

    // Тестирование метода setBase() с некорректным основанием
    @Test
    public void testSetBaseInvalid() {
        assertThrows(IllegalArgumentException.class, () -> editorPNumber.setBase(1)); // Основание меньше 2
        assertThrows(IllegalArgumentException.class, () -> editorPNumber.setBase(17)); // Основание больше 16
    }

    // Тестирование метода isZero()
    @Test
    public void testIsZero() {
        assertTrue(editorPNumber.isZero()); // Изначально число равно 0

        editorPNumber.addPDigit(5); // Добавляем цифру 5
        assertFalse(editorPNumber.isZero()); // Число больше нуля
    }
}
