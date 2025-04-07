package view.error;

public class AddRecordEmptyError extends Exception {

    public AddRecordEmptyError() {
        super("Заполните данные для добавления!");
    }
}
