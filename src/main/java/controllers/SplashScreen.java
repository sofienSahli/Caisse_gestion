package controllers;

import Utils.TemporalyData;
import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;

public class SplashScreen {
    @FXML
    private Circle outerCircle;

    @FXML
    private Circle innerCircle;

    @FXML
    private Circle middleCircle;

    public synchronized void initialize() {
        rotate();

        Task task = new Task() {
            @Override
            protected Object call() {
                TemporalyData.loadData();

                try {
                    return FXMLLoader.<Parent>load(getClass().getResource("/sample.fxml"));

                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        task.setOnSucceeded(event -> {

            dataColected((Parent) task.getValue());

        });

        new Thread(task).start();
    }

    private synchronized void rotate() {
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(2), outerCircle);
        rotateTransition.setFromAngle(0);
        rotateTransition.setToAngle(360);
        rotateTransition.setAutoReverse(true);
        rotateTransition.setCycleCount(1000);
        rotateTransition.play();

        RotateTransition rotateTransition1 = new RotateTransition(Duration.seconds(2), middleCircle);
        rotateTransition1.setFromAngle(360);
        rotateTransition1.setToAngle(0);
        rotateTransition1.setAutoReverse(true);
        rotateTransition1.setCycleCount(1000);
        rotateTransition1.play();

        RotateTransition rotateTransition2 = new RotateTransition(Duration.seconds(2), innerCircle);
        rotateTransition2.setFromAngle(0);
        rotateTransition2.setToAngle(360);
        rotateTransition2.setAutoReverse(true);
        rotateTransition2.setCycleCount(1000);
        rotateTransition2.play();

    }

    private void dataColected(Parent root) {

        Stage primaryStage = new Stage();

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        Scene scene = new Scene(root, screenBounds.getWidth(), screenBounds.getHeight());
        primaryStage.initStyle(StageStyle.UNDECORATED);

        scene.getStylesheets().add("Main.css");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), root);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();

    }
}


