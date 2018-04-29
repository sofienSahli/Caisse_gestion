package entities;

import java.util.Objects;

public class Fournisseur {
    private int id;
    private String nom, mail, tel;

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
        return "Fournisseur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", mail='" + mail + '\'' +
                ", tel='" + tel + '\'' +
                '}';
    }

    public Fournisseur() {
    }

    public Fournisseur(int id, String nom, String mail, String tel) {
        this.id = id;
        this.nom = nom;
        this.mail = mail;
        this.tel = tel;
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
}
