package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TObjectList implements Serializable {
    private List<TAbonent> abonents;

    public List<TAbonent> getAbonents() {
        return abonents;
    }

    public TObjectList() {
        abonents = new ArrayList<>();
    }

    public TAbonent readRecord(int index) {
        return abonents.get(index);
    }

    public int countRecords() {
        return abonents.size();
    }

    public void addRecord(TAbonent abonent) {
        abonents.add(abonent);
    }

    public int findRecord(TAbonent abonent) {
        for (int i = 0; i < abonents.size(); i++) {
            if (abonents.get(i).equals(abonent)) {
                return i;
            }
        }
        return -1;
    }

    public void removeRecord(TAbonent abonent) {
        abonents.remove(abonent);
    }

    public void removeHighlightedEntryRecord(int index) {
        abonents.remove(index);
    }

    public void clearBook() {
        abonents.clear();
    }

    @Override
    public String toString() {
        return abonents.stream().map(TAbonent::toString)
                .collect(Collectors.joining("\n"));
    }
}
