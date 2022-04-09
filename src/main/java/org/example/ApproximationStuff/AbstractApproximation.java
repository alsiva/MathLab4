package org.example.ApproximationStuff;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.DecompositionSolver;
import org.apache.commons.math3.linear.LUDecomposition;
import org.example.FunctionStuff.Function;
import org.example.dotStuff.DotStorage;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;

public abstract class AbstractApproximation {

    protected abstract String type();

    public String getType() {
        return this.type();
    }

    protected abstract String createFunction(double[] coefficients);

    public ApproximationResult approximate(DotStorage dotStorage) {
        double[] coefficients = findCoefficients(dotStorage);

        if (coefficients == null) {
            return null;
        }

        Function phi = new Function(createFunction(coefficients));
        double s = setS(dotStorage, phi);
        double midSquareDeviation = setMidSquareDeviation(dotStorage, phi);

        DecimalFormat df = new DecimalFormat("0.000");

        double[] roundedCoefficients = Arrays.stream(coefficients)
            .map(value -> new BigDecimal(value).setScale(2, RoundingMode.HALF_UP).doubleValue())
            .toArray();

        String roundedFunction = createFunction(roundedCoefficients);

        String functionType = type();

        return new ApproximationResult(coefficients, phi, midSquareDeviation, functionType);
    }

    protected abstract double[] findCoefficients(DotStorage dotStorage);


    protected double[] solveLinearSystem(double[][] coefficients, double[] constants) {
        DecompositionSolver solver = new LUDecomposition(new Array2DRowRealMatrix(coefficients)).getSolver();
        return solver.solve(new ArrayRealVector(constants)).toArray();
    }


    protected double setS(DotStorage dotStorage, Function phi) {
        double value = 0;
        for (int i = 0; i < dotStorage.size(); i++) {
            value += Math.pow(phi.apply(dotStorage.getDot(i).getX()) - dotStorage.getDot(i).getY(), 2);
        }

        return value;
    }

    protected double setMidSquareDeviation(DotStorage dotStorage, Function phi) {

        double value = 0;
        for (int i = 0; i < dotStorage.size(); i++) {
            value += Math.pow(phi.apply(dotStorage.getDot(i).getX()) - dotStorage.getDot(i).getY(), 2);
        }

        value = value / dotStorage.size();
        value = Math.sqrt(value);
        return value;
    }

    protected double[] reverseArray(double[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            double temp = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = temp;
        }
        return array;
    }

}
