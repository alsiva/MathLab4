package org.example.ApproximationStuff;


import org.example.dotStuff.DotStorage;


public class QuadraticApproximation extends AbstractApproximation {

    @Override
    protected String type() {
        return "Quadratic";
    }

    @Override
    protected String createFunction(double[] coefficients) {
        return coefficients[0] + "x^2+" + coefficients[1] + "x+" + coefficients[0];
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

}
