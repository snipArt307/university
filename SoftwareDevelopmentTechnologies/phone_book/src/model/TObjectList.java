package model;

import java.util.ArrayList;
import java.util.List;

public class TObjectList {
    private List<TAbonent> abonents;

    public TObjectList() {
        abonents = new ArrayList<>();
    }

    public TAbonent get(int index) {
        return abonents.get(index);
    }

    public void add(TAbonent abonent) {
        abonents.add(abonent);
    }

    public int length() {
        return abonents.size();
    }

    public int find(TAbonent abonent) {
        for (int i = 0; i < abonents.size(); i++) {
            if (abonents.get(i).equals(abonent)) {
                return i;
            }
        }
        return -1;
    }

    public void remove(TAbonent abonent) {
        abonents.remove(abonent);
    }

    public void removeHighlightedEntry(int index) {
        abonents.remove(index);
    }

    public void clear() {
        abonents.clear();
    }


}
