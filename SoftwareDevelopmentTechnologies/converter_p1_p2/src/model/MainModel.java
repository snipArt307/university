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
    public void convertNumberToNewBase(Record record) {
        String newNumber;
        if (!record.getLastNumber().contains(".")) {
            newNumber = Convector.intToAnyNumberSystem(record.getLastNumber(), record.getBase(), record.getNewBase());
        } else {
            newNumber = Convector.doubleToAnyNumberSystem(record.getLastNumber(), record.getBase(), record.getNewBase());
        }
        record.setNumber(newNumber);
        history.addEntry(record);
    }
}
