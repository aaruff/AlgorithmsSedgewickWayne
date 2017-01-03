package com.anwarruff.sedgewick.algorithms.textbook.chapter1.section4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by aruff on 12/29/16.
 */
public class ThreeSumTest {
    private int[] numbers;

    public ThreeSumTest(String fileName, int size) throws FileNotFoundException {
        File file = new File(getClass().getClassLoader().getResource("textbook/chapter1/section4/" + fileName).getFile());
        Scanner scanner = new Scanner(file);

        numbers = new int[size];
        int i = 0;
        while (scanner.hasNextLine() && i < size) {
            numbers[i++] = scanner.nextInt();
        }
        scanner.close();
    }

    public void runTest() {
        long startTime = System.nanoTime();
        int result = ThreeSum.count(numbers);
        long endTime = System.nanoTime();

        long seconds = (endTime - startTime) / 1000000000;

        System.out.println("Input Size = " + numbers.length);
        System.out.println("Result = " + result);
        System.out.println("Running time in seconds = " + seconds);
    }

    public static void main(String[] args) {
        HashMap<String, Integer> testCases = new HashMap<>();
        for (int i = 0; i <= 5; ++i) {
            int thousands = (int) Math.pow(2, i);
            testCases.put(String.format("%dKints.txt", thousands), thousands * 1000);
        }

        try {
            for (Map.Entry<String, Integer> s : testCases.entrySet()) {
                ThreeSumTest twoThousandIntTest = new ThreeSumTest(s.getKey(), s.getValue());
                twoThousandIntTest.runTest();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}