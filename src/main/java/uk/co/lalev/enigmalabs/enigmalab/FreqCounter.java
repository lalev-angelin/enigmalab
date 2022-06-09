package uk.co.lalev.enigmalabs.enigmalab;

import java.util.Map;
import java.util.TreeMap;

public class FreqCounter {
    public static Map<Character, Integer> count(String s) {
        Map<Character, Integer> result = new TreeMap<>();

        for (int i=0; i<26; i++) {
            char letter=(char)('A'+i);
            int count=0;

            for (int j=0; j<s.length(); j++) {
                if (s.charAt(j)==letter) {
                    count++;
                }
            }

            result.put((char)('A'+i), count);
        }

        return result;
    }
}
