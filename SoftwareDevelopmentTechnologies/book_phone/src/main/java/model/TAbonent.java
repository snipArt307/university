package model; // пакет модель

import java.io.Serializable; // включаем сериализацию (те может сохранить класс целиков, а не отдельные данные)

public class TAbonent implements Serializable { // объявляем публичный класс, который доступен во всём проекте
    private TRecord record; //Объявляем приватную переменную(доступна только внутри данного класса)

    public TAbonent(TRecord record) { // конструктор класса
        this.record = record; // this - помогает отличить переменные с одинаковым названием (переменная данного класса от внешней переменной)
    }

    public TRecord readRecord() { // функция чтения. возвращает переменную класса TRecord (как геттер)
        return record;
    }

    public void writeRecord(TRecord record) { // (сеттер)
        this.record = record;
    }

    /**
     * @author Artemka
     * @date 26.03.25
     * @time 15:23
     * публичный класс, возвращающий
     * */
    public int less(TRecord record) {
        return this.record.getPhoneNumber().compareTo(record.getPhoneNumber());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) return false;
        TAbonent other = (TAbonent) obj;
        return this.record.equals(other.record);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return record.toString();
    }
}

