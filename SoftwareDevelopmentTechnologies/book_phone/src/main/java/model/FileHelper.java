package model;

import java.io.*;

public class FileHelper {

    public static void writeToFile(TObjectList objectList, File file) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(objectList);
        }
    }

    public static TObjectList readFromFile(File file) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (TObjectList) ois.readObject();
        }
    }
}
