package controller;

import model.*;
import view.CalculatorView;

public class CalculatorController {
    private TProc model;  // Модель калькулятора
    private CalculatorView view;  // Представление

    private boolean isFirstOperation = true;  // Флаг для отслеживания первой операции
    private boolean isResultDisplayed = false;  // Флаг для отслеживания результата операции

    // Конструктор
    public CalculatorController(TProc model, CalculatorView view) {
        this.model = model;
        this.view = view;
    }

    // Обработчик числовой кнопки
    public void processNumberButton(String number) {
        String displayText = view.getDisplayText();

        // Если это первый ввод числа, просто заменяем экранный текст
        if (displayText.equals("0") || isResultDisplayed) {
            displayText = number;
            isResultDisplayed = false;
        } else {
            displayText += number;
        }

        // Обновляем экран
        view.updateDisplay(displayText);
    }

    // Обработчик операции (+, -, *, /)
    public void processOperationButton(String operation) {
        // Если операция = и это не первая операция, то выполняем операцию с последним результатом
        if (operation.equals("=")) {
            if (isFirstOperation) {
                // Если операция первая, то записываем новый правый операнд
                model.setResult(new TPNumber(model.readCurrentNumber().getValue(), model.readCurrentNumber().getBase(), model.readCurrentNumber().getPrecision()));
                model.performOperation();
                view.updateDisplay(model.getResultAsString());
                return;
            }

            model.setResult(new TPNumber(Double.parseDouble(view.getDisplayText()), model.getBase(), 2));
            // Если результат был уже рассчитан, то повторно применяем операцию
            model.performOperation();

            view.updateDisplay(model.getResultAsString());
        } else {
            // Если результат не был раньше, записываем первый операнд в currentNumber
            if (isFirstOperation)
                model.setLastResult(new TPNumber(model.getResult().getValue(), model.getResult().getBase(), model.getResult().getPrecision()));

            
            model.writeCurrentNumber(new TPNumber(Double.parseDouble(view.getDisplayText()), model.getBase(), 2));


            // Устанавливаем операцию
            model.setOperation(operation);

            // Обновляем экран
            view.updateDisplay("");
            isFirstOperation = false;  // Операция теперь не первая
        }
    }

    // Обработчик операции с одним операндом (например, Sqr, Reciprocal)
    public void processFunctionButton(String function) {
        String displayText = view.getDisplayText();
        TPNumber operand = new TPNumber(Double.parseDouble(displayText), model.getBase(), 2);
        model.writeCurrentNumber(operand);

        // Вычисление функции
        model.calculateFunction(function);

        // Отображаем результат
        view.updateDisplay(model.getResultAsString());
        isFirstOperation = true;
    }

    // Повторное выполнение последней операции
    public void repeatLastOperation() {
        if (lastResult != null) {
            // Повторяем последнюю операцию с предыдущим результатом
            model.performOperation();

            // Обновляем экран
            view.updateDisplay(model.getResultAsString());
        }
    }

    // Обработчик сохранения в память
    public void saveResultToMemory() {
        model.saveToMemory();
    }

    // Обработчик восстановления из памяти
    public void recallResultFromMemory() {
        model.recallFromMemory();
        view.updateDisplay(model.getResultAsString());
    }

    // Обработчик очистки процессора
    public void clearProcessor() {
        model.clear();
        view.updateDisplay("0");
    }

    // Обработчик изменения основания системы счисления
    public void changeBase(int base) {
        model.setBase(base);
        view.updateDisplay(model.getResultAsString());
    }

    // Получение результата как строки
    public String getResultAsString() {
        return model.getResultAsString();
    }

    // Обработчик очистки памяти
    public void clearMemory() {
        model.clearMemory();
    }

    // Проверка состояния памяти
    public boolean isMemoryOn() {
        return model.isMemoryOn();
    }

    // Обработчик функции квадратного корня
    public void calculateSquare() {
        model.square();
        view.updateDisplay(model.getResultAsString());
    }

    // Обработчик функции обратного числа
    public void calculateReciprocal() {
        model.reciprocal();
        view.updateDisplay(model.getResultAsString());
    }

    // Метод для добавления текущего числа в память
    public void addToMemory() {
        model.addToMemory();
    }
}
