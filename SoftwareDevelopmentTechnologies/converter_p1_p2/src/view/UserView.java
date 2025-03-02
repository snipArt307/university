package view;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UserView extends JFrame implements View {
    private StringBuilder number;
    private String newNumber;
    private int base;
    private int newBase;

    private Controller controller;

    private JTextField areaP1, areaP2;
    private JLabel labelP1, labelP2, areaNumber, areaNewNumber;
    private JPanel calculatorPanel, panelMain;
    private JButton[] buttons = new JButton[20];
    private JSlider slider1, slider2;

    public UserView() {
        initView();
    }

    private void initView() {
        newBase = base = 10;
        number = new StringBuilder();
        //creating the main window
        this.setTitle("User View");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 400);

        //creating the elements
        areaP1 = new JTextField();
        areaP2 = new JTextField();
        areaNumber = new JLabel("Исходное число: ");
        areaNewNumber = new JLabel("Результат: ");
        slider1 = new JSlider(2, 16);
        slider2 = new JSlider(2, 16);
        labelP1 = new JLabel("Основание с.сч. исходного числа - 10");
        labelP2 = new JLabel("Основание с.сч. результата - 10");

        // Set text alignment
        labelP1.setHorizontalAlignment(SwingConstants.LEFT);
        labelP2.setHorizontalAlignment(SwingConstants.LEFT);

        Dimension dimAreaP = new Dimension(100, 40);
        areaP1.setPreferredSize(dimAreaP);
        areaP2.setPreferredSize(dimAreaP);
        areaNumber.setPreferredSize(dimAreaP);
        areaNewNumber.setPreferredSize(dimAreaP);

        //creating the base skeleton
        panelMain = new JPanel();
        panelMain.setLayout(new BoxLayout(panelMain, BoxLayout.Y_AXIS));
        this.add(panelMain);

        //creating first slider
        panelMain.add(areaP1);
        panelMain.add(labelP1);
        panelMain.add(slider1);
        slider1.setValue(10);
        slider1.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                jSlider1MouseDragged(e);
            }
        });

        //creating second slider
        panelMain.add(areaP2);
        panelMain.add(labelP2);
        panelMain.add(slider2);
        slider2.setValue(10);
        slider2.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                jSlider2MouseDragged(e);
            }
        });

        panelMain.add(areaNumber);
        panelMain.add(areaNewNumber);

        //creating to calculate panel
        calculatorPanel = new JPanel();
        calculatorPanel.setLayout(new GridLayout(5, 4));
        panelMain.add(calculatorPanel);

        buttons[16] = new JButton(".");
        buttons[17] = new JButton("BS");
        buttons[18] = new JButton("CE");
        buttons[19] = new JButton("Execute");
        for (int i = 0; i < 20; i++) {
            if (i < 16) buttons[i] = new JButton(Integer.toHexString(i).toUpperCase());
            calculatorPanel.add(buttons[i]);
        }

        for (JButton button : buttons) {
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    jButtonActionPerformed(e);
                }
            });
        }

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void updateNewAreaNumber() {
        areaNumber.setText("Исходное число: " + number.toString());
        areaNewNumber.setText("Результат: " + newNumber);
        newNumber = "";
    }

    private void updateAreaNumber() {
        areaNumber.setText("Исходное число: " + number.toString());
    }

    private void jSlider1MouseDragged(MouseEvent e) {
        base = slider1.getValue();
        labelP1.setText("Основание с.сч. исходного числа - " + base);
    }


    private void jSlider2MouseDragged(MouseEvent e) {
        newBase = slider2.getValue();
        labelP2.setText("Основание с.сч. результата - " + newBase);
    }

    private void jButtonActionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();

        switch (button.getText()) {
            case "BS":
                number.deleteCharAt(number.length() - 1);
                break;
            case "CE":
                number.delete(0, number.length());
                break;
            case "Execute":
                newNumber = controller.convertNumber(number.toString(), base, newBase);
                updateNewAreaNumber();
                break;
            default:
                number.append(button.getText());
        }
        updateAreaNumber();
    }

    public void getErrorIncorrectNumberInput() {
        String errorMessage = "Произошла ошибка! Неверный ввод.";
        number.delete(0, number.length());
        updateAreaNumber();
        JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }


    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

}
