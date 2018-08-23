package entities;

import javax.persistence.*;

@Entity
public class SoldProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String codabar;
    String nom;
    String reference;
    double prixVente;
    double prixSansDiscount ;
    int discount;
    int qunatity;
    int productID ;

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "caisse_id")
    private HistoriqueCaisse caisse;

    public HistoriqueCaisse getCaisse() {
        return caisse;
    }

    public void setCaisse(HistoriqueCaisse caisse) {
        this.caisse = caisse;
    }

    public double getPrixSansDiscount() {
        return prixSansDiscount;
    }

    public void setPrixSansDiscount(double prixSansDiscount) {
        this.prixSansDiscount = prixSansDiscount;
    }

    public int getQunatity() {
        return qunatity;
    }

    public void setQunatity(int qunatity) {
        this.qunatity = qunatity;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCodabar() {
        return codabar;
    }

    public void setCodabar(String codabar) {
        this.codabar = codabar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public double getPrixVente() {
        return prixVente;
    }

    public void setPrixVente(double prixVente) {
        this.prixVente = prixVente;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return nom + "\t  Quantite : " + this.qunatity;
    }
}
