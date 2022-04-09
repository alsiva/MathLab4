package org.example.ApproximationStuff;

import org.example.FunctionStuff.Function;
import org.example.dotStuff.DotStorage;

public class ExponentialApproximation extends AbstractApproximation {


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
