package org.example;

import org.example.ApproximationStuff.*;
import org.example.dotStuff.Dot;
import org.example.dotStuff.DotStorage;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Application {
    private static final QuadraticApproximation quadraticApproximation = new QuadraticApproximation();
    private static final CubicApproximation cubicApproximation = new CubicApproximation();

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

        List<ApproximationResult> sortedResults = Stream.of(quadraticApproximation, cubicApproximation)
            .map(approximation -> approximation.approximate(dotStorage))
            .sorted(Comparator.comparingDouble(ApproximationResult::getDeviation))
            .collect(Collectors.toList());

        sortedResults.stream()
            .map(ApproximationResult::answer)
            .forEach(System.out::println);

        List<ApproximationResult> firstApproximationResult = sortedResults.subList(0, 1);




//        List<ApproximationResult> list = new ArrayList<>(List.of(
//            quadraticApproximation));
//
//        for (ApproximationResult approximationResult: list) {
//            System.out.println(approximationResult.answer());
//        }

//        list.sort(Comparator.comparingDouble(ApproximationResult::getDeviation));

        Graph graph = new Graph();
        graph.setApproximationResult(sortedResults, dotStorage);
        graph.run();


    }

}
