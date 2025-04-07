package model;

public class TProc {
    private TMemory memory; // Память калькулятора
    private TPNumber result; // Результат выполнения операций
    private TPNumber lastResult;  // Последний результат для повторного использования
    private TPNumber currentNumber; // Текущее число
    private String operation; // Операция (+, -, *, /)
    private int base; // Основание системы счисления (от 2 до 16)

    // Конструктор
    public TProc() {
        this.memory = new TMemory();
        this.result = new TPNumber(0, 10, 2); // Изначально результат равен 0
        this.currentNumber = new TPNumber(0, 10, 2); // Изначально текущее число равно 0
        this.operation = "None"; // Операция не установлена
        this.base = 10; // Изначально десятичная система
    }

    public TPNumber getLastResult() {
        return lastResult;
    }

    public void setLastResult(TPNumber lastResult) {
        this.lastResult = lastResult;
    }

    public void setResult(TPNumber result) {
        this.result = result;
    }

    // Сброс процессора
    public void resetProcessor() {
        currentNumber = new TPNumber(0, 10, 2);
        result = new TPNumber(0, 10, 2);
        operation = "None";
    }

    // Сброс операции
    public void resetOperation() {
        operation = "None";
    }

    // Выполнение операции
    public void performOperation() {
        switch (operation) {
            case "+":
                result = result.add(currentNumber);
                break;
            case "-":
                result = result.subtract(currentNumber);
                break;
            case "*":
                result = result.multiply(currentNumber);
                break;
            case "/":
                if (result.getValue() != 0) {
                    result = result.divide(currentNumber);
                } else {
                    throw new ArithmeticException("Деление на ноль");
                }
                break;
            default:
                // Если операция не установлена, ничего не делаем
                break;
        }
    }

    // Выполнение функции
    public void calculateFunction(String func) {
        switch (func) {
            case "Sqr": // Например, вычисление квадрата
                result = result.square();
                break;
            case "Reciprocal": // Обратное число
                result = result.reciprocal();
                break;
            default:
                throw new IllegalArgumentException("Неизвестная функция");
        }
    }

    // Чтение левого операнда
    public TPNumber readCurrentNumber() {
        return currentNumber;
    }

    // Запись левого операнда
    public void writeCurrentNumber(TPNumber operand) {
        currentNumber = new TPNumber(operand.getValue(), operand.getBase(), operand.getPrecision());
    }

    // Чтение состояния
    public String readState() {
        return operation;
    }

    // Запись состояния
    public void writeState(String oprtn) {
        operation = oprtn;
    }

    // Установка операции
    public void setOperation(String operation) {
        this.operation = operation;
    }

    // Получение текущего результата
    public TPNumber getResult() {
        return result;
    }

    // Очистка состояния процессора
    public void clear() {
        result = new TPNumber(0, 10, 2);
        currentNumber = new TPNumber(0, 10, 2);
        operation = "None";
    }

    // Сохранение результата в память
    public void saveToMemory() {
        memory.saveToMemory(result);
    }

    // Восстановление результата из памяти
    public void recallFromMemory() {
        try {
            result = memory.recallMemory();
        } catch (IllegalStateException e) {
            System.out.println("Память пуста");
        }
    }

    // Вывод текущего результата как строку
    public String getResultAsString() {
        return result.getStringRepresentation();
    }

    // Метод для добавления текущего числа к памяти
    public void addToMemory() {
        memory.addToMemory(currentNumber);
    }

    // Метод для очистки памяти
    public void clearMemory() {
        memory.clearMemory();
    }

    // Метод для получения текущего значения в памяти
    public String getMemoryAsString() {
        return memory.getMemoryAsString();
    }

    // Метод для получения состояния памяти (включена или нет)
    public boolean isMemoryOn() {
        return memory.isMemoryOn();
    }

    // Метод для выполнения квадратного корня
    public void square() {
        result = result.square();
    }

    // Метод для получения обратного числа
    public void reciprocal() {
        result = result.reciprocal();
    }

    // Метод для установки точности результата
    public void setPrecision(int precision) {
        result.setPrecision(precision);
    }

    // Метод для получения результата как вещественного значения
    public double getResultAsDouble() {
        return result.getValue();
    }

    // Получить текущее основание
    public int getBase() {
        return base;
    }

    // Установить основание системы счисления
    public void setBase(int base) {
        this.base = base;
        // При изменении основания также обновляем число в нужном формате
        this.result.setBase(base);
        this.currentNumber.setBase(base);
    }
}