package model;

import java.util.Objects;

public class TRecord {
    private String name;
    private String phoneNumber;

    public TRecord(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TRecord tRecord = (TRecord) o;
        return Objects.equals(phoneNumber, tRecord.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(phoneNumber);
    }

    @Override
    public String toString() {
        return String.format("Имя: %s\tт-ф: %s", name, phoneNumber);
    }
}
