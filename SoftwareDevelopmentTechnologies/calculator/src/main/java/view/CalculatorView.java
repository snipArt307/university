package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.CalculatorController;

public class CalculatorView extends JFrame {
    private JTextField display;  // Поле для отображения чисел и результатов
    private JButton[] numButtons;  // Кнопки для чисел
    private JButton[] operationButtons;  // Кнопки для операций
    private JButton backSpaceButton, clearButton, ceButton, memoryClearButton, memoryRecallButton, memorySaveButton, memoryAddButton, changeSignButton;
    private JComboBox<String> baseComboBox;  // Для выбора основания системы счисления
    private JLabel baseLabel, baseValueLabel;  // Для отображения метки для ползунка

    private JSlider baseSlider;  // Ползунок для выбора основания

    private CalculatorController controller;  // Контроллер для управления моделью

    public void setController(CalculatorController controller) {
        this.controller = controller;
    }

    // Конструктор для инициализации интерфейса
    public CalculatorView(CalculatorController controller) {
        this.controller = controller;

        setTitle("Калькулятор р-ичных чисел");
        setSize(400, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Создаем панель меню
        createMenuBar();

        // Отображение результата
        display = new JTextField("0");
        display.setFont(new Font("Arial", Font.PLAIN, 24));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        // Панель кнопок
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 4));

        numButtons = new JButton[16]; // Для чисел от 0 до F (16)
        for (int i = 0; i < 16; i++) {
            numButtons[i] = new JButton(Integer.toHexString(i).toUpperCase());
            numButtons[i].addActionListener(new NumberButtonListener());
            buttonPanel.add(numButtons[i]);
        }

        // Кнопки операций
        String[] operations = {"+", "-", "*", "/", "Sqr", "1/x", "+/-", "CE", "C", "BackSpace", "MC", "MR", "MS", "M+"};
        operationButtons = new JButton[operations.length];

        for (int i = 0; i < operations.length; i++) {
            operationButtons[i] = new JButton(operations[i]);
            operationButtons[i].addActionListener(new OperationButtonListener());
            buttonPanel.add(operationButtons[i]);
        }

        // Панель для выбора основания с ползунком
        baseLabel = new JLabel("Основание");
        baseSlider = new JSlider(JSlider.HORIZONTAL, 2, 16, 10);  // Диапазон от 2 до 16, начальное значение 10 (десятичная система)
        baseSlider.setMajorTickSpacing(2);  // Большие деления через 2
        baseSlider.setMinorTickSpacing(1);  // Меньшие деления через 1
        baseSlider.setPaintTicks(true);  // Отображение делений
        baseSlider.setPaintLabels(true);  // Отображение меток

        baseValueLabel = new JLabel("10");  // Для отображения текущего выбранного значения основания
        baseSlider.addChangeListener(e -> {
            int base = baseSlider.getValue();
            baseValueLabel.setText(String.valueOf(base));
            controller.changeBase(base);
        });

        // Панель для ползунка и метки основания
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());
        bottomPanel.add(baseLabel);
        bottomPanel.add(baseSlider);
        bottomPanel.add(baseValueLabel);

        // Добавляем панель с кнопками и панель основания
        add(buttonPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    // Метод для создания панели меню
    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // Создание меню "Файл"
        JMenu fileMenu = new JMenu("Файл");
        JMenuItem newItem = new JMenuItem("Новый");
        JMenuItem openItem = new JMenuItem("Открыть");
        JMenuItem saveItem = new JMenuItem("Сохранить");
        JMenuItem exitItem = new JMenuItem("Выход");
        exitItem.addActionListener(e -> System.exit(0));

        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        // Создание меню "Правка"
        JMenu editMenu = new JMenu("Правка");
        JMenuItem copyItem = new JMenuItem("Копировать");
        JMenuItem pasteItem = new JMenuItem("Вставить");

        editMenu.add(copyItem);
        editMenu.add(pasteItem);

        // Добавляем меню в панель меню
        menuBar.add(fileMenu);
        menuBar.add(editMenu);

        // Устанавливаем панель меню
        setJMenuBar(menuBar);
    }

    // Метод для обновления дисплея
    public void updateDisplay(String text) {
        display.setText(text);
    }

    // Листенеры для числовых кнопок
    private class NumberButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            controller.processNumberButton(source.getText());
        }
    }

    // Листенеры для кнопок операций
    private class OperationButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            String buttonText = source.getText();
            switch (buttonText) {
                case "MC":
                    controller.clearMemory();
                    break;
                case "MR":
                    controller.recallResultFromMemory();
                    break;
                case "MS":
                    controller.saveResultToMemory();
                    break;
                case "M+":
                    controller.addToMemory();
                    break;
                default:
                    controller.processOperationButton(buttonText);
                    break;
            }
        }
    }

    // Метод для получения текущего текста дисплея
    public String getDisplayText() {
        return display.getText();
    }

    // Метод для получения выбранного основания
    public int getSelectedBase() {
        return baseSlider.getValue();  // Получаем значение с ползунка
    }
}
