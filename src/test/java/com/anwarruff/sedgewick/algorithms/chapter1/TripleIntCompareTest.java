package com.anwarruff.sedgewick.algorithms.chapter1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

/**
 * Created by aruff on 12/21/16.
 */
public class TripleIntCompareTest {
    private ByteArrayOutputStream byteArrayOutputStream;
    @Before
    public void setUp() throws Exception {
        // set stdout
        byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        System.setOut(printStream);
    }

    @Test
    public void testNotEqualInput() throws Exception {
        String[] notEqualInput = {"1", "2", "3"};

        TripleIntCompare.main(notEqualInput);

        String output = byteArrayOutputStream.toString();
        Assert.assertTrue("not equal\n".equals(output));
    }

    @Test
    public void testEqualInput() throws Exception {
        String[] notEqualInput = {"2", "2", "2"};

        TripleIntCompare.main(notEqualInput);

        String output = byteArrayOutputStream.toString();
        Assert.assertTrue("equals\n".equals(output));
    }
}