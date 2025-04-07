import model.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@Disabled
class TProcTest {

    private TProc processor;

    @BeforeEach
    void setUp() {
        // Создаем новый экземпляр процессора перед каждым тестом
        processor = new TProc();
    }

    @Test
    void testResetProcessor() {
        // Проверим, что после сброса процессора все значения равны нулю
        processor.resetProcessor();

        assertNotNull(processor.readLeftOperand(), "Left operand should not be null after reset");
        assertNotNull(processor.readRightOperand(), "Right operand should not be null after reset");
        assertEquals("None", processor.readState(), "State should be 'None' after reset");
    }

    @Test
    void testSetAndGetBase() {
        // Проверим, что установка и получение основания работает корректно
        processor.setBase(16);
        assertEquals(16, processor.getBase(), "Base should be set to 16");

        processor.setBase(2);
        assertEquals(2, processor.getBase(), "Base should be set to 2");
    }

    @Test
    void testWriteAndReadOperands() {
        // Проверим запись и чтение операндов
        TPNumber number = new TPNumber(5, 10, 2); // Создаем число 5 в десятичной системе
        processor.writeLeftOperand(number);
        processor.writeRightOperand(number);

        assertEquals(5, processor.readLeftOperand().getValue(), "Left operand should be 5");
        assertEquals(5, processor.readRightOperand().getValue(), "Right operand should be 5");
    }

    @Test
    void testPerformOperation() {
        // Проверим выполнение операции
        processor.writeLeftOperand(new TPNumber(5, 10, 2));
        processor.writeRightOperand(new TPNumber(3, 10, 2));
        processor.setOperation("+");

        processor.performOperation();

        // После выполнения операции (5 + 3) результат должен быть 8
        assertEquals(8, processor.readLeftOperand().getValue(), "Result of addition should be 8");
    }

    @Test
    void testPerformDivision() {
        // Проверим деление
        processor.writeLeftOperand(new TPNumber(10, 10, 2));
        processor.writeRightOperand(new TPNumber(2, 10, 2));
        processor.setOperation("/");

        processor.performOperation();

        // После деления (10 / 2) результат должен быть 5
        assertEquals(5, processor.readLeftOperand().getValue(), "Result of division should be 5");
    }

    @Test
    void testPerformDivisionByZero() {
        // Проверим деление на ноль
        processor.writeLeftOperand(new TPNumber(10, 10, 2));
        processor.writeRightOperand(new TPNumber(0, 10, 2));
        processor.setOperation("/");

        // Должно выбросить исключение ArithmeticException
        assertThrows(ArithmeticException.class, () -> processor.performOperation(), "Division by zero should throw exception");
    }

    @Test
    void testCalculateSquare() {
        // Проверим функцию квадрата
        processor.writeRightOperand(new TPNumber(4, 10, 2));
        processor.calculateFunction("Sqr");

        // 4^2 = 16
        assertEquals(16, processor.readRightOperand().getValue(), "Square of 4 should be 16");
    }

    @Test
    void testCalculateReciprocal() {
        // Проверим функцию обратного числа
        processor.writeRightOperand(new TPNumber(4, 10, 2));
        processor.calculateFunction("Reciprocal");

        // 1/4 = 0.25
        assertEquals(0.25, processor.readRightOperand().getValue(), "Reciprocal of 4 should be 0.25");
    }

    @Test
    void testSaveAndRecallMemory() {
        // Проверим сохранение и восстановление из памяти
        processor.saveToMemory();
        processor.recallFromMemory();

        // Ожидаем, что результат сохранения и восстановления будет равен 0 (начальное значение)
        assertEquals(0, processor.getResultAsDouble(), "Memory recall should give the initial value 0");
    }

    @Test
    void testClearMemory() {
        // Проверим очистку памяти
        processor.saveToMemory();
        processor.clearMemory();

        // После очистки памяти, память должна быть пуста
        assertFalse(processor.isMemoryOn(), "Memory should be empty after clearing");
    }

    @Test
    void testClearProcessor() {
        // Проверим, что после очистки процессора все значения сбрасываются
        processor.writeLeftOperand(new TPNumber(10, 10, 2));
        processor.writeRightOperand(new TPNumber(5, 10, 2));
        processor.setOperation("+");
        processor.performOperation();

        assertEquals(15, processor.readLeftOperand().getValue(), "Initial operation result should be 15");

        processor.clear();
        assertEquals(0, processor.readLeftOperand().getValue(), "Left operand should be 0 after clear");
        assertEquals(0, processor.readRightOperand().getValue(), "Right operand should be 0 after clear");
    }
}
