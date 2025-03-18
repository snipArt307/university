package model;

public class TAbonent {
    private TRecord record;

    public TAbonent(TRecord record) {
        this.record = record;
    }

    public TRecord readRecord() {
        return record;
    }

    public void writeRecord(TRecord record) {
        this.record = record;
    }

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

