package org.example.ApproximationStuff;


import org.example.FunctionStuff.Function;

public class ApproximationResult {

    private final double[] coefficients;
    private final String type;
    private final Function function;
    private final double midSquareDeviation;

    public ApproximationResult(double[] coefficients, Function function, double midSquareDeviation, String type) {
        this.coefficients = coefficients;
        this.function = function;
        this.midSquareDeviation = midSquareDeviation;
        this.type = type;
    }

    public Function getFunction() {
        return function;
    }

    public String answer() {
        return type + " approximation result.\n" +
            "Function: " + function + "\n" +
            "Deviation: " + midSquareDeviation + "\n";
    }

    public double getDeviation() {
        return midSquareDeviation;
    }

    public double[] getCoefficients() {
        return coefficients;
    }

}
