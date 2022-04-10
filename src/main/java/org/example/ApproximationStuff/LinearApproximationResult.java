package org.example.ApproximationStuff;

import org.example.FunctionStuff.Function;

public class LinearApproximationResult extends ApproximationResult{

    private final double correlation;

    public LinearApproximationResult(double[] coefficients, Function function, double midSquareDeviation, String type, String funcShort, double correlation) {
        super(coefficients, function, midSquareDeviation, type, funcShort);
        this.correlation = correlation;
    }

    @Override
    public String answer() {
        return type + " approximation result.\n" +
            "Function: " + function + "\n" +
            "Short function: " + funcShort + "\n" +
            "Deviation: " + midSquareDeviation + "\n" +
            "Correlation: " + correlation + "\n";
    }
}
