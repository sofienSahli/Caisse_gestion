package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Fournisseur implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom, mail, tel, address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "provider_id")
    List<Produit> produits;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fournisseur that = (Fournisseur) o;
        return id == that.id &&
                Objects.equals(nom, that.nom);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, nom);
    }

    @Override
    public String toString() {
        return this.nom;
    }

    public Fournisseur() {
        produits = new ArrayList<>() ;
    }

    public Fournisseur(int id, String nom, String mail, String tel) {
        this.id = id;
        this.nom = nom;
        this.mail = mail;
        this.tel = tel;
        produits = new ArrayList<>() ;

    }

    public Fournisseur(int id, String nom, String mail, String tel, ArrayList<Produit> produits) {
        this.id = id;
        this.nom = nom;
        this.mail = mail;
        this.tel = tel;
        this.produits = produits;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public List<Produit> getProduits() {
        return produits;
    }

    public void setProduits(ArrayList<Produit> produits) {
        this.produits = produits;
    }
}
