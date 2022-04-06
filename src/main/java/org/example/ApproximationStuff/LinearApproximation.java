package org.example.ApproximationStuff;

import org.example.FunctionStuff.Function;
import org.example.dotStuff.DotStorage;


public class LinearApproximation extends AbstractApproximation {


    public LinearApproximation(DotStorage dotStorage) {
        super(dotStorage);
    }

    public ApproximationResult approximate() {
        double[] coefficients = findCoefficients(dotStorage);
        Function phi = new Function(coefficients[0] + "x+" + coefficients[1]);
        double s = setS(dotStorage, phi);
        double midSquareDeviation = setMidSquareDeviation(dotStorage, phi);
        return new ApproximationResult(coefficients, phi, midSquareDeviation, "Linear");
    }


    protected double[] findCoefficients(DotStorage dotStorage) {

        double sx = 0;
        double sx2 = 0;
        double sy = 0;
        double sxy = 0;

        for (int i = 0; i < dotStorage.size(); i++) {
            sx += dotStorage.getDot(i).getX();
            sx2 += Math.pow(dotStorage.getDot(i).getX(), 2);
            sy += dotStorage.getDot(i).getY();
            sxy += dotStorage.getDot(i).getX() * dotStorage.getDot(i).getY();
        }



        double[][] elements = {
            {sx2, sx},
            {sx, dotStorage.size()}
        };

        double[] constants = {
            sxy, sy
        };

        return solveLinearSystem(elements, constants);
    }



}