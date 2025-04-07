package view;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuHelper {

    public static JButton addButton(JPanel parent, String text, ActionListener listener) {
        JButton button = new JButton(text);
        button.addActionListener(listener);
        parent.add(button);
        return button;
    }

    public static JButton addButton(String text, ActionListener listener) {
        JButton button = new JButton(text);
        button.addActionListener(listener);
        return button;
    }

    public static JPanel initEastPanel(Controller controller) {
        JPanel eastPanel = new JPanel();
        eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.Y_AXIS));
        addButton(eastPanel, "Очистить", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.clearBook();
            }
        });
        addButton(eastPanel, "Сохранить", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.saveBook();
            }
        });
        addButton(eastPanel, "Удалить", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.removeRecord();
            }
        });
        addButton(eastPanel, "Изменить", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.changeRecord();
            }
        });
        return eastPanel;
    }

    public static JPanel initButtonForRecordingMenu(Controller controller) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        addButton(buttonPanel, "Добавить", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.addRecord();
            };
        });
        addButton(buttonPanel, "Найти", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.findRecord();
            }
        });
        return buttonPanel;
    }

    public static JPanel initTextFieldForRecordingMenu(Controller controller, JTextField textField, String text) {
        JPanel textFieldPanel = new JPanel();
        textFieldPanel.setLayout(new BoxLayout(textFieldPanel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel();
        label.setText(text);
        textFieldPanel.add(label);

        textFieldPanel.add(textField);

        Dimension dim = new Dimension(300, 10);
        textField.setPreferredSize(dim);

        return textFieldPanel;
    }
}
