package org.example;

import controller.CalculatorController;
import model.TProc;
import view.CalculatorView;

public class Main {
    public static void main(String[] args) {
        // Создаем модель
        TProc model = new TProc();

        // Создаем представление
        CalculatorView view = new CalculatorView(null);  // Временно передаем null

        // Создаем контроллер и передаем ему модель и представление
        CalculatorController controller = new CalculatorController(model, view);

        // Обновляем представление, теперь контроллер будет работать с ним
        view.setController(controller);

        // Устанавливаем видимость окна калькулятора
        view.setVisible(true);
    }
}
