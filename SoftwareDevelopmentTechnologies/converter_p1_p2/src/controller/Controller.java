package controller;

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
        String result = "";
        try {
             result = model.convertNumberToNewBase(number, base, newBase);
        }catch (NumberFormatException e) {
            userView.getErrorIncorrectNumberInput();
        }
        return result;
    }
}
