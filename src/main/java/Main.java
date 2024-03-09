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
        long sumOfNumbers = 0;
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
                sumOfNumbers += currentNumber;
                currentLine = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Some problems with file", e);
        }
        int amountOfNumbers = numbersFromFileList.size();
        double arithmeticAverage = (double)sumOfNumbers / amountOfNumbers;
        List<Integer> greatestSequenceOfNumbers = findGreatestSequenceOfNumbers(numbersFromFileList);
        List<Integer> smallestSequenceOfNumbers = findSmallestDecreasingSequence(numbersFromFileList);
        numbersFromFileList.sort(null);
        int minNum = numbersFromFileList.get(0);
        int maxNum = numbersFromFileList.get(amountOfNumbers - 1);
        double median = findMedian(numbersFromFileList, amountOfNumbers);
        System.out.println("Максимальне значення: " + maxNum);
        System.out.println("Мінимальне значення: " + minNum);
        System.out.println("Середнє арифметичне: " + arithmeticAverage);
        System.out.println("Медіана: " + median);
        System.out.println("Найбільша послідовність чисел (які ідуть один за одним), яка збільшується: "
                + greatestSequenceOfNumbers);
        System.out.println("Найбільша послідовність чисел (які ідуть один за одним), яка зменшується: "
                + smallestSequenceOfNumbers);

    }

    public static List<Integer> findGreatestSequenceOfNumbers(List<Integer> numbersFromFileList) {
        List<Integer> greatestSequenceOfNumbers = new ArrayList<>();
        List<Integer> greatestSequenceOfNumbersTmp = new ArrayList<>();
        for (int i = 0; i < numbersFromFileList.size() - 1; i++) {
            greatestSequenceOfNumbersTmp.add(numbersFromFileList.get(i));
            if (numbersFromFileList.get(i) >= numbersFromFileList.get(i + 1)) {
                if (greatestSequenceOfNumbers.size() < greatestSequenceOfNumbersTmp.size()) {
                    greatestSequenceOfNumbers = new ArrayList<>(greatestSequenceOfNumbersTmp);
                }
                greatestSequenceOfNumbersTmp.clear();
            }
        }
        if (greatestSequenceOfNumbers.size() <= greatestSequenceOfNumbersTmp.size()) {
            greatestSequenceOfNumbers = new ArrayList<>(greatestSequenceOfNumbersTmp);
        }
        return greatestSequenceOfNumbers;
    }

    public static List<Integer> findSmallestDecreasingSequence(List<Integer> numbersFromFileList) {

        List<Integer> smallestSequenceOfNumbers = new ArrayList<>();
        List<Integer> smallestSequenceOfNumbersTmp = new ArrayList<>();
        for (int i = 0; i < numbersFromFileList.size() - 1; i++) {
            smallestSequenceOfNumbersTmp.add(numbersFromFileList.get(i));
            if (numbersFromFileList.get(i) <= numbersFromFileList.get(i + 1)) {
                if (smallestSequenceOfNumbers.size() < smallestSequenceOfNumbersTmp.size()) {
                    smallestSequenceOfNumbers = new ArrayList<>(smallestSequenceOfNumbersTmp);
                }
                smallestSequenceOfNumbersTmp.clear();
            }
        }
        if (smallestSequenceOfNumbers.size() <= smallestSequenceOfNumbersTmp.size()) {
            smallestSequenceOfNumbers = new ArrayList<>(smallestSequenceOfNumbersTmp);
        }
        return smallestSequenceOfNumbers;
    }

    public static double findMedian(List<Integer> numbersFromFileList, int amountOfNumbers) {
        if (amountOfNumbers % 2 == 0) {
            return (double)(((numbersFromFileList.get(amountOfNumbers / 2))) + ((numbersFromFileList.get((amountOfNumbers / 2) - 1)))) / 2;
        } else {
            return (double)(numbersFromFileList.get((amountOfNumbers) / 2)) ;
        }
    }
}
