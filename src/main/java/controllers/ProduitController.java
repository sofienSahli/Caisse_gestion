package controllers;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import entities.Fournisseur;
import entities.Produit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TreeItem;

public class ProduitController {
    @FXML
    private ComboBox<Fournisseur> cbFournisseur;

    public static Controller controller;

    @FXML
    public void initialize() {
        fillFournisseurComboBox();
        initializeTableView();
    }

    private void fillFournisseurComboBox() {
        ObservableList<Fournisseur> fournisseurs = FXCollections.observableArrayList();

        cbFournisseur.getItems().clear();
        cbFournisseur.setPromptText("Aucun fournisseur selectioné");
        cbFournisseur.getItems().addAll(fournisseurs);
    }

    private void initializeTableView() {
    }

    public void addProduit(ActionEvent actionEvent) {
        if (controller != null) {
            NouveauProduitController.controller = controller;
            controller.loadScene(Controller.NOUVEAU_PRODUIT_URL);
        }
    }
}
