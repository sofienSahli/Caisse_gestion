package controllers;

import com.jfoenix.controls.JFXComboBox;
import entities.Fournisseur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class ProduitController {
    @FXML
    private JFXComboBox cbFournisseur;

    @FXML
    public void initialize() {
        ObservableList<Fournisseur> fournisseurs = FXCollections.observableArrayList();
        fournisseurs.add(new Fournisseur(1, "Abc", "Abc", "Abc"));
        fournisseurs.add(new Fournisseur(2, "Abcde", "Abcde", "Abcde"));
        fournisseurs.add(new Fournisseur(3, "Abcd", "Abcd", "Abcd"));
        fournisseurs.add(new Fournisseur(4, "Ab", "Ab", "Ab"));
        for (Fournisseur f : fournisseurs) {
            cbFournisseur.getItems().add(f.getNom());
        }

    }

    private void generateCell() {


    }
}
