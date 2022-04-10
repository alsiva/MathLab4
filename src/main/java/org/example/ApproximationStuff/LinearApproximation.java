package org.example.ApproximationStuff;
import org.example.FunctionStuff.Function;
import org.example.dotStuff.Dot;
import org.example.dotStuff.DotStorage;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;


public class LinearApproximation extends AbstractApproximation {


    @Override
    protected String type() {
        return "Linear";
    }

    @Override
    protected String createFunction(double[] coefficients) {
        return coefficients[0] + "x+" + coefficients[1];
    }

    @Override
    public ApproximationResult approximate(DotStorage dotStorage) {
        double[] coefficients = findCoefficients(dotStorage);

        if (coefficients == null) {
            return null;
        }

        Function phi = new Function(createFunction(coefficients));
        double s = setS(dotStorage, phi);
        double midSquareDeviation = setMidSquareDeviation(dotStorage, phi);

        DecimalFormat df = new DecimalFormat("0.000");

        double[] roundedCoefficients = Arrays.stream(coefficients)
            .map(value -> new BigDecimal(value).setScale(2, RoundingMode.HALF_UP).doubleValue())
            .toArray();

        String roundedFunction = createFunction(roundedCoefficients);

        String functionType = type();

        return new LinearApproximationResult(coefficients, phi, midSquareDeviation, functionType, roundedFunction, countCorrelation(dotStorage));
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

    private double countCorrelation(DotStorage dotStorage) {
        List<Dot> dotList = dotStorage.getDOTS();

        final double xAvg = dotList.stream().mapToDouble(Dot::getX).sum() / dotList.size();
        final double yAvg = dotList.stream().mapToDouble(Dot::getY).sum() / dotList.size();

        double top = dotList.stream().map(dot -> new double[]{dot.getX() - xAvg, dot.getY() - yAvg}).mapToDouble(dot -> dot[0] * dot[1]).sum();
        double bottomXSum = dotList.stream().mapToDouble(dot -> Math.pow(dot.getX() - xAvg, 2)).sum();
        double bottomYSum = dotList.stream().mapToDouble(dot -> Math.pow(dot.getY() - yAvg, 2)).sum();

        return top / Math.sqrt(bottomXSum * bottomYSum);
    }
}