package org.example.ApproximationStuff;


import org.example.FunctionStuff.Function;

public class ApproximationResult {

    protected final double[] coefficients;
    protected final String type;
    protected final Function function;
    protected final String funcShort;
    protected final double midSquareDeviation;

    public ApproximationResult(double[] coefficients, Function function, double midSquareDeviation, String type, String funcShort) {
        this.coefficients = coefficients;
        this.function = function;
        this.midSquareDeviation = midSquareDeviation;
        this.type = type;
        this.funcShort = funcShort;
    }

    public Function getFunction() {
        return function;
    }

    public String getFuncShort() {
        return this.funcShort;
    }

    public String answer() {
        return type + " approximation result.\n" +
            "Function: " + function + "\n" +
            "Short function: " + funcShort + "\n" +
            "Deviation: " + midSquareDeviation + "\n";
    }

    public double getDeviation() {
        return midSquareDeviation;
    }

    public double[] getCoefficients() {
        return coefficients;
    }

}
