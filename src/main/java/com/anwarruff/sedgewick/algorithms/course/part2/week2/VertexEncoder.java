package com.anwarruff.sedgewick.algorithms.course.part2.week2;

import java.util.HashMap;

/**
 * Created by aruff on 2/4/17.
 */
public class VertexEncoder {
    private HashMap<String, Integer> toInteger;
    private HashMap<Integer, String> toString;
    private int nextId;

    public VertexEncoder() {
        nextId = 0;
        toString = new HashMap<>();
        toInteger = new HashMap<>();
    }

    public boolean isEncoded(String v) {
        return toInteger.containsKey(v);
    }

    public void encode(String name) {
        if (toInteger.containsKey(name)) {
            return;
        }

        toInteger.put(name, nextId);
        toString.put(nextId, name);
        ++nextId;
    }

    public int getEncoding(String name) {
        if (!toInteger.containsKey(name)) {
            encode(name);
        }

        return toInteger.get(name);
    }


    public String getEncoding(int id) {
        return toString.get(id);
    }
}
