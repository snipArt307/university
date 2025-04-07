package controller;

import model.FileHelper;
import model.MainModel;
import model.Model;
import view.ErrorHelper;
import view.View;
import view.error.AddRecordEmptyError;
import view.error.BufferEmptyError;
import view.error.RecordExistError;

import javax.swing.*;
import java.io.IOException;

public class Controller {
    private View view;
    private Model model;
    private String[] buff = new String[2];  // Буфер для хранения данных о выбранной записи

    public Controller(View view) {
        this.view = view;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    // Очистка всей книги
    public void clearBook() {
        try {
            model.clearBook();
            updateTheOutputWindow();
        } catch (Exception e) {
            ErrorHelper.getError("Ошибка при очистке книги!");
        }
    }

    // Сохранение книги
    public void saveBook() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            try {
                FileHelper.writeToFile(model.getBook(), fileChooser.getSelectedFile());
            } catch (IOException e) {
                ErrorHelper.getError("Ошибка при записи в файл!");
            } catch (Exception e) {
                ErrorHelper.getError("Неизвестная ошибка при сохранении файла!");
            }
        }
    }

    // Загрузка книги
    public void loadBook() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            try {
                model.setBook(FileHelper.readFromFile(fileChooser.getSelectedFile()));
                updateTheOutputWindow();
            } catch (IOException e) {
                ErrorHelper.getError("Ошибка при чтении файла!");
            } catch (ClassNotFoundException e) {
                ErrorHelper.getError("В файле хранится не то, что нужно!");
            } catch (Exception e) {
                ErrorHelper.getError("Неизвестная ошибка при загрузке файла!");
            }
        }
    }

    // Удаление записи
    public void removeRecord() {
        String initials = view.getInitialsField();
        String phone = view.getPhoneField();
        try {
            if (initials.isEmpty() || phone.isEmpty()) throw new AddRecordEmptyError();
            model.removeRecord(initials, phone);
            updateTheOutputWindow();
        } catch (AddRecordEmptyError e) {
            ErrorHelper.getError("Запись не может быть удалена: данные отсутствуют.");
        } catch (Exception e) {
            ErrorHelper.getError("Ошибка при удалении записи!");
        }
    }

    // Добавление записи
    public void addRecord() {
        String initials = view.getInitialsField();
        String phone = view.getPhoneField();
        try {
            if (initials.isEmpty() || phone.isEmpty()) throw new AddRecordEmptyError();
            if (model.findRecord(initials, phone) != -1) throw new RecordExistError();

            model.addRecord(initials, phone);
            updateTheOutputWindow();
        } catch (AddRecordEmptyError | RecordExistError e) {
            ErrorHelper.getError(e.getMessage());
        } catch (Exception e) {
            ErrorHelper.getError("Ошибка при добавлении записи!");
        }
    }

    // Поиск записи
    public void findRecord() {
        String initials = view.getInitialsField();
        String phone = view.getPhoneField();
        try {
            if (initials.isEmpty() || phone.isEmpty()) throw new AddRecordEmptyError();
            int index = model.findRecord(initials, phone);
            if (index == -1) {
                ErrorHelper.getError("Запись не найдена!");
            } else {
                view.setHighlightLine(index, model.getBook().toString().split("\n"));
                buff[0] = initials;
                buff[1] = phone;
            }
        } catch (AddRecordEmptyError e) {
            ErrorHelper.getError(e.getMessage());
        } catch (Exception e) {
            ErrorHelper.getError("Ошибка при поиске записи!");
        }
    }

    // Изменение записи
    public void changeRecord() {
        String initials = view.getInitialsField();
        String phone = view.getPhoneField();
        try {
            if (initials.isEmpty() || phone.isEmpty()) throw new AddRecordEmptyError();
            if (buff[0].isEmpty() || buff[1].isEmpty()) throw new BufferEmptyError();

            model.resetRecord(buff[0], buff[1], initials, phone);
            updateTheOutputWindow();
        } catch (AddRecordEmptyError | BufferEmptyError e) {
            ErrorHelper.getError(e.getMessage());
        } catch (Exception e) {
            ErrorHelper.getError("Ошибка при изменении записи!");
        }
    }

    // Обновление окна вывода
    private void updateTheOutputWindow() {
        view.setjTextPane(model.toString());
        buff[0] = null;
        buff[1] = null;
    }
}
