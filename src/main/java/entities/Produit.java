package entities;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
public class Produit extends RecursiveTreeObject<Produit> implements Serializable{

    private String nom;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Product_id")
    private int id;
    private String barcode;
    private int discount;
    private Date date;
    private double purchacesPrice;
    private double purchagcesTax;
    private int quantity;
    private String reference;
    private double sellTax;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Categorie categorie;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "provider_id")
    private Fournisseur fournisseur;
    private double purchasePriceHT;
    private double purchasePriceTTC;

    public Produit() {

    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getPurchacesPrice() {
        return purchacesPrice;
    }

    public void setPurchacesPrice(double purchacesPrice) {
        this.purchacesPrice = purchacesPrice;
    }

    public double getPurchagcesTax() {
        return purchagcesTax;
    }

    public void setPurchagcesTax(double purchagcesTax) {
        this.purchagcesTax = purchagcesTax;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public double getSellTax() {
        return sellTax;
    }

    public void setSellTax(double sellTax) {
        this.sellTax = sellTax;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

    public double getPurchasePriceHT() {
        return purchasePriceHT;
    }

    public void setPurchasePriceHT(double purchasePriceHT) {
        this.purchasePriceHT = purchasePriceHT;
    }

    public double getPurchasePriceTTC() {
        return purchasePriceTTC;
    }

    public void setPurchasePriceTTC(double purchasePriceTTC) {
        this.purchasePriceTTC = purchasePriceTTC;
    }



    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    @Override
    public String toString() {
        return "Produit{" +
                "nom=" + nom +
                ", id=" + id +
                ", barcode='" + barcode + '\'' +
                ", discount=" + discount +
                ", date=" + date +
                ", purchacesPrice=" + purchacesPrice +
                ", purchagcesTax=" + purchagcesTax +
                ", quantity=" + quantity +
                ", reference='" + reference + '\'' +
                ", sellTax=" + sellTax +
                ", categorie=" + categorie +
                ", fournisseur=" + fournisseur +
                ", purchasePriceHT=" + purchasePriceHT +
                ", purchasePriceTTC=" + purchasePriceTTC +
                '}';
    }
}
