package bean;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class History {
    List<Record> history;

    public History() {
        history = new ArrayList<>();
    }

    public void addEntry(Record record) {
        this.history.add(record);
    }

    public void clearHistory() {
        this.history.clear();
    }

    public int getSizeHistory() {
        return history.size();
    }

    public String getLineForNumber(int number) {
        return (history.get(number)).toString();
    }

    @Override
    public String toString() {
        return history.stream()
                .map(Record::toString)
                .collect(Collectors.joining("\n"));
    }
}
