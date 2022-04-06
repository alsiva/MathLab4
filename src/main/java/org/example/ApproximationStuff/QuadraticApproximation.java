package org.example.ApproximationStuff;


import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.DecompositionSolver;
import org.example.FunctionStuff.Function;
import org.example.dotStuff.DotStorage;
import org.apache.commons.math3.linear.LUDecomposition;

public class QuadraticApproximation {


    private final DotStorage dotStorage;

    public QuadraticApproximation(DotStorage dotStorage) {
        this.dotStorage = dotStorage;
    }

    public ApproximationResult approximate() {
        double[] coefficients = findCoefficients(dotStorage);
        Function phi = new Function(coefficients[0] + "x^2+" + coefficients[1] + "x+" + coefficients[0]);
        double s = setS(dotStorage, phi);
        double midSquareDeviation = setMidSquareDeviation(dotStorage, phi);
        return new ApproximationResult(coefficients, phi, midSquareDeviation);
    }


    private double[] findCoefficients(DotStorage dotStorage) {

        double sx = 0;
        double sx2 = 0;
        double sx3 = 0;
        double sx4 = 0;
        double sy = 0;
        double sxy = 0;
        double sx2y = 0;

        for (int i = 0; i < dotStorage.size(); i++) {
            sx += dotStorage.getDot(i).getX();
            sx2 += dotStorage.getDot(i).getX() * dotStorage.getDot(i).getX();
            sx3 += dotStorage.getDot(i).getX() * dotStorage.getDot(i).getX() * dotStorage.getDot(i).getX();
            sy += dotStorage.getDot(i).getY();
            sxy += dotStorage.getDot(i).getY() * dotStorage.getDot(i).getY();
            sx2y += dotStorage.getDot(i).getX() * dotStorage.getDot(i).getX() * dotStorage.getDot(i).getY();
        }

        double[][] elements = new double[][] {
            {dotStorage.size(), sx, sx2},
            {sx, sx2, sx3},
            {sx2, sx3, sx4}
        };

        double[] constants = new double[] {sy, sxy, sx2y};
        return solveLinearSystem(elements, constants);

    }

    private double[] solveLinearSystem(double[][] coefficients, double[] constants) {
        DecompositionSolver solver = new LUDecomposition(new Array2DRowRealMatrix(coefficients)).getSolver();
        return solver.solve(new ArrayRealVector(constants)).toArray();

    }

    private double setS(DotStorage dotStorage, Function phi) {
        double value = 0;
        for (int i = 0; i < dotStorage.size(); i++) {
            value += Math.pow(phi.apply(dotStorage.getDot(i).getX()) - dotStorage.getDot(i).getY(), 2);
        }
        return value;
    }

    private double setMidSquareDeviation(DotStorage dotStorage, Function phi) {

        double value = 0;
        for (int i = 0; i < dotStorage.size(); i++) {
            value += Math.pow(phi.apply(dotStorage.getDot(i).getX()) - dotStorage.getDot(i).getY(), 2);
        }

        value = value / dotStorage.size();
        value = Math.sqrt(value);
        return value;
    }

}
