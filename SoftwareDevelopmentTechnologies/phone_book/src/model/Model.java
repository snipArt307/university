package model;

public interface Model {

    int recordCountsInBook();

    String readRecord(int id);

    String readRecordName(int id);

    String readRecordPhoneNumber(int id);

    void addRecord(String name, String phoneNumber);

    void removeRecord(String name, String phoneNumber);


}
