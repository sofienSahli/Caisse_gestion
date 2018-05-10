package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Caisse implements Serializable {
    private ArrayList<Produit> produits;
    private double totalCaisse;

    private int id;
    private Timestamp date;

    public Caisse(ArrayList<Produit> produits) {
        this.produits = produits;
        date = new Timestamp(System.currentTimeMillis());
        if (!produits.isEmpty()) {
            calculateTotalCaisse();
        }
    }

    private void calculateTotalCaisse() {
        totalCaisse = 0.0 ;
        for (Produit p : produits) {
            double prixTotalProd = p.getQuantity() * p.getPurchasePriceTTC();
            totalCaisse += prixTotalProd;
        }
    }

    public void addProductToCaisse(Produit p) {
        if(!produits.contains(p)){
            produits.add(p);
            calculateTotalCaisse();
        }else {
            produits.remove(p);
            addProductToCaisse(p);
        }

    }

    public void removeProductFromCaisse(Produit p) {
        produits.remove(p);
        calculateTotalCaisse();
    }

    public ArrayList<Produit> getProduits() {
        return produits;
    }

    public void setProduits(ArrayList<Produit> produits) {
        this.produits = produits;
    }

    public double getTotalCaisse() {
        return totalCaisse;
    }

    public void setTotalCaisse(double totalCaisse) {
        this.totalCaisse = totalCaisse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
