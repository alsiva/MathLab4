package org.example.ApproximationStuff;

import org.example.FunctionStuff.Function;
import org.example.dotStuff.DotStorage;


public class QuadraticApproximation extends AbstractApproximation {


    public QuadraticApproximation(DotStorage dotStorage) {
        super(dotStorage);
    }

    public ApproximationResult approximate() {
        double[] coefficients = findCoefficients(dotStorage);
        Function phi = new Function(coefficients[0] + "x^2+" + coefficients[1] + "x+" + coefficients[0]);
        double s = setS(dotStorage, phi);
        double midSquareDeviation = setMidSquareDeviation(dotStorage, phi);
        return new ApproximationResult(coefficients, phi, midSquareDeviation, "Quadratic");
    }


    protected double[] findCoefficients(DotStorage dotStorage) {

        double sx = 0;
        double sx2 = 0;
        double sx3 = 0;
        double sx4 = 0;
        double sy = 0;
        double sxy = 0;
        double sx2y = 0;

        for (int i = 0; i < dotStorage.size(); i++) {
            sx += dotStorage.getDot(i).getX();
            sx2 += Math.pow(dotStorage.getDot(i).getX(), 2);
            sx3 += Math.pow(dotStorage.getDot(i).getX(), 3);
            sx4 += Math.pow(dotStorage.getDot(i).getX(), 4);
            sy += dotStorage.getDot(i).getY();
            sxy += dotStorage.getDot(i).getX() * dotStorage.getDot(i).getY();
            sx2y += Math.pow(dotStorage.getDot(i).getX(), 2) * dotStorage.getDot(i).getY();
        }

        double[][] elements = new double[][] {
            {dotStorage.size(), sx, sx2},
            {sx, sx2, sx3},
            {sx2, sx3, sx4}
        };

        double[] constants = new double[] {sy, sxy, sx2y};
        return reverseArray(solveLinearSystem(elements, constants));


    }

    private double[] reverseArray(double[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            double temp = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = temp;
        }
        return array;
    }

}
