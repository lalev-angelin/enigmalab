package uk.co.lalev.enigmalabs.enigmalab;

import org.apache.commons.math3.stat.inference.ChiSquareTest;

import java.util.*;

public class ChiSquaredAnalyser {

    private double[] letterFreqEnglish = {
            0.13,    //E
            0.091,   //T
            0.082,   //A
            0.075,   //O
            0.070,   //I
            0.067,   //N
            0.063,   //S
            0.061,   //H
            0.060,   //R
            0.043,   //D
            0.040,   //L
            0.028,   //C
            0.028,   //U
            0.024,   //M
            0.024,   //W
            0.022,   //F
            0.02,    //G
            0.02,    //Y
            0.019,   //P
            0.015,   //B
            0.0098,  //V
            0.0077,  //K
            0.0015,  //J
            0.0015,  //X
            0.00095, //Q
            0.00074  //J
    };

    public double computeChiSquare(String s, int skip) {
        Map<Character, Integer> chars = FreqCounter.countEveryNth(s, skip);
        List<Integer> l = new ArrayList<>(chars.values().stream().toList());
        Collections.sort(l, Collections.reverseOrder());
        long[] observed = l.stream().mapToLong(a->a).toArray();
        double[] expected = Arrays.stream(letterFreqEnglish)
                .map((a->(a*(s.length()))/skip))
                .toArray();

        ChiSquareTest chi = new ChiSquareTest();
        return chi.chiSquare(expected, observed);
    }

}
