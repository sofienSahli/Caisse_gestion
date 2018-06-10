package controllers;

import Utils.TemporalyData;
import com.jfoenix.controls.JFXTextField;
import entities.Fournisseur;
import entities.Produit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import services.FournisseurDAO;
import services.ProduitDAO;

import java.io.IOException;
import java.util.List;

public class NouveauFournisseurController {
    public static Controller controller;
    @FXML
    public JFXTextField search;
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
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.equals("")) {
                List<Fournisseur> list = makeSearch(newValue);
                if (list != null)
                    fillTableView(FXCollections.observableArrayList(list));
            } else {
                fillTableView(TemporalyData.fournisseurs);
            }

        });
    }

    private List<Fournisseur> makeSearch(String newValue) {
        FournisseurDAO fournisseurDAO = new FournisseurDAO() ;
        return  fournisseurDAO.findByString(newValue);
    }

    private void fillTableView(ObservableList<Fournisseur> produits) {
        listFournisseur.setItems(produits);
    }

    public void update(ActionEvent actionEvent) {
        if (currentSelectedFournisseur != null) {
            currentSelectedFournisseur.setAddress(addressFournisseur.getText());
            currentSelectedFournisseur.setNom(nomFournisseur.getText());
            currentSelectedFournisseur.setMail(mailFournisseur.getText());
            currentSelectedFournisseur.setTel(numeroFournisseur.getText());

            FournisseurDAO fournisseurDAO = new FournisseurDAO();
            fournisseurDAO.update(currentSelectedFournisseur);
            TemporalyData.fournisseurs.remove(currentSelectedFournisseur);
            TemporalyData.fournisseurs.add(currentSelectedFournisseur);
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
            Controller.showDialog(thisSatge, root , 300 , 300 );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void supprimer(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Etes vous sur de vouloir supprimer le produit  ?", ButtonType.YES, ButtonType.CANCEL);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            TemporalyData.fournisseurs.remove(currentSelectedFournisseur);
            FournisseurDAO fournisseurDAO = new FournisseurDAO();
            fournisseurDAO.delete(currentSelectedFournisseur.getId());
            fillTableView(TemporalyData.fournisseurs);
        }
    }
}
