package controllers;

import Utils.TemporalyData;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.sun.javafx.tk.Toolkit;
import entities.Categorie;
import entities.Fournisseur;
import entities.Produit;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import services.CategorieDAO;
import services.FournisseurDAO;
import services.ProduitDAO;

import java.util.List;

public class NouveauProduitController {
    public static Controller controller;
    @FXML
    private JFXTextField quantity;

    @FXML
    private JFXTextField sellTax;

    @FXML
    private JFXTextField purchacePriceHTT;

    @FXML
    private JFXTextField purchacePriceHT;

    @FXML
    private JFXTextField purchaceTax;

    @FXML
    private JFXTextField pruchacePrice;

    @FXML
    private JFXTextField nom;

    @FXML
    private JFXComboBox<Categorie> cbCategorie;

    @FXML
    private JFXComboBox<Fournisseur> cbFournisseur;

    @FXML
    private JFXTextField barCode;

    //TODO IMPLEMENTS RUNNABLE FOR BACKGROUND HIVERTE WORK
    public void initialize() {
        if (TemporalyData.fournisseurs != null) {
            cbFournisseur.setItems(TemporalyData.fournisseurs);
        } else {
            fillFournisseurComboBox();
        }
        fillCategorieComboBox();
        setFormatter(quantity);
        setFormatter(sellTax);
        setFormatter(purchacePriceHTT);
        setFormatter(purchacePriceHT);
        setFormatter(pruchacePrice);
    }

    private void fillFournisseurComboBox() {
        ObservableList<Fournisseur> fournisseurs = FXCollections.observableArrayList();
        FournisseurDAO fournisseurDAO = new FournisseurDAO();
        fournisseurs.addAll(fournisseurDAO.findAll());
        cbFournisseur.getItems().clear();
        cbFournisseur.getItems().addAll(fournisseurs);
        cbFournisseur.setValue(fournisseurs.get(0));
    }

    private void fillCategorieComboBox() {
        ObservableList<Categorie> categories = FXCollections.observableArrayList();
        CategorieDAO fournisseurDAO = new CategorieDAO();
        categories.addAll(fournisseurDAO.findAll());
        cbCategorie.getItems().clear();
        cbCategorie.getItems().addAll(categories);
//        cbCategorie.setValue(categories.get(0));
    }

    public void add(ActionEvent actionEvent) {
        if (checkIntegrity(quantity) && checkIntegrity(sellTax) && checkIntegrity(purchacePriceHTT)
                && checkIntegrity(purchacePriceHT) && checkIntegrity(purchaceTax) && checkIntegrity(pruchacePrice)
                && checkIntegrity(nom) && checkIntegrity(barCode)
                ) {
            Produit produit = getProductReady();
            goBack();
            Task task = new Task() {
                @Override
                protected Object call() {
                    add(produit);
                    return null;
                }
            };
            new Thread(task).start();
        }
    }

    // Make call to Service Add class
    private void add(Produit produit) {
        ProduitDAO produitDAO = new ProduitDAO();
        TemporalyData.produits.add(produitDAO.add(produit));


    }

    // make instance of entity
    private Produit getProductReady() {
        Produit produit = new Produit();

        double prixHT = Double.parseDouble(purchacePriceHT.getText());
        double prixHTT = Double.parseDouble(purchacePriceHTT.getText());
        double prixAchat = Double.parseDouble(pruchacePrice.getText());
        double taxAchat = Double.parseDouble(purchaceTax.getText());
        double taxVente = Double.parseDouble(sellTax.getText());
        int qte = Integer.parseInt(quantity.getText());

        produit.setCategorie(cbCategorie.getValue());
        produit.setFournisseur(cbFournisseur.getValue());
        produit.setNom(nom.getText());
        produit.setBarcode(barCode.getText());
        produit.setPurchasePriceHT(prixHT);
        produit.setPurchasePriceTTC(prixHTT);
        produit.setPurchacesPrice(prixAchat);
        produit.setPurchagcesTax(taxAchat);
        produit.setSellTax(taxVente);
        produit.setQuantity(qte);
        cbFournisseur.getValue().getProduits().add(produit);

        return produit;
    }

    public void cancel(ActionEvent actionEvent) {
        goBack();
    }

    private void goBack() {
        controller.loadScene(Controller.PRODUIT_URL);

    }

    //Check weither or not textfied is empty
    private boolean checkIntegrity(TextField textField) {
        if (textField.getText().trim().isEmpty()) {
            textField.setPadding(new Insets(5));
            textField.setBorder(new Border(new BorderStroke(Color.RED,
                    BorderStrokeStyle.SOLID, new CornerRadii(5), BorderWidths.DEFAULT)));
            return false;
        }
        return true;
    }

    //Format textField to get only digits
    private void setFormatter(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }


}
