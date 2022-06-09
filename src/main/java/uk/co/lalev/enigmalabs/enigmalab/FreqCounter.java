package uk.co.lalev.enigmalabs.enigmalab;

import java.util.Map;
import java.util.TreeMap;

public class FreqCounter {
    public static Map<Character, Integer> count(String s) {
        Map<Character, Integer> result = new TreeMap<>();
        for (int i=0; i<26; i++) {
            result.put((char)('A'+i), 0);
        }


        for (int i=0; i<s.length(); i++) {
            int count=result.get(s.charAt(i));

            for (int j=0; j<s.length(); j++) {
                if (s.charAt(i)==s.charAt(j)) {
                    count++;
                }
            }

            result.put(s.charAt(i), count);
        }

        return result;
    }
}
