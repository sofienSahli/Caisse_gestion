package controllers;

import com.jfoenix.controls.JFXDrawer;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.util.Duration;

import java.io.IOException;

public class Controller implements EventHandler<MouseEvent> {
    @FXML
    public AnchorPane bodyPage;
    VBox drawerVbox;
    @FXML
    private JFXDrawer drawer;

    public static final String PRODUIT_URL = "../views/ProduitView.fxml";


    @FXML
    public void initialize() {
        drawerSetting();
    }

    private void drawerSetting() {
        //Drawer VBOX settings
        try {

            drawerVbox = FXMLLoader.load(getClass().getResource("../views/DrawerView.fxml"));
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
            bodyPage.getChildren().add(loadPane);
            fxmlInAnitmation();
            System.out.println("here");
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

}
