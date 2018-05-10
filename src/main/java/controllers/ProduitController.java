package controllers;

import Utils.TemporalyData;
import com.jfoenix.controls.JFXComboBox;
import entities.Categorie;
import entities.Fournisseur;
import entities.Produit;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.ProduitDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProduitController {
    @FXML
    private TableView<ProduitUI> TableView;
    @FXML
    private TableColumn<ProduitUI, Double> prixHtColum;

    @FXML
    private TableColumn<ProduitUI, String> barCodeColumn;

    @FXML
    private TableColumn<ProduitUI, String> nomColum;

    @FXML
    private TableColumn<ProduitUI, Double> prixTtcColum;

    @FXML
    private JFXComboBox<Fournisseur> cbFournisseur;
    @FXML
    private JFXComboBox<Categorie> cbCategorie;

    @FXML
    private TableColumn<ProduitUI, Double> remiseColum;

    @FXML
    private TableColumn<ProduitUI, Integer> qteColumn;

    @FXML
    private TableColumn<ProduitUI, String> categorieColum;
    @FXML
    private TableColumn<ProduitUI, String> minusOneQte;
    @FXML
    private TableColumn<ProduitUI, String> plusOneQte;

    public static Controller controller;


    @FXML
    public void initialize() {
        fillFournisseurComboBox();
        initializeTableView();
        fillCategorieComboBox();
    }


    private void initializeTableView() {
        prixHtColum.setCellValueFactory(new PropertyValueFactory("prixHT"));
        barCodeColumn.setCellValueFactory(new PropertyValueFactory("barCode"));
        nomColum.setCellValueFactory(new PropertyValueFactory("name"));
        remiseColum.setCellValueFactory(new PropertyValueFactory("remise"));
        categorieColum.setCellValueFactory(new PropertyValueFactory("categorie"));
        qteColumn.setCellValueFactory(new PropertyValueFactory("quantity"));
        prixTtcColum.setCellValueFactory(new PropertyValueFactory("prixTTC"));
        plusOneQte.setCellValueFactory(new PropertyValueFactory<>("addQte"));
        minusOneQte.setCellValueFactory(new PropertyValueFactory<>("reduceQte"));

    }

    private void fillCategorieComboBox() {
        cbCategorie.getItems().clear();
        cbCategorie.getItems().addAll(TemporalyData.categories);
        cbCategorie.valueProperty().addListener((observable, oldValue, newValue) -> {
            List<Produit> produits = observable.getValue().getProduits();
            fillTableView(produits);
        });
    }

    private void fillFournisseurComboBox() {
        cbFournisseur.getItems().clear();
        cbFournisseur.getItems().addAll(TemporalyData.fournisseurs);
//        cbFournisseur.setValue(fournisseurs.get(0));
      //  fillTableView(TemporalyData.fournisseurs.get(0).getProduits());
        cbFournisseur.valueProperty().addListener((observable, oldValue, newValue) -> {
            List<Produit> produits = observable.getValue().getProduits();
            fillTableView(produits);
        });
    }

    private void fillTableView(List<Produit> produits) {
        if (produits != null) {
            ProduitUIList produitUIList = new ProduitUIList(produits);
            TableView.setItems(produitUIList.getProduitUIS());
        }
    }

    public void addProduit(ActionEvent actionEvent) {
        if (controller != null) {
            NouveauProduitController.controller = controller;
            controller.loadScene(Controller.NOUVEAU_PRODUIT_URL);

        }
    }

    public void addCategorie(ActionEvent actionEvent) {

    }


    //UI class from Entities
    public class ProduitUI {
        private int id;
        private SimpleStringProperty barCode;
        private SimpleStringProperty name;
        private SimpleStringProperty categorie;
        private SimpleIntegerProperty quantity;
        private SimpleDoubleProperty prixHT;
        private SimpleDoubleProperty prixTTC;
        private SimpleDoubleProperty remise;
        private Button addQte;
        private Button reduceQte;

        ProduitUI(Produit produit) {

            addQte = new Button("Modifier");
            reduceQte = new Button("Supprimer");
            barCode = new SimpleStringProperty(produit.getBarcode());
            name = new SimpleStringProperty(produit.getNom());
            categorie = new SimpleStringProperty(produit.getCategorie().getName());
            quantity = new SimpleIntegerProperty(produit.getQuantity());
            prixHT = new SimpleDoubleProperty(produit.getPurchasePriceHT());
            prixTTC = new SimpleDoubleProperty(produit.getPurchasePriceTTC());
            remise = new SimpleDoubleProperty(produit.getDiscount());
            this.id = produit.getId();

        }

        public Button getAddQte() {
            return addQte;
        }

        public void setAddQte(Button addQte) {
            this.addQte = addQte;
        }

        public Button getReduceQte() {
            return reduceQte;
        }

        public void setReduceQte(Button reduceQte) {
            this.reduceQte = reduceQte;
        }

        public double getPrixHT() {
            return prixHT.get();
        }

        public SimpleDoubleProperty prixHTProperty() {
            return prixHT;
        }

        public void setPrixHT(double prixHT) {
            this.prixHT.set(prixHT);
        }

        public double getPrixTTC() {
            return prixTTC.get();
        }

        public SimpleDoubleProperty prixTTCProperty() {
            return prixTTC;
        }

        public void setPrixTTC(double prixTTC) {
            this.prixTTC.set(prixTTC);
        }

        public double getRemise() {
            return remise.get();
        }

        public SimpleDoubleProperty remiseProperty() {
            return remise;
        }

        public void setRemise(double remise) {
            this.remise.set(remise);
        }


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getBarCode() {
            return barCode.get();
        }

        public SimpleStringProperty barCodeProperty() {
            return barCode;
        }

        public void setBarCode(String barCode) {
            this.barCode.set(barCode);
        }

        public String getName() {
            return name.get();
        }

        public SimpleStringProperty nameProperty() {
            return name;
        }

        public void setName(String name) {
            this.name.set(name);
        }

        public String getCategorie() {
            return categorie.get();
        }

        public SimpleStringProperty categorieProperty() {
            return categorie;
        }

        public void setCategorie(String categorie) {
            this.categorie.set(categorie);
        }

        public int getQuantity() {
            return quantity.get();
        }

        public SimpleIntegerProperty quantityProperty() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity.set(quantity);
        }

    }

    //Class to match Product with Product
    public class ProduitUIList {
        private ObservableList<ProduitUI> produitUIS;
        private List<Produit> produits;

        ProduitUIList(List<Produit> produits) {
            this.produits = produits;
            produitUIS = FXCollections.observableArrayList();
            for (Produit produit : produits) {
                ProduitUI p = new ProduitUI(produit);
                p.addQte.setOnMouseClicked(event -> {
                    addOneProduct(produit, p);
                });
                p.reduceQte.setOnMouseClicked(event -> {
                    removeOneProduct(produit, p);

                });
                produitUIS.add(p);
            }
        }

        ObservableList<ProduitUI> getProduitUIS() {
            return produitUIS;
        }

        public void setProduitUIS(ObservableList<ProduitUI> produitUIS) {
            this.produitUIS = produitUIS;
        }

        public List<Produit> getProduits() {
            return produits;
        }

        public void setProduits(ArrayList<Produit> produits) {
            this.produits = produits;
        }

        private void addOneProduct(Produit produit, ProduitUI p) {
            ProduitUpdateController.produit = produit ;
            ProduitUpdateController.controller = controller ;
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/ProduitUpdateScreen.fxml"));
                Stage thisSatge = (Stage) nomColum.getTableView().getScene().getWindow();
                Controller.showDialog(thisSatge, root , 600 , 600);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        private void removeOneProduct(Produit produit, ProduitUI p) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Etes vous sur de vouloir supprimer le produit  ?", ButtonType.YES, ButtonType.CANCEL);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                TemporalyData.produits.remove(produit);
                ProduitDAO produitDAO = new ProduitDAO();
                produitDAO.delete(produit.getId());
            }
        }
    }


}

