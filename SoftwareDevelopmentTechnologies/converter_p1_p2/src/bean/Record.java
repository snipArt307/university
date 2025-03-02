package bean;

public class Record {
    String lastNumber;
    String number;
    int base;
    int newBase;

    public Record(String lastNumber, String number, int base, int newBase) {
        this.lastNumber = lastNumber;
        this.number = number;
        this.base = base;
        this.newBase = newBase;
    }

    public String getLastNumber() {
        return lastNumber;
    }

    public String getNumber() {
        return number;
    }

    public int getBase() {
        return base;
    }

    public int getNewBase() {
        return newBase;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s", lastNumber, number, base, newBase);
    }
}
