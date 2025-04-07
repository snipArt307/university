package view.error;

public class RecordExistError extends Exception {

    public RecordExistError() {
        super("Данная запись уже существует!");
    }
}
