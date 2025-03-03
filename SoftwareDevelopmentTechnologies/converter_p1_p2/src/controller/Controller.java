package controller;

import bean.Record;
import model.Model;
import view.UserView;

public class Controller {
    private Model model;
    private UserView userView;

    public void setModel(Model model) {
        this.model = model;
    }

    public void setUserView(UserView userView) {
        this.userView = userView;
    }

    public String convertNumber(String number, int base, int newBase) {
        Record newRecord = new Record(number, base, newBase);
        try {
              model.convertNumberToNewBase(newRecord);
        }catch (NumberFormatException e) {
            userView.getErrorIncorrectNumberInput();
        }
        return newRecord.getNumber();
    }
}
