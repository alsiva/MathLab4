package org.example;

import org.example.ApproximationStuff.ApproximationResult;
import org.example.ApproximationStuff.CubicApproximation;
import org.example.ApproximationStuff.LinearApproximation;
import org.example.ApproximationStuff.QuadraticApproximation;
import org.example.dotStuff.DotStorage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Application {

    public static void main(String[] args) {

        DotStorage dotStorage = new DotStorage();

        ApproximationResult linearApproximation = new LinearApproximation(dotStorage).approximate();
        ApproximationResult quadraticApproximation = new QuadraticApproximation(dotStorage).approximate();
        ApproximationResult cubicApproximation = new CubicApproximation(dotStorage).approximate();

        List<ApproximationResult> list = new ArrayList<>(List.of(
            linearApproximation, quadraticApproximation, cubicApproximation));

        for (ApproximationResult approximationResult: list) {
            System.out.println(approximationResult.answer());
        }

        list.sort(Comparator.comparingDouble(ApproximationResult::getDeviation));

        Graph graph = new Graph();
        graph.setApproximationResult(list);
        graph.run();


    }

}
