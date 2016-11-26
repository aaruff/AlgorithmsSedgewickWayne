package com.anwarruff.sedgewick.algorithms.week3.assignment;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by aruff on 11/25/16.
 */
public class FastRunner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(FastRunner.getFile(args[0]));
        int n = Integer.valueOf(scanner.nextLine().trim());
        Point[] points = new Point[n];

        int i = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if ( ! line.isEmpty()) {
                String[] split = line.split("\\s+");
                int x = Integer.valueOf(split[0].trim());
                int y = Integer.valueOf(split[1].trim());
                points[i++] = new Point(x, y);
            }
        }

        // draw the points
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
        StdOut.print(collinear.numberOfSegments());
    }

    private static String getFile(String fileName) {

        StringBuilder result = new StringBuilder("");

        //Get file from resources folder
        ClassLoader classLoader = FastRunner.class.getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());

        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                result.append(line).append("\n");
            }

            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result.toString();

    }

}
