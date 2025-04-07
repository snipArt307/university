package org.example;

import controller.Controller;
import model.MainModel;
import model.Model;
import view.View;

public class App 
{
    public static void main(String[] args) {
        View view = new View();
        Controller controller = new Controller(view);
        Model model = new MainModel();

        controller.setModel(model);
        view.setController(controller);
        view.init();
    }
}
