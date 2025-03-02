package model;

import bean.History;

public interface Model {
    History getHistory();

    String convertNumberToNewBase(String number, int base, int newBase);
}
