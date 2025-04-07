package model;

import java.io.IOException;

public interface Model {

    void setBook(TObjectList book);

    int recordCountsInBook();

    String readRecord(int id);

    String readRecordName(int id);

    String readRecordPhoneNumber(int id);

    void addRecord(String name, String phoneNumber);

    void removeRecord(String name, String phoneNumber);

    void removeHighlightedRecord(int id);

    void clearBook();

    int findRecord(String name, String phoneNumber);

    void resetRecord(String lastInitials, String lastPhone, String newInitials, String newPhone);

    TObjectList getBook();
}
