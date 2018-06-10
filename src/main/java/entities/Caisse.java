package entities;

import Utils.TemporalyData;
import services.ProduitDAO;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Caisse implements Serializable {
    private ArrayList<SoldProduct> produits;
    private double totalCaisse;

    private int id;
    private Timestamp date;

    public Caisse(ArrayList<SoldProduct> produits) {
        this.produits = produits;
        date = new Timestamp(System.currentTimeMillis());
        if (!produits.isEmpty()) {
            calculateTotalCaisse();
        }
    }

    private void calculateTotalCaisse() {
        totalCaisse = 0.0;
        for (SoldProduct p : produits) {

            totalCaisse += p.getPrixVente() * p.getQunatity();
        }
    }

    public void addProductToCaisse(SoldProduct p) {
        if (!produits.contains(p)) {
            produits.add(p);
            calculateTotalCaisse();
        } else {
            produits.remove(p);
        }

    }

    public void removeProductFromCaisse(SoldProduct p) {
        produits.remove(p);
        calculateTotalCaisse();
    }

    public ArrayList<SoldProduct> getProduits() {
        return produits;
    }

    public void setProduits(ArrayList<SoldProduct> produits) {
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

    public void clear() {
        this.produits.clear();
        calculateTotalCaisse();
    }

    public void validateCaisse() {
        for (SoldProduct p : produits) {
                ProduitDAO produitDAO = new ProduitDAO();
                Produit prod = produitDAO.findById(p.getId());
                prod.setQuantity(prod.getQuantity() - p.getQunatity());
                produitDAO = new ProduitDAO();
                produitDAO.update(prod);

        }
    }

    @Override
    public String toString() {

        String s = " \n \t \t  CONSEIL BEAUTE \n \t \t -----------------  ";
        for (SoldProduct p : produits) {
            s = s + "\n" + p.getNom() + "\t" + "Quantite : " + p.getQunatity()  +"Prix unitaire: " + String.format("%.3f",  p.getPrixVente())+"\t"
                    + String.format("%.3f",  p.getPrixVente() * p.getQunatity()) + "Dt";
        }

        s = s + " \n \t \t -------------------";
        s = s + "\n Total Caisse : " + String.format("%.3f",  totalCaisse)+ " DT ";
        s = s + "\n Date : " + this.getDate() + "\n";
        s = s + "\t Merci pour votre visite ";
        return String.valueOf(s);
    }
}
