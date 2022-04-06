package org.example;

import org.example.ApproximationStuff.ApproximationResult;
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
        System.out.println(linearApproximation.answer());
        System.out.println(quadraticApproximation.answer());

        List<ApproximationResult> list = new ArrayList<>(List.of(linearApproximation, quadraticApproximation));
        list.sort(Comparator.comparingDouble(ApproximationResult::getDeviation));

        Graph graph = new Graph();
        graph.setApproximationResult(list);
        graph.run();


    }

}
