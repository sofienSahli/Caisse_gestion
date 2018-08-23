package controllers;

import Utils.TemporalyData;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import entities.Caisse;
import entities.PriceCalculator;
import entities.Produit;
import entities.SoldProduct;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import services.ProduitDAO;

import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.event.PrintJobAdapter;
import javax.print.event.PrintJobEvent;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CaisseController {
    public static Controller controller;
    @FXML
    public JFXTextField prixRemise;
    @FXML
    public JFXTextField remise;

    @FXML
    private Label quantity;

    @FXML
    private JFXTextField prixTTC;

    @FXML
    private Label totalCaisse;


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
    private JFXListView<SoldProduct> contenuCart;
    @FXML
    private Label nomProduit;
    @FXML
    public JFXTextField search;
    @FXML
    public JFXListView<Produit> stock;

    private Caisse currentCaisse;
    private Produit currentProduit;
    private int currentIndex;
    private SoldProduct currentSoldProduct;

    @FXML
    public void initialize() {
        setUpStockList(TemporalyData.produits);
        currentCaisse = new Caisse(new ArrayList<>());
        codabar.textProperty().addListener((observable, oldValue, newValue) -> {
            Produit p = TemporalyData.findIdByCodabar(newValue);
            if (p != null) {
                currentProduit = p;
                setUpProduit();
                enableButtons();
            }
        });


        contenuCart.setOnMouseClicked(e -> {
            currentSoldProduct = contenuCart.getSelectionModel().getSelectedItem();
            setUpSoldProduit();
        });
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.equals("")) {
                List<Produit> list = makeSearch(newValue);
                if (list != null)
                    setUpStockList(list);
            } else {
                setUpStockList(TemporalyData.produits);
            }

        });
        remise.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.equals("")) {
                double prixSansRemise = parsePrice(prixTTC.getText());
                int remise = Integer.parseInt(newValue);
                double prixRem = PriceCalculator.calculerPrixRemise(prixSansRemise, remise);
                String s = String.format("%.3f", prixRem);
                prixRemise.setText(s);
            }
        });
    }

    private void setUpSoldProduit() {
        currentIndex = 1;
        nomProduit.setText(currentSoldProduct.getNom());
        quantity.setText(currentIndex + "");
        tfNomProduit.setText(currentSoldProduct.getNom());
        String s1 = String.format("%.3f", PriceCalculator.prixVente(currentSoldProduct.getPrixVente(), currentSoldProduct.getDiscount()));
        prixTTC.setText(s1);

    }

    private double parsePrice(String price) {
        NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
        Number number = null;
        try {
            number = format.parse(price);
            return number.doubleValue();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    private List<Produit> makeSearch(String searchQuery) {
        List<Produit> list;
        ProduitDAO produitDAO = new ProduitDAO();
        list = produitDAO.findByString(searchQuery);

        return list;
    }

    private void setUpProduit() {
        currentIndex = 1;
        nomProduit.setText(currentProduit.getNom());
        quantity.setText(currentIndex + "");
        tfNomProduit.setText(currentProduit.getNom());
        String s1 = String.format("%.3f", PriceCalculator.prixVente(currentProduit.getPurchasePriceTTC(), currentProduit.getSellTax()));
        prixTTC.setText(s1);
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

    // Added If case to recalculate price with Discount made for owner to client
    public void addTocart(MouseEvent actionEvent) {
        currentCaisse.addProductToCaisse(soldProduct());
        String s1 = String.format("%.3f", currentCaisse.getTotalCaisse());
        totalCaisse.setText(s1);
        contenuCart.setItems(FXCollections.observableArrayList(currentCaisse.getProduits()));
        disableButtons();
        currentIndex = 1;
        quantity.setText(currentIndex + " ");
        clearInterface();
    }

    private void clearInterface() {
        codabar.clear();
        tfNomProduit.clear();
        remise.clear();
        prixTTC.clear();
        prixRemise.clear();
        nomProduit.setText("Aucune Selection");
    }

    public void supprimer(ActionEvent actionEvent) {
        currentCaisse.removeProductFromCaisse(currentSoldProduct);
        String s1 = String.format("%.3f", currentCaisse.getTotalCaisse());
        totalCaisse.setText(s1);
        contenuCart.setItems(FXCollections.observableArrayList(currentCaisse.getProduits()));
    }

    private SoldProduct soldProduct() {
        SoldProduct soldProduct = new SoldProduct();
        soldProduct.setProductID(currentProduit.getId());
        soldProduct.setCodabar(currentProduit.getBarcode());
        soldProduct.setReference(currentProduit.getReference());
        soldProduct.setQunatity(currentIndex);
        soldProduct.setNom(currentProduit.getNom());
        double prixVente = PriceCalculator.prixVente(currentProduit.getPurchasePriceTTC(), currentProduit.getSellTax());
        soldProduct.setPrixSansDiscount(prixVente);

        if (!remise.getText().equals("")) {
            Integer discount = Integer.parseInt(remise.getText());
            soldProduct.setDiscount(discount);
            Double p = this.parsePrice(prixRemise.getText());
            soldProduct.setPrixVente(p);
        } else {
            soldProduct.setDiscount(0);
            soldProduct.setPrixVente(prixVente);
        }

        return soldProduct;
    }

    public void setUpStockList(List<Produit> produits) {
        stock.setItems(FXCollections.observableArrayList(produits));
        stock.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                currentProduit = newValue;
                setUpProduit();
                codabar.setText(newValue.getBarcode());

            }
        });
    }

    public void clearAll(ActionEvent actionEvent) {
        currentCaisse.clear();
        String s = String.format("%.3f", currentCaisse.getTotalCaisse());
        totalCaisse.setText(s);
        contenuCart.setItems(FXCollections.observableArrayList(currentCaisse.getProduits()));

    }

    public void printRecipient(ActionEvent actionEvent) {
        print();
        //  System.out.println(currentCaisse.toString());
        clearAll(null);
    }

    private void print() {
        String defaultPrinter =
                PrintServiceLookup.lookupDefaultPrintService().getName();
        System.out.println("Default printer: " + defaultPrinter);
        PrintService service = PrintServiceLookup.lookupDefaultPrintService();

        // prints the famous hello world! plus a form feed
        InputStream is = null;
        try {
            String printing = currentCaisse.toString() + "\f";
            is = new ByteArrayInputStream(printing.getBytes("UTF8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
        pras.add(new Copies(1));

        DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
        Doc doc = new SimpleDoc(is, flavor, null);

        DocPrintJob job = service.createPrintJob();

        PrintJobWatcher pjw = new PrintJobWatcher(job);
        try {
            job.print(doc, pras);
        } catch (PrintException e) {
            e.printStackTrace();
        }
        pjw.waitForDone();
        try {
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

class PrintJobWatcher {
    boolean done = false;

    PrintJobWatcher(DocPrintJob job) {
        job.addPrintJobListener(new PrintJobAdapter() {
            public void printJobCanceled(PrintJobEvent pje) {
                allDone();
            }

            public void printJobCompleted(PrintJobEvent pje) {
                allDone();
            }

            public void printJobFailed(PrintJobEvent pje) {
                allDone();
            }

            public void printJobNoMoreEvents(PrintJobEvent pje) {
                allDone();
            }

            void allDone() {
                synchronized (PrintJobWatcher.this) {
                    done = true;
                    System.out.println("Printing done ...");
                    PrintJobWatcher.this.notify();
                }
            }
        });
    }

    public synchronized void waitForDone() {
        try {
            while (!done) {
                wait();
            }
        } catch (InterruptedException e) {
        }
    }
}

