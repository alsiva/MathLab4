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

import java.util.List;

public class Graph extends Application implements Runnable {

    private static List<ApproximationResult> approximations;

    public void setApproximationResult(List<ApproximationResult> approximations) {
        Graph.approximations = approximations;
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
            new NumberAxis(-10, 10, 2),
            new NumberAxis(-10, 10, 2)
        );

        Scene scene = new Scene(lineChart, 900, 600);

        for (ApproximationResult approximationResult: approximations) {
            drawFunction(approximationResult.getFunction());
        }

        stage.setScene(scene);
        stage.show();
    }

    private void drawFunction(Function function) {
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("y=" + function.toString());
        for (double point = -10; point <= 10; point += 0.01) {
            series.getData().add(new XYChart.Data<Number, Number>(point, function.apply(point)));
        }
        lineChart.setCreateSymbols(false);
        lineChart.getData().add(series);
    }
}
