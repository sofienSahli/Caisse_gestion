package controllers;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerNextArrowBasicTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;

import java.io.IOException;

public class Controller {
    VBox drawerVbox;
    @FXML
    private JFXDrawer drawer;

    @FXML
    private JFXHamburger hamburger;

    @FXML
    public void initialize() {
        drawerSetting();
        hambuerSetting();
    }

    private void drawerSetting() {

        drawerVbox = null;
        //Drawer VBOX settings
        try {
            drawerVbox = FXMLLoader.load(getClass().getResource("../views/DrawerView.fxml"));
            drawer.setDefaultDrawerSize(Screen.getPrimary().getVisualBounds().getWidth() / 4);
            ImageView imageView = (ImageView) drawerVbox.getChildren().get(0);
            imageView.fitWidthProperty().bind(drawerVbox.widthProperty());
            drawerVbox.setMinWidth(drawer.getDefaultDrawerSize());
            drawerVbox.setMaxWidth(drawer.getDefaultDrawerSize());
            drawerVbox.setFillWidth(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        drawer.setSidePane(drawerVbox);


    }

    private void hambuerSetting() {

        //Hamburger buttons setting
        HamburgerNextArrowBasicTransition basicTransition = new HamburgerNextArrowBasicTransition(hamburger);
        basicTransition.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            basicTransition.setRate(basicTransition.getRate() * -1);
            basicTransition.play();
            if (drawer.isHidden()) {
                drawer.open();
            } else {
                drawer.close();
            }
        });

    }

}
