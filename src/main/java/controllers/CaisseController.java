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

    @FXML
    public void initialize() {
        observableList = FXCollections.observableArrayList();
        currentCaisse = new Caisse(new ArrayList<Produit>());

        totalCaisse.setText("0 Dt");
        codabar.textProperty().addListener((observable, oldValue, newValue) -> {
            currentProduit = new Produit(newValue);
            if (TemporalyData.produits.contains(currentProduit)) {
                currentIndex = TemporalyData.produits.indexOf(currentProduit);
                currentProduit = TemporalyData.produits.get(currentIndex);
                nomProduit.setText(currentProduit.getNom());
                currentProduitChanceGlistener();
                printQuatity(1);

            }

        });
        refreshList();
    }

    private void currentProduitChanceGlistener() {
        tfNomProduit.setText(currentProduit.getNom());
        prixTTC.setText(currentProduit.getPurchasePriceTTC() + "  dt");
        PrixHT.setText(currentProduit.getPurchasePriceHT() + "  dt");
        reduction.setText(currentProduit.getDiscount() + " %");
    }

    private void refreshList() {
        observableList.clear();
        observableList.addAll(currentCaisse.getProduits());

        contenuCart.setItems(observableList);
        totalCaisse.setText(currentCaisse.getTotalCaisse() + " DT");
    }


    public void minus(ActionEvent actionEvent) {
        printQuatity(currentProduit.getQuantity() - 1);
    }

    public void plus(ActionEvent actionEvent) {
        printQuatity(currentProduit.getQuantity() + 1);

    }

    public void addTocart(ActionEvent actionEvent) {
        int qte = Integer.parseInt(quantity.getText());
        currentProduit.setQuantity(qte);
        currentCaisse.addProductToCaisse(currentProduit);
        refreshList();

    }

    private void printQuatity(int qte) {
        currentProduit.setQuantity(qte);
        quantity.setText("" + currentProduit.getQuantity());
    }

    public void supprimer(ActionEvent actionEvent) {
        currentCaisse.removeProductFromCaisse(currentProduit);
        refreshList();
    }
}
