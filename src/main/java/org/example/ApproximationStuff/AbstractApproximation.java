package org.example.ApproximationStuff;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.DecompositionSolver;
import org.apache.commons.math3.linear.LUDecomposition;
import org.example.FunctionStuff.Function;
import org.example.dotStuff.DotStorage;

public abstract class AbstractApproximation {

    protected final DotStorage dotStorage;

    public AbstractApproximation(DotStorage dotStorage) {
        this.dotStorage = dotStorage;
    }

    public abstract ApproximationResult approximate();

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

}
