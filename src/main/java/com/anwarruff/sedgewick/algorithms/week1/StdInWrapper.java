package com.anwarruff.sedgewick.algorithms.week1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Scanner;
import java.util.regex.Pattern;

public class StdInWrapper
{
    private static final Pattern WHITESPACE_PATTERN = Pattern.compile("\\p{javaWhitespace}+");
    private static final Pattern EVERYTHING_PATTERN = Pattern.compile("\\A");

    public static int[] readAllInts(String fileName) {
        String[] fields = readAllStrings(fileName);
        int[] vals = new int[fields.length];
        for (int i = 0; i < fields.length; i++)
            vals[i] = Integer.parseInt(fields[i]);
        return vals;
    }

    public static String[] readAllStrings(String fileName) {
        String[] tokens = WHITESPACE_PATTERN.split(readAll(fileName));
        if (tokens.length == 0 || tokens[0].length() > 0)
            return tokens;
        String[] decapitokens = new String[tokens.length-1];
        for (int i=0; i < tokens.length-1; i++)
            decapitokens[i] = tokens[i+1];
        return decapitokens;
    }

    public static String readAll(String fileName)
    {
        Scanner scanner = StdInWrapper.getFileScanner(fileName);
        if (scanner == null)
            return "";

        if (!scanner.hasNextLine())
            return "";

        String result = scanner.useDelimiter(EVERYTHING_PATTERN).next();
        scanner.useDelimiter(WHITESPACE_PATTERN);
        return result;
    }

    public static Scanner getFileScanner(String fileName)
    {
        String path = ThreeSumRunner.class.getClassLoader().getResource(fileName).getFile();
        File inFile = null;
        try {
            inFile = new File(URLDecoder.decode(path, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }

        try {
            return new Scanner(inFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
