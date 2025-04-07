package view.error;

public class BufferEmptyError extends Exception {

    public BufferEmptyError() {
        super("Вы не выбрали запись!");
    }
}
