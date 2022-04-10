package org.example;

import org.example.ApproximationStuff.*;
import org.example.dotStuff.Dot;
import org.example.dotStuff.DotStorage;


import java.util.*;


public class Application {

    private static final LinearApproximation linearApproximation = new LinearApproximation();
    private static final QuadraticApproximation quadraticApproximation = new QuadraticApproximation();
    private static final CubicApproximation cubicApproximation = new CubicApproximation();

    private static final ExponentialApproximation exponentialApproximation = new ExponentialApproximation();
    private static final LogarithmicApproximation logarithmicApproximation = new LogarithmicApproximation();
    private static final PowerApproximation powerApproximation = new PowerApproximation();

    public static void main(String[] args) {

        DotStorage dotStorage = new DotStorage(List.of(
            new Dot(-2, -0.5),
            new Dot(-1.8, -0.497),
            new Dot(-1.6, -0.488),
            new Dot(-1.4, -0.47),
            new Dot(-1.2, -0.441),
            new Dot(-1, -0.4),
            new Dot(-0.8, -0.345),
            new Dot(-0.6, -0.275),
            new Dot(-0.4, -0.192),
            new Dot(-0.2, -0.099),
            new Dot(0, 0)
        ));




        List<AbstractApproximation> approximations = Arrays.asList(
            linearApproximation, quadraticApproximation, cubicApproximation,
            exponentialApproximation, logarithmicApproximation, powerApproximation
        );

        List<ApproximationResult> approximationResults = new ArrayList<>();

        for (AbstractApproximation approximation: approximations) {
            ApproximationResult result = approximation.approximate(dotStorage);
            if (result != null) {
                approximationResults.add(result);
                System.out.println(result.answer());
            } else {
                System.out.println(approximation.getType() + " didn't work");
            }
        }


        approximationResults.sort(Comparator.comparingDouble(ApproximationResult::getDeviation));
        List<ApproximationResult> firstApproximationResult = approximationResults.subList(0, 1);

        Graph graph = new Graph();
        graph.setApproximationResult(firstApproximationResult, dotStorage);
        graph.run();
    }

}
