package org.example.ApproximationStuff;
import org.example.dotStuff.DotStorage;

public class ExponentialApproximation extends AbstractApproximation {


    @Override
    protected String type() {
        return "Exponential";
    }

    @Override
    protected String createFunction(double[] coefficients) {
        return coefficients[1] + "e^" + "{" + coefficients[0] + "x" + "}";
    }

    @Override
    protected double[] findCoefficients(DotStorage dotStorage) {

        DotStorage modifiedDotStorage = dotStorage.copy();

        for (int i = 0; i < dotStorage.size(); i++) {
            double y = modifiedDotStorage.getDot(i).getY();

            if (y <= 0) {
                return null;
            }

            modifiedDotStorage.getDot(i).setY(Math.log(y));

        }

        ApproximationResult linearApproximation = new LinearApproximation().approximate(modifiedDotStorage);
        double[] coefficients = linearApproximation.getCoefficients();
        coefficients[1] = Math.exp(coefficients[1]);

        return coefficients;
    }
}
