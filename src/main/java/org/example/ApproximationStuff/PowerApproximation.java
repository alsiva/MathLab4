package org.example.ApproximationStuff;

import org.example.FunctionStuff.Function;
import org.example.dotStuff.DotStorage;

public class PowerApproximation extends AbstractApproximation {

    public PowerApproximation(DotStorage dotStorage) {
        super(dotStorage);
    }

    @Override
    public ApproximationResult approximate() {
        double[] coefficients = findCoefficients(dotStorage);
        Function phi = new Function(coefficients[1] + "x^" + coefficients[0]);
        double s = setS(dotStorage, phi);
        double midSquareDeviation = setMidSquareDeviation(dotStorage, phi);
        return new ApproximationResult(coefficients, phi, midSquareDeviation, "Power");
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

        ApproximationResult linearApproximation = new LinearApproximation(modifiedDotStorage).approximate();
        double[] coefficients = linearApproximation.getCoefficients();
        coefficients[1] = Math.exp(coefficients[1]);

        return coefficients;
    }
}
