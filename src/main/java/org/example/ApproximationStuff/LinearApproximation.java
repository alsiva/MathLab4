package org.example.ApproximationStuff;

import org.example.FunctionStuff.Function;
import org.example.dotStuff.DotStorage;


public class LinearApproximation extends AbstractApproximation {


    @Override
    protected String type() {
        return "Linear";
    }

    @Override
    protected String createFunction(double[] coefficients) {
        return coefficients[0] + "x+" + coefficients[1];
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