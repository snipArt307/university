package model;

import bean.History;
import bean.Record;

public interface Model {
    History getHistory();

    void convertNumberToNewBase(Record record);
}
