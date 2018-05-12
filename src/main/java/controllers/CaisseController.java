package controllers;

import Utils.TemporalyData;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import entities.Caisse;
import entities.Produit;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.util.ArrayList;

public class CaisseController {
    public static Controller controller;

    @FXML
    private Label quantity;

    @FXML
    private JFXTextField prixTTC;

    @FXML
    private Label totalCaisse;

    @FXML
    private JFXTextField PrixHT;

    @FXML
    private Button addToCart;

    @FXML
    private JFXTextField codabar;

    @FXML
    private JFXButton plusProduct;

    @FXML
    private JFXTextField tfNomProduit;

    @FXML
    private JFXButton minusProduct;

    @FXML
    private JFXTextField reduction;

    @FXML
    private JFXListView<Produit> contenuCart;
    @FXML
    private Label nomProduit;

    private Caisse currentCaisse;
    private Produit currentProduit;
    private int currentIndex;
    private ObservableList<Produit> observableList;
    private String showedProductCodabar;

    @FXML
    public void initialize() {
        disableButtons();
        currentCaisse = new Caisse(new ArrayList<>());
        codabar.textProperty().addListener((observable, oldValue, newValue) -> {
            Produit p = TemporalyData.findIdByCodabar(newValue);
            if (p != null) {
                currentProduit = p;
                setUpProduit();
                enableButtons();
            }
        });


    }

    private void setUpProduit() {
        currentIndex = 1;
        nomProduit.setText(currentProduit.getNom());
        quantity.setText(currentIndex + "");
        setUpInterfaceProduit(currentProduit);
    }

    private void setUpInterfaceProduit(Produit p) {
        tfNomProduit.setText(p.getNom());
        PrixHT.setText(p.getPurchasePriceHT() + "  DT");
        prixTTC.setText(p.getPurchasePriceTTC() + "  DT");
        reduction.setText(p.getDiscount() + " Dts");
        showedProductCodabar = p.getBarcode();
    }


    public void plus(ActionEvent actionEvent) {
        currentIndex++;
        quantity.setText(currentIndex + " ");
    }

    public void minus(ActionEvent actionEvent) {
        if (currentIndex - 1 == 0) {
            currentIndex = 1;
        } else {
            currentIndex--;
        }
        quantity.setText(currentIndex + " ");

    }

    private void disableButtons() {
        addToCart.setDisable(true);
        plusProduct.setDisable(true);
        minusProduct.setDisable(true);
    }

    private void enableButtons() {
        addToCart.setDisable(false);
        plusProduct.setDisable(false);
        minusProduct.setDisable(false);

    }

    public void addTocart(ActionEvent actionEvent) {
        currentCaisse.addProductToCaisse(currentProduit, currentIndex);
        currentIndex = 1;
        quantity.setText(currentIndex + " ");
        totalCaisse.setText(currentCaisse.getTotalCaisse() + "  Dt");
        contenuCart.setItems(FXCollections.observableArrayList(currentCaisse.getProduits()));
        disableButtons();
    }

    public void supprimer(ActionEvent actionEvent) {
        currentCaisse.removeProductFromCaisse(currentProduit);
        totalCaisse.setText(currentCaisse.getTotalCaisse()+" Dt");
        contenuCart.setItems(FXCollections.observableArrayList(currentCaisse.getProduits()));
    }
}
