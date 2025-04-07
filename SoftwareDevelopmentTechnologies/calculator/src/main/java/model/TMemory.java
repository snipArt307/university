package model;

public class TMemory {
    private TPNumber memory; // Число, хранящееся в памяти
    private boolean isMemoryOn; // Флаг для отслеживания состояния памяти

    // Конструктор
    public TMemory() {
        this.memory = new TPNumber(0, 10, 2); // Изначально память пуста (0 в десятичной системе с точностью 2 знака)
        this.isMemoryOn = false;
    }

    // Метод для очистки памяти
    public void clearMemory() {
        memory = new TPNumber(0, 10, 2); // Сбрасываем память на 0
        isMemoryOn = false; // Память выключена
    }

    // Метод для сохранения текущего числа в память
    public void saveToMemory(TPNumber number) {
        memory = new TPNumber(number.getValue(), number.getBase(), number.getPrecision());
        isMemoryOn = true; // Память теперь содержит значение
    }

    // Метод для вызова числа из памяти
    public TPNumber recallMemory() {
        if (isMemoryOn) {
            return memory;
        } else {
            throw new IllegalStateException("Память пуста");
        }
    }

    // Метод для добавления текущего числа к значению в памяти
    public void addToMemory(TPNumber number) {
        if (isMemoryOn) {
            TPNumber temp = memory.add(number); // Добавляем числа
            memory = temp; // Обновляем память
        } else {
            saveToMemory(number); // Если память пуста, просто сохраняем число
        }
    }

    // Метод для проверки, есть ли значение в памяти
    public boolean isMemoryOn() {
        return isMemoryOn;
    }

    // Метод для получения текущего значения памяти в строковом формате
    public String getMemoryAsString() {
        return memory.getStringRepresentation();
    }
}

