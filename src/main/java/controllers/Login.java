package controllers;

import com.jfoenix.controls.JFXTextField;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;

public class Login {
    private final String PASSWORD = "LOKA";
    @FXML
    public JFXTextField password;

    public void caisse(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Caisse.fxml"));
            dataColected(root);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void login(ActionEvent actionEvent) {
        if (password.getText().equals(PASSWORD)) {
            try {
                dataColected(FXMLLoader.<Parent>load(getClass().getResource("/sample.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void dataColected(Parent root) {

        Stage primaryStage = new Stage();

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        Scene scene = new Scene(root, screenBounds.getWidth(), screenBounds.getHeight());

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
