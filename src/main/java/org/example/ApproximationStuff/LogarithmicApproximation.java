package org.example.ApproximationStuff;

import org.example.FunctionStuff.Function;
import org.example.dotStuff.DotStorage;


public class LogarithmicApproximation extends AbstractApproximation{

    public LogarithmicApproximation(DotStorage dotStorage) {
        super(dotStorage);
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
