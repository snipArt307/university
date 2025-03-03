package bean;

public class Convector {

    public static char intToChar(int x) {
        return (char) (x + '0');
    }

    public static String int_to_P(String number, int base, int newBase) {
        if (base == newBase)
            return number;

        // Проверка на основание системы счисления, равное 1
        if (base == 1 || newBase == 1) {
            throw new IllegalArgumentException("Основание системы счисления не может быть равно 1.");
        }

        return Long.toString(Long.parseLong(number, base), newBase);
    }

    public static String flt_to_P(String number, int base, int newBase) {
        if (base == newBase)
            return number;

        // Проверка на основание системы счисления, равное 1
        if (base == 1 || newBase == 1) {
            throw new IllegalArgumentException("Основание системы счисления не может быть равно 1.");
        }

        // Преобразование дробной части
        String[] parts = number.split("\\.");
        String integerPart = int_to_P(parts[0], base, newBase);
        StringBuilder fractionalPart = new StringBuilder(".");

        double fraction = Integer.parseInt(parts[1], base) / Math.pow(base, parts[1].length());
        while (fraction > 0) {
            fraction *= newBase;
            int digit = (int) fraction;
            fractionalPart.append(Integer.toString(digit, newBase));
            fraction -= digit;
        }

        return integerPart + fractionalPart;
    }

}
