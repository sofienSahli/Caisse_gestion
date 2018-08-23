package controllers;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import entities.HistoriqueCaisse;
import entities.SoldProduct;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import services.HistoriqueCaisseDAO;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class HistoriqueCaisseController {

    @FXML
    private DatePicker end_date;

    @FXML
    private TableColumn<SoldProduct, String> nom_column;

    @FXML
    private Label date_caisse;

    @FXML
    private JFXListView<HistoriqueCaisse> list_caisses;

    @FXML
    private Label numero_caisse;

    @FXML
    private TableColumn<SoldProduct, Integer> remise_column;

    @FXML
    private TableColumn<SoldProduct, String> codabar_column;

    @FXML
    private TableColumn<SoldProduct, Double> prix_vetnte_column;

    @FXML
    private TableColumn<SoldProduct, Double> prix_origine_column;

    @FXML
    private Label total_caisse;

    @FXML
    private JFXTextField recherche_produit;

    @FXML
    private TableView<SoldProduct> liste_produit;

    @FXML
    private TableColumn<SoldProduct, String> reference_column;

    @FXML
    private DatePicker start_date;

    @FXML
    private JFXTextField search_field;

    @FXML
    public void initialize() {
        loadCaisseHistorique();
        setUptableView();
    }

    private void loadCaisseHistorique() {
        HistoriqueCaisseDAO historiqueCaisseDAO = new HistoriqueCaisseDAO();
        fillListeView(historiqueCaisseDAO.findAll());
        list_caisses.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setUpTableViewData(newValue.getProduits());
            numero_caisse.setText("Caisse numero : " + newValue.getId() + "\t");
            date_caisse.setText("Date : " + newValue.getDate().toString() + "\t");
            total_caisse.setText("Total : " + newValue.getTotal_caisse() + "\t");
        });
        search_field.textProperty().addListener((observable, oldValue, newValue) -> {
            HistoriqueCaisseDAO historiqueCaisseDAO1 = new HistoriqueCaisseDAO();
            if (!newValue.equals(oldValue) && !newValue.equals("")) {
                fillListeView(historiqueCaisseDAO1.findByString(newValue));
            } else {
                fillListeView(historiqueCaisseDAO1.findAll());

            }
        });
    }

    private void fillListeView(List<HistoriqueCaisse> list) {
        list_caisses.getItems().clear();
        list_caisses.setItems(FXCollections.observableArrayList(list));
    }

    private void setUptableView() {
        nom_column.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getNom()));
        reference_column.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getReference()));
        remise_column.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getDiscount()));
        codabar_column.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getCodabar()));
        prix_vetnte_column.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getPrixVente()));
        prix_origine_column.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getPrixSansDiscount()));
    }

    private void setUpTableViewData(List<SoldProduct> data) {
        liste_produit.setItems(FXCollections.observableArrayList(data));
    }

    public void filtrer(MouseEvent mouseEvent) {
        LocalDate start = start_date.getValue();
        LocalDate end = end_date.getValue();
        HistoriqueCaisseDAO historiqueCaisseDAO = new HistoriqueCaisseDAO();
        fillListeView(historiqueCaisseDAO.findByDate(Date.valueOf(start), Date.valueOf(end)));

    }
}
