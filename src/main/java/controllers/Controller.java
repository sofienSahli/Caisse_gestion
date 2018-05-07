package controllers;

import com.jfoenix.controls.JFXDrawer;
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

    public static void showDialog(Stage stage, Parent root) {
        Stage subStage = new Stage();
        Scene scene = new Scene(root, 250, 250);
        subStage.setScene(scene);
        subStage.initOwner(stage);
        subStage.initModality(Modality.WINDOW_MODAL);
        subStage.show();

    }


}
