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
            newNumber = Convector.int_to_P(record.getLastNumber(), record.getBase(), record.getNewBase());
        } else {
            newNumber = Convector.flt_to_P(record.getLastNumber(), record.getBase(), record.getNewBase());
        }
        newNumber = newNumber.toUpperCase();
        record.setNumber(newNumber);
        history.addEntry(record);
    }
}
