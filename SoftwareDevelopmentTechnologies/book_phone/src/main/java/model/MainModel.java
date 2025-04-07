package model;

public class MainModel implements Model {
    private TObjectList book = new TObjectList();

    @Override
    public TObjectList getBook() {
        return book;
    }

    @Override
    public void setBook(TObjectList book) {
        this.book = book;
    }

    @Override
    public int findRecord(String name, String phoneNumber) {
        return book.findRecord(new TAbonent(new TRecord(name , phoneNumber)));
    }

    @Override
    public int recordCountsInBook() {
        return book.countRecords();
    }

    @Override
    public String readRecord(int id) {
        return book.readRecord(id).toString();
    }

    @Override
    public String readRecordName(int id) {
        return book.readRecord(id).toString().split(" ")[1];
    }

    @Override
    public String readRecordPhoneNumber(int id) {
        return book.readRecord(id).toString().split(" ")[3];
    }

    @Override
    public void addRecord(String name, String phoneNumber) {
        book.addRecord(new TAbonent(new TRecord(name, phoneNumber)));
    }

    @Override
    public void removeRecord(String name, String phoneNumber) {
        TAbonent rmAbonent = new TAbonent(new TRecord(name, phoneNumber));
        book.removeRecord(rmAbonent);
    }

    @Override
    public void removeHighlightedRecord(int id) {
        book.removeHighlightedEntryRecord(id);
    }

    @Override
    public void clearBook() {
        book.clearBook();
    }

    @Override
    public void resetRecord(String lastInitials, String lastPhone, String newInitials, String newPhone) {
        TAbonent rmAbonent = new TAbonent(new TRecord(lastInitials, lastPhone));
        for (TAbonent abonent : book.getAbonents()) {
            if (abonent.equals(rmAbonent)) {
                abonent.writeRecord(new TRecord(newInitials, newPhone));
            }
        }
    }

    @Override
    public String toString() {
        return book.toString();
    }
}
