package view;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame {
    private Controller controller;

    private JPanel tabbedPane = new JPanel();
    private JPanel panelContent = new JPanel();
    private JPanel panelRecord = new JPanel();
    private JTextPane jTextPane = new JTextPane();
    private JTextField initialsField = new JTextField();
    private JTextField phoneField = new JTextField();

    public View() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void init() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 400);

        initGui();

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void initGui() {
        initMainMenu();
        initMenuBar();
        initRecordingMenu();
    }

    public void initMainMenu() {
        getContentPane().add(tabbedPane);
        tabbedPane.setLayout(new BoxLayout(tabbedPane, BoxLayout.Y_AXIS));
        jTextPane.setEditable(false);
    }

    public void initMenuBar() {
        panelContent.setLayout(new BorderLayout());
        tabbedPane.add(panelContent);

        panelContent.add(MenuHelper.initEastPanel(controller), BorderLayout.EAST);

        panelContent.add(MenuHelper.addButton("Загрузить", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.loadBook();
            }
        }), BorderLayout.SOUTH);

        panelContent.add(jTextPane, BorderLayout.CENTER);

    }

    public void initRecordingMenu() {
        panelRecord.setLayout(new BoxLayout(panelRecord, BoxLayout.X_AXIS));
        tabbedPane.add(panelRecord);

        panelRecord.add(MenuHelper.initTextFieldForRecordingMenu(controller, initialsField, "ФИО"));
        panelRecord.add(MenuHelper.initTextFieldForRecordingMenu(controller, phoneField, "Номер"));

        panelRecord.add(MenuHelper.initButtonForRecordingMenu(controller));
    }

    public void setjTextPane(String text) {
        jTextPane.setText(text);
    }

    public String getInitialsField(){
        return initialsField.getText();
    }

    public String getPhoneField(){
        return phoneField.getText();
    }

    public void setHighlightLine(int line, String[] strings) {
        strings[line] = "----------------> " + strings[line];
        jTextPane.setText(String.join("\n", strings));
    }
}
