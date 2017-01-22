package com.anwarruff.sedgewick.algorithms.course.part2.week1.assignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by aruff on 1/22/17.
 */
public class OutcastFileReader {
    private ArrayList<String> nouns;

    public OutcastFileReader(String fileName) {
        File file = new File(getClass().getClassLoader().getResource("part2/week1/assignment/" + fileName).getFile());
        try {
            Scanner scanner = new Scanner(file);

            nouns = new ArrayList<>();
            while (scanner.hasNextLine()) {
                nouns.add(scanner.nextLine().trim());
            }
            scanner.close();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
    }

    public String[] getNouns() {
        return nouns.toArray(new String[nouns.size()]);
    }
}
