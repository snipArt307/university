package model;

public class EditorPNumber {
    private String number; // Строковое представление р-ичного числа
    private boolean isZero; // Флаг, указывающий, является ли число нулевым

    private int base; // Основание системы счисления (от 2 до 16)

    // Конструктор
    public EditorPNumber(int base) {
        if (base < 2 || base > 16) {
            throw new IllegalArgumentException("Основание системы счисления должно быть от 2 до 16");
        }
        this.base = base;
        this.number = "0"; // Изначально число равно 0
        this.isZero = true;
    }

    // Метод для добавления знака (положительный или отрицательный)
    public void addSign() {
        if (number.charAt(0) == '-') {
            number = number.substring(1); // Убираем знак "-"
        } else {
            number = "-" + number; // Добавляем знак "-"
        }
        updateZeroFlag();
    }

    // Метод для добавления р-ичной цифры
    public void addPDigit(int digit) {
        if (digit < 0 || digit >= base) {
            throw new IllegalArgumentException("Цифра должна быть в пределах от 0 до " + (base - 1));
        }
        if (isZero) {
            number = String.valueOf(digit); // Если число было нулем, заменяем его на цифру
            isZero = false;
        } else {
            number += String.valueOf(digit); // Добавляем цифру в конец строки
        }
    }

    // Метод для добавления нуля
    public void addZero() {
        if (isZero) {
            number = "0"; // Если число ноль, оставляем его как есть
        } else {
            number += "0"; // Иначе добавляем ноль в конец числа
        }
    }

    // Метод для удаления крайнего символа
    public void deleteLastSymbol() {
        if (number.length() > 1) {
            number = number.substring(0, number.length() - 1); // Удаляем последний символ
        } else {
            number = "0"; // Если осталось одно число, оно становится 0
            isZero = true;
        }
    }

    // Метод для очистки числа
    public void clear() {
        number = "0"; // Устанавливаем число в 0
        isZero = true;
    }

    // Метод для обновления флага, если число равно нулю
    private void updateZeroFlag() {
        if (number.equals("0") || number.equals("-0")) {
            isZero = true;
        } else {
            isZero = false;
        }
    }

    // Метод для получения текущего состояния числа в строковом формате
    public String getNumber() {
        return number;
    }

    // Метод для чтения строки и установки р-ичного числа
    public void setNumberFromString(String str) {
        number = str;
        updateZeroFlag();
    }

    // Метод для записи р-ичного числа в строковом формате
    public String getNumberAsString() {
        return number;
    }

    // Метод для получения состояния флага "число равно нулю"
    public boolean isZero() {
        return isZero;
    }

    // Метод для получения основания системы счисления
    public int getBase() {
        return base;
    }

    // Метод для установки нового основания системы счисления
    public void setBase(int base) {
        if (base < 2 || base > 16) {
            throw new IllegalArgumentException("Основание должно быть в пределах от 2 до 16");
        }
        this.base = base;
    }
}
