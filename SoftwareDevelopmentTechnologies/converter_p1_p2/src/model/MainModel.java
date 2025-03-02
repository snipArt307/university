package model;

import bean.Convector;
import bean.History;
import bean.Record;

public class MainModel implements Model {

    private History history;

    public MainModel() {
        history = new History();
    }

    @Override
    public History getHistory() {
        return history;
    }

    @Override
    public String convertNumberToNewBase(String number, int base, int newBase) {
        String newNumber;
        if (!number.contains(".")) {
            newNumber = Convector.intToAnyNumberSystem(number, base, newBase);
        } else {
            newNumber = Convector.doubleToAnyNumberSystem(number, base, newBase);
        }
        history.addEntry(new Record(number, newNumber, base, newBase));
        return newNumber.toUpperCase();
    }
}
