package model;

import java.io.Serializable;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TRecord implements Serializable {
    private String name;
    private String phoneNumber;

    // Конструктор с проверкой на валидность
    public TRecord(String name, String phoneNumber) {
        setName(name); // Используем сеттер для проверки
        setPhoneNumber(phoneNumber); // Используем сеттер для проверки
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (!name.matches("^[a-zA-Zа-яА-ЯёЁ\\s]+$")) { // Проверка на буквы и пробелы
            throw new IllegalArgumentException("Name can only contain letters and spaces");
        }
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Phone number cannot be empty");
        }

        // Проверка номера телефона (например, формат 123-456-7890 или 123 456 7890)
        Pattern pattern = Pattern.compile("^(\\+?\\d{1,4})?[-\\s]?\\(?\\d{1,4}\\)?[-\\s]?\\d{1,4}[-\\s]?\\d{1,4}$");
        Matcher matcher = pattern.matcher(phoneNumber);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid phone number format");
        }

        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TRecord tRecord = (TRecord) o;
        return phoneNumber.equals(tRecord.phoneNumber) && Objects.equals(name, tRecord.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneNumber);
    }

    @Override
    public String toString() {
        return String.format("Имя: %s \t т-ф: %s", name, phoneNumber);
    }
}
