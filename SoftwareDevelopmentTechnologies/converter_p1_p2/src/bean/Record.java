package bean;

public class Record {
    String lastNumber;
    String number;
    int base;
    int newBase;

    public Record() {
        lastNumber = "";
        number = "";
        base = 0;
        newBase = 0;
    }

    public Record(String lastNumber, int base, int newBase) {
        this.lastNumber = lastNumber;
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

    public void setLastNumber(String lastNumber) {
        this.lastNumber = lastNumber;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setBase(int base) {
        this.base = base;
    }

    public void setNewBase(int newBase) {
        this.newBase = newBase;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s", lastNumber, number, base, newBase);
    }
}
