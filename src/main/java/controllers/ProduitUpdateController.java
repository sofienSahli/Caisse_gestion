package controllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import entities.Categorie;
import entities.Fournisseur;
import entities.Produit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.CategorieDAO;
import services.FournisseurDAO;
import services.ProduitDAO;

public class ProduitUpdateController {
    public static Produit produit;
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

    @FXML
    void add(ActionEvent event) {
        getProductReady();
        goBack();
        Task task = new Task() {
            @Override
            protected Object call() {
                ProduitDAO produitDAO = new ProduitDAO();
                produitDAO.update(produit);
                return null;
            }
        };
        new Thread(task).start();


    }

    private void goBack() {

        Stage stage = (Stage) quantity.getScene().getWindow();
        stage.close();
        controller.loadScene(Controller.PRODUIT_URL);

    }

    @FXML
    void cancel(ActionEvent event) {
        goBack();
    }

    public void initialize() {
        if (produit != null) {
            fillFournisseurComboBox();
            fillCategorieComboBox();
            fillForm();
        }
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
        cbFournisseur.setValue(produit.getFournisseur());
    }

    private void fillCategorieComboBox() {
        ObservableList<Categorie> categories = FXCollections.observableArrayList();
        CategorieDAO fournisseurDAO = new CategorieDAO();
        categories.addAll(fournisseurDAO.findAll());
        cbCategorie.getItems().clear();
        cbCategorie.getItems().addAll(categories);
        cbCategorie.setValue(produit.getCategorie());
    }

    private void fillForm() {
        quantity.setText(produit.getQuantity() + "");
        barCode.setText(produit.getBarcode());
        nom.setText(produit.getNom());
        pruchacePrice.setText(produit.getPurchacesPrice() + "");
        purchacePriceHT.setText(produit.getPurchasePriceHT() + "");
        purchacePriceHTT.setText(produit.getPurchasePriceTTC() + "");
        purchaceTax.setText(produit.getPurchagcesTax() + "");
        sellTax.setText(produit.getSellTax() + "");

    }

    //Format textField to get only digits
    private void setFormatter(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    private void getProductReady() {

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

    }

}
