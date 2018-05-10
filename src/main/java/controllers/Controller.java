package controllers;

import com.jfoenix.controls.JFXDrawer;
import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;

public class Controller implements EventHandler<MouseEvent> {
    @FXML
    public AnchorPane bodyPage;
    VBox drawerVbox;
    @FXML
    private JFXDrawer drawer;

    public static final String PRODUIT_URL = "/ProduitView.fxml";
    public static final String NOUVEAU_PRODUIT_URL = "/NouveauProduit.fxml";
    public static final String NOUVEAU_FOURNISSEUR_URL = "/NouveauFournisseur.fxml";
    public static final String CAISSE_CONTROLLER_URL = "/Caisse.fxml";


    @FXML
    public void initialize() {
        drawerSetting();
        ProduitController.controller = this;
        loadScene(PRODUIT_URL);
    }

    private void drawerSetting() {
        try {

            drawerVbox = FXMLLoader.load(getClass().getResource("/DrawerView.fxml"));
            drawer.setDefaultDrawerSize(Screen.getPrimary().getVisualBounds().getWidth() / 4);
            ImageView imageView = (ImageView) drawerVbox.getChildren().get(0);
            imageView.fitWidthProperty().bind(drawerVbox.widthProperty());
            drawerVbox.setMinWidth(drawer.getDefaultDrawerSize());
            drawerVbox.setMaxWidth(drawer.getDefaultDrawerSize());
            drawerVbox.setFillWidth(true);
            DrawerController.mainController = this;

        } catch (IOException e) {
            e.printStackTrace();
        }
        drawer.setSidePane(drawerVbox);
        drawer.open();

    }


    public void dragged(MouseDragEvent mouseDragEvent) {

    }

    @Override
    public void handle(MouseEvent event) {

    }

    public void DrawerIntertaction() {
        if (drawer.isHidden()) {
            drawer.open();
        } else {
            drawer.close();
        }
    }

    // ------------------------------------------------------Animation and Scene Loading -------------------
    public void loadScene(String url) {
        try {
            Pane loadPane = FXMLLoader.load(getClass().getResource(url));
            AnchorPane.setBottomAnchor(loadPane, 0.0);
            AnchorPane.setLeftAnchor(loadPane, 0.0);
            AnchorPane.setRightAnchor(loadPane, 0.0);
            AnchorPane.setTopAnchor(loadPane, 0.0);
            bodyPage.getChildren().clear();
            bodyPage.getChildren().add(loadPane);
            fxmlInAnitmation();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void fxmlInAnitmation() {
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(300), bodyPage);
        translateTransition.setFromX(Screen.getPrimary().getVisualBounds().getWidth());
        translateTransition.setToX(0.0);
        translateTransition.play();
    }

    public static void showDialog(Stage stage, Parent root, double height, double widht) {
        Stage subStage = new Stage();
        subStage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(root, widht, height);
        subStage.setScene(scene);
        subStage.initOwner(stage);
        subStage.initModality(Modality.WINDOW_MODAL);
        subStage.show();

    }

    public static void rotate(Node node, double fromAngle, double toAngle) {
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(2), node);
        rotateTransition.setFromAngle(fromAngle);
        rotateTransition.setToAngle(toAngle);
        rotateTransition.setAutoReverse(true);
        rotateTransition.setCycleCount(1000);
        rotateTransition.play();
    }

    public static void fadIn(Node node, double fromAlpha, double toAlpha) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(500), node);
        fadeTransition.setFromValue(fromAlpha);
        fadeTransition.setToValue(toAlpha);
        fadeTransition.play();
    }

}
