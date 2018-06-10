package entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
public class HistoriqueCaisse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int  id ;
    double total_caisse ;
    Date date ;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "caisse_id")
    List<SoldProduct> produits;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotal_caisse() {
        return total_caisse;
    }

    public void setTotal_caisse(double total_caisse) {
        this.total_caisse = total_caisse;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<SoldProduct> getProduits() {
        return produits;
    }

    public void setProduits(List<SoldProduct> produits) {
        this.produits = produits;
    }
}
