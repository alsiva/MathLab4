package org.example;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import org.example.ApproximationStuff.ApproximationResult;
import org.example.FunctionStuff.Function;
import org.example.dotStuff.DotStorage;

import java.util.List;

public class Graph extends Application implements Runnable {

    private static List<ApproximationResult> approximations;
    private static DotStorage dotStorage;
    private static final double lineSize = 3;

    public void setApproximationResult(List<ApproximationResult> approximations, DotStorage dotStorage) {
        Graph.approximations = approximations;
        Graph.dotStorage = dotStorage;
    }

    @Override
    public void run() {
        launch();
    }

    @FXML
    public LineChart<Number, Number> lineChart;

    @Override
    public void start(Stage stage) throws Exception {
        lineChart = new LineChart<>(
            new NumberAxis(-lineSize, lineSize, 2),
            new NumberAxis(-lineSize, lineSize, 2)
        );
        lineChart.setCreateSymbols(false);


        Scene scene = new Scene(lineChart, 900, 600);



        for (ApproximationResult approximationResult: approximations) {
            drawFunction(approximationResult.getFunction());
        }

        drawDots(dotStorage);


        stage.setScene(scene);
        stage.show();
    }

    private void drawFunction(Function function) {
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("y=" + function.toString());


        for (double point = -lineSize; point <= lineSize; point += 0.25) {
            series.getData().add(new XYChart.Data<Number, Number>(point, function.apply(point)));
        }



        lineChart.getData().add(series);
    }

    private void drawDots(DotStorage dotStorage) {
        XYChart.Series<Number, Number> series = new XYChart.Series<>();


        series.setName("Original function");
        for (int i = 0; i < dotStorage.size(); i++) {
            double x = dotStorage.getDot(i).getX();
            double y = dotStorage.getDot(i).getY();
            series.getData().add(new XYChart.Data<Number, Number>(x, y));
        }



        lineChart.getData().add(series);
    }

}
