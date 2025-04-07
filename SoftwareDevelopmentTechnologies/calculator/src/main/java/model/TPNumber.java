package model;

public class TPNumber {
    private double value; // Числовое значение
    private int base; // Основание системы счисления (от 2 до 16)
    private int precision; // Точность (количество цифр после запятой)

    // Конструктор, принимающий вещественное число, основание и точность
    public TPNumber(double value, int base, int precision) {
        if (base < 2 || base > 16) {
            throw new IllegalArgumentException("Основание должно быть в диапазоне от 2 до 16");
        }
        this.value = value;
        this.base = base;
        this.precision = precision;
    }

    // Конструктор, принимающий строковое представление числа в системе счисления
    public TPNumber(String strValue, int base, int precision) {
        this.base = base;
        this.precision = precision;
        this.value = convertToDecimal(strValue, base);
    }

    // Преобразует строковое значение числа в десятичное по заданному основанию
    private double convertToDecimal(String strValue, int base) {
        String[] parts = strValue.split("\\.");
        int integerPart = Integer.parseInt(parts[0], base);
        double fractionalPart = 0;

        if (parts.length > 1) {
            String fractionalStr = parts[1];
            for (int i = 0; i < fractionalStr.length(); i++) {
                char digitChar = fractionalStr.charAt(i);
                int digitValue = Character.digit(digitChar, base);
                fractionalPart += digitValue * Math.pow(base, -(i + 1));
            }
        }

        return integerPart + fractionalPart;
    }

    // Преобразует десятичное значение обратно в строковое представление в заданном основании
    public String convertToBase(int base) {
        int integerPart = (int) value;
        double fractionalPart = value - integerPart;

        StringBuilder result = new StringBuilder();

        // Преобразование целой части
        result.append(Integer.toString(integerPart, base));

        // Преобразование дробной части
        if (precision > 0) {
            result.append(".");
            for (int i = 0; i < precision; i++) {
                fractionalPart *= base;
                int fractionalDigit = (int) fractionalPart;
                result.append(Integer.toString(fractionalDigit, base));
                fractionalPart -= fractionalDigit;
            }
        }

        return result.toString();
    }

    // Сложение двух р-ичных чисел
    public TPNumber add(TPNumber other) {
        if (this.base != other.base) {
            throw new IllegalArgumentException("Операции возможны только с числами в одинаковых системах счисления");
        }
        double resultValue = this.value + other.value;
        return new TPNumber(resultValue, this.base, this.precision);
    }

    // Умножение двух р-ичных чисел
    public TPNumber multiply(TPNumber other) {
        if (this.base != other.base) {
            throw new IllegalArgumentException("Операции возможны только с числами в одинаковых системах счисления");
        }
        double resultValue = this.value * other.value;
        return new TPNumber(resultValue, this.base, this.precision);
    }

    // Деление двух р-ичных чисел
    public TPNumber divide(TPNumber other) {
        if (this.base != other.base) {
            throw new IllegalArgumentException("Операции возможны только с числами в одинаковых системах счисления");
        }
        if (other.value == 0) {
            throw new ArithmeticException("Деление на ноль");
        }
        double resultValue = this.value / other.value;
        return new TPNumber(resultValue, this.base, this.precision);
    }

    // Вычитание двух р-ичных чисел
    public TPNumber subtract(TPNumber other) {
        if (this.base != other.base) {
            throw new IllegalArgumentException("Операции возможны только с числами в одинаковых системах счисления");
        }
        double resultValue = this.value - other.value;
        return new TPNumber(resultValue, this.base, this.precision);
    }

    // Обратное число
    public TPNumber reciprocal() {
        if (this.value == 0) {
            throw new ArithmeticException("Обратное число для нуля не существует");
        }
        double resultValue = 1 / this.value;
        return new TPNumber(resultValue, this.base, this.precision);
    }

    // Квадрат числа
    public TPNumber square() {
        double resultValue = this.value * this.value;
        return new TPNumber(resultValue, this.base, this.precision);
    }

    // Получение значения числа в десятичном формате
    public double getValue() {
        return value;
    }

    // Получение строкового представления числа в р-ичной системе
    public String getStringRepresentation() {
        return convertToBase(base);
    }

    // Метод для установки нового значения числа
    public void setValue(double value) {
        this.value = value;
    }

    // Метод для получения точности
    public int getPrecision() {
        return precision;
    }

    // Метод для установки точности
    public void setPrecision(int precision) {
        this.precision = precision;
    }

    // Метод для получения основания
    public int getBase() {
        return base;
    }

    // Метод для установки основания
    public void setBase(int base) {
        this.base = base;
    }
}

