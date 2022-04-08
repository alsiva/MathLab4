package org.example.ApproximationStuff;

import org.example.FunctionStuff.Function;
import org.example.dotStuff.DotStorage;

public class ExponentialApproximation extends AbstractApproximation {

    public ExponentialApproximation(DotStorage dotStorage) {
        super(dotStorage);
    }

    @Override
    public ApproximationResult approximate() {
        double[] coefficients = findCoefficients(dotStorage);
        Function phi = new Function(coefficients[1] + "e^" + "{" + coefficients[0] + "x" + "}");
        double s = setS(dotStorage, phi);
        double midSquareDeviation = setMidSquareDeviation(dotStorage, phi);
        return new ApproximationResult(coefficients, phi, midSquareDeviation, "Exponential");
    }

    @Override
    protected double[] findCoefficients(DotStorage dotStorage) {

        DotStorage modifiedDotStorage = dotStorage.copy();

        for (int i = 0; i < dotStorage.size(); i++) {
            double y = modifiedDotStorage.getDot(i).getY();

            if (y <= 0) {
                continue;
            }

            modifiedDotStorage.getDot(i).setY(Math.log(y));

        }

        ApproximationResult linearApproximation = new LinearApproximation(modifiedDotStorage).approximate();
        double[] coefficients = linearApproximation.getCoefficients();
        coefficients[1] = Math.exp(coefficients[1]);

        return coefficients;
    }
}
