package org.example.ApproximationStuff;


import org.example.FunctionStuff.Function;

public class ApproximationResult {

    private double[] coefficients;
    private Function function;
    private double midSquareDeviation;

    public ApproximationResult(double[] coefficients, Function function, double midSquareDeviation) {
        this.coefficients = coefficients;
        this.function = function;
        this.midSquareDeviation = midSquareDeviation;
    }

    public Function getFunction() {
        return function;
    }

    public String answer() {
        return String.format("Approximation result.\n" +
            "Function: %s\n" + function,
            "Deviation: %f\n" + midSquareDeviation);
    }

    public double getDeviation() {
        return midSquareDeviation;
    }

}
