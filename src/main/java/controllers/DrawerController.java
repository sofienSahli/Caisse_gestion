package controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class DrawerController implements EventHandler<MouseEvent> {
    public VBox drawerVbox;
    public JFXButton stockButton;
    public static Controller mainController;

    @FXML
    public void initialize() {
        stockButton.setOnMouseClicked(this);
    }

    @Override
    public void handle(MouseEvent event) {
        if (mainController != null) {
            mainController.loadScene(Controller.PRODUIT_URL);
            ProduitController.controller = mainController;
        }

    }

    public void fournisseurClicked(ActionEvent actionEvent) {

        if (mainController != null) {
            mainController.loadScene(Controller.NOUVEAU_FOURNISSEUR_URL);
            NouveauFournisseurController.controller = mainController;
        }

    }

    public void nouvelleCaisse(ActionEvent actionEvent) {
        if (mainController != null) {
            mainController.loadScene(Controller.CAISSE_CONTROLLER_URL);
            //CaisseController.controller = mainController;
        }
    }

    public void exit(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void categorie(ActionEvent actionEvent) {
        if (mainController != null) {
            mainController.loadScene(Controller.NOUVEL_CATEGORIE_CONTROLLER);
            NouvelleCategorieController.controller = mainController;
        }
    }
}
