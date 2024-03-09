import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        File fileWithNumbers = new File("10m.txt");
        List<Integer> numbersFromFileList = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileWithNumbers))) {
            String currentLine = bufferedReader.readLine();
            int currentNumber;
            while (currentLine != null) {
                try {
                    currentNumber = Integer.parseInt(currentLine.trim());
                    numbersFromFileList.add(currentNumber);
                } catch (NumberFormatException e) {
                    throw new RuntimeException("Can`t parse String to Integer", e);
                }
                currentLine = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Some problems with file", e);
        }
    }
}
