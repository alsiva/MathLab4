package org.example.ApproximationStuff;
import org.example.dotStuff.DotStorage;


public class LogarithmicApproximation extends AbstractApproximation{


    @Override
    protected String type() {
        return null;
    }

    protected final String createFunction(double[] coefficients) {
        return coefficients[1] + "log(x)+" + coefficients[0];
    }

    @Override
    protected double[] findCoefficients(DotStorage dotStorage) {
        DotStorage modifiedDotStorage = dotStorage.copy();

        for (int i = 0; i < dotStorage.size(); i++) {
            double x = modifiedDotStorage.getDot(i).getX();

            if (x <= 0) {
                continue;
            }

            modifiedDotStorage.getDot(i).setX(Math.log(x));

        }

        ApproximationResult linearApproximation = new LinearApproximation().approximate(modifiedDotStorage);

        return linearApproximation.getCoefficients();
    }
}
