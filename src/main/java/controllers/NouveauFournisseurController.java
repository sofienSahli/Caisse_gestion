package controllers;

import Utils.TemporalyData;
import com.jfoenix.controls.JFXTextField;
import entities.Fournisseur;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import services.FournisseurDAO;
import services.ProduitDAO;

import java.io.IOException;

public class NouveauFournisseurController {
    public static Controller controller;
    @FXML
    ListView<Fournisseur> listFournisseur;
    @FXML
    private JFXTextField addressFournisseur;

    @FXML
    private JFXTextField numeroFournisseur;


    @FXML
    private JFXTextField nomFournisseur;

    @FXML
    private JFXTextField mailFournisseur;
    private Fournisseur currentSelectedFournisseur;

    public void initialize() {
        listFournisseur.setItems(TemporalyData.fournisseurs);
        listFournisseur.setOnMouseClicked(event -> {
            // currentSelectedFournisseur = (Fournisseur) listFournisseur.getSelectionModel().getSelectedItems();
            currentSelectedFournisseur = listFournisseur.getSelectionModel().getSelectedItem();
            prepareUpdate();
        });
    }

    public void update(ActionEvent actionEvent) {
        if (currentSelectedFournisseur != null) {
            currentSelectedFournisseur.setAddress(addressFournisseur.getText());
            currentSelectedFournisseur.setNom(nomFournisseur.getText());
            currentSelectedFournisseur.setMail(mailFournisseur.getText());
            currentSelectedFournisseur.setTel(numeroFournisseur.getText());

            FournisseurDAO fournisseurDAO = new FournisseurDAO();
            fournisseurDAO.update(currentSelectedFournisseur);
            currentSelectedFournisseur = null;
        }
    }

    private void prepareUpdate() {
        addressFournisseur.setText(currentSelectedFournisseur.getAddress());
        nomFournisseur.setText(currentSelectedFournisseur.getNom());
        mailFournisseur.setText(currentSelectedFournisseur.getMail());
        numeroFournisseur.setText(currentSelectedFournisseur.getTel());
    }


    public void addFournisseur(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/NouveauFournisseurDialog.fxml"));
            Stage thisSatge = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Controller.showDialog(thisSatge, root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
