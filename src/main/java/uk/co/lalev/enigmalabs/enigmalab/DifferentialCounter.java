package uk.co.lalev.enigmalabs.enigmalab;

import java.util.Map;
import java.util.TreeMap;

public class DifferentialCounter {
    private int startNumber;
    private int endNumber;

    public DifferentialCounter(int startNumber, int endNumber) {
        this.startNumber = startNumber;
        this.endNumber = endNumber;
    }

    public Map<Integer, Map<Character, Integer>> count(String str) {
        var response = new TreeMap<Integer, Map<Character, Integer>>();

        for (int skip = startNumber; skip<=endNumber; skip++) {
            StringBuilder k = new StringBuilder();
            for (int i=0; i<str.length(); i+=skip) {
                k.append(str.charAt(i));
            }
            response.put(skip, FreqCounter.count(k.toString()));
        }

        return response;
    }
}
