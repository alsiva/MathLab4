package org.example.ApproximationStuff;
import org.example.dotStuff.DotStorage;

public class PowerApproximation extends AbstractApproximation {


    @Override
    protected String type() {
        return "Power";
    }

    @Override
    protected String createFunction(double[] coefficients) {
        return coefficients[1] + "x^" + coefficients[0];
    }

    @Override
    protected double[] findCoefficients(DotStorage dotStorage) {
        DotStorage modifiedDotStorage = dotStorage.copy();

        for (int i = 0; i < dotStorage.size(); i++) {
            double x = modifiedDotStorage.getDot(i).getX();
            double y = modifiedDotStorage.getDot(i).getY();

            if (x > 0) {
                modifiedDotStorage.getDot(i).setX(Math.log(x));
            }

            if (y > 0) {
                modifiedDotStorage.getDot(i).setY(Math.log(y));
            }

        }

        ApproximationResult linearApproximation = new LinearApproximation().approximate(modifiedDotStorage);
        double[] coefficients = linearApproximation.getCoefficients();
        coefficients[1] = Math.exp(coefficients[1]);

        return coefficients;
    }
}
