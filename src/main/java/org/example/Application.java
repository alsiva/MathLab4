package org.example;

import org.example.ApproximationStuff.ApproximationResult;
import org.example.ApproximationStuff.LinearApproximation;
import org.example.dotStuff.DotStorage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Application {

    public static void main(String[] args) {

        DotStorage dotStorage = new DotStorage();

        ApproximationResult linearApproximation = new LinearApproximation(dotStorage).approximate();
        System.out.println(linearApproximation.answer());

        List<ApproximationResult> list = new ArrayList<>(List.of(linearApproximation));
        list.sort(Comparator.comparingDouble(ApproximationResult::getDeviation));

        Graph graph = new Graph();
        graph.setApproximationResult(list);
        graph.run();


    }

}
