import model.TPNumber;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TPNumberTest {

    private TPNumber number;

    // Метод для инициализации объекта перед каждым тестом
    @BeforeEach
    public void setUp() {
        number = new TPNumber(10, 2, 2); // Число в десятичной системе, точность 2
    }

    // Тестирование метода add (сложение)
    @Test
    public void testAdd() {
        TPNumber num1 = new TPNumber(10, 10, 2); // 10 в десятичной системе
        TPNumber num2 = new TPNumber(20, 10, 2); // 20 в десятичной системе
        TPNumber result = num1.add(num2);
        assertEquals(30.0, result.getValue(), 0.001); // Ожидаем 30 в десятичной системе
    }

    // Тестирование метода add с разными основаниями
    @Test
    public void testAddDifferentBases() {
        TPNumber num1 = new TPNumber(10, 10, 2); // 10 в десятичной системе
        TPNumber num2 = new TPNumber(10, 2, 2); // 10 в двоичной системе
        assertThrows(IllegalArgumentException.class, () -> num1.add(num2)); // Ошибка при разных основаниях
    }

    // Тестирование метода multiply (умножение)
    @Test
    public void testMultiply() {
        TPNumber num1 = new TPNumber(2, 10, 2); // 2 в десятичной системе
        TPNumber num2 = new TPNumber(3, 10, 2); // 3 в десятичной системе
        TPNumber result = num1.multiply(num2);
        assertEquals(6.0, result.getValue(), 0.001); // Ожидаем 6 в десятичной системе
    }

    // Тестирование метода divide (деление)
    @Test
    public void testDivide() {
        TPNumber num1 = new TPNumber(10, 10, 2); // 10 в десятичной системе
        TPNumber num2 = new TPNumber(2, 10, 2); // 2 в десятичной системе
        TPNumber result = num1.divide(num2);
        assertEquals(5.0, result.getValue(), 0.001); // Ожидаем 5 в десятичной системе
    }

    // Тестирование деления на ноль
    @Test
    public void testDivideByZero() {
        TPNumber num1 = new TPNumber(10, 10, 2); // 10 в десятичной системе
        TPNumber num2 = new TPNumber(0, 10, 2); // 0 в десятичной системе
        assertThrows(ArithmeticException.class, () -> num1.divide(num2)); // Ошибка деления на ноль
    }

    // Тестирование метода subtract (вычитание)
    @Test
    public void testSubtract() {
        TPNumber num1 = new TPNumber(10, 10, 2); // 10 в десятичной системе
        TPNumber num2 = new TPNumber(3, 10, 2); // 3 в десятичной системе
        TPNumber result = num1.subtract(num2);
        assertEquals(7.0, result.getValue(), 0.001); // Ожидаем 7 в десятичной системе
    }

    // Тестирование метода reciprocal (обратное число)
    @Test
    public void testReciprocal() {
        TPNumber num = new TPNumber(2, 10, 2); // 2 в десятичной системе
        TPNumber result = num.reciprocal();
        assertEquals(0.5, result.getValue(), 0.001); // Ожидаем 0.5 в десятичной системе
    }

    // Тестирование обратного числа для нуля
    @Test
    public void testReciprocalZero() {
        TPNumber num = new TPNumber(0, 10, 2); // 0 в десятичной системе
        assertThrows(ArithmeticException.class, () -> num.reciprocal()); // Ошибка для обратного числа от нуля
    }

    // Тестирование метода square (квадрат)
    @Test
    public void testSquare() {
        TPNumber num = new TPNumber(3, 10, 2); // 3 в десятичной системе
        TPNumber result = num.square();
        assertEquals(9.0, result.getValue(), 0.001); // Ожидаем 9 в десятичной системе
    }

    // Тестирование изменения точности
    @Test
    public void testSetPrecision() {
        TPNumber num = new TPNumber(10.75, 10, 2); // 10.75 в десятичной системе, точность 2
        num.setPrecision(3);
        assertEquals(3, num.getPrecision()); // Проверка изменения точности
    }

    // Тестирование изменения основания
    @Test
    public void testSetBase() {
        TPNumber num = new TPNumber(10, 10, 2); // 10 в десятичной системе
        num.setBase(2);
        assertEquals(2, num.getBase()); // Проверка изменения основания
    }
}
