package Utils;

import entities.Categorie;
import entities.Fournisseur;
import entities.Produit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import services.CategorieDAO;
import services.FournisseurDAO;
import services.ProduitDAO;

public class TemporalyData {
    public static ObservableList<Produit> produits;
    public static ObservableList<Fournisseur> fournisseurs;
    public static ObservableList<Categorie> categories;

    public synchronized static void loadData() {

        FournisseurDAO fournisseurDAO = new FournisseurDAO();
        fournisseurs = FXCollections.observableArrayList();
        fournisseurs.addAll(fournisseurDAO.findAll());
        CategorieDAO categorieDAO = new CategorieDAO();
        categories = FXCollections.observableArrayList();
        categories.addAll(categorieDAO.findAll());

        ProduitDAO produitDAO = new ProduitDAO();
        produits = FXCollections.observableArrayList();
        produits.addAll(produitDAO.findAll());

    }

    public synchronized static void saveData() {
        FournisseurDAO fournisseurDAO = new FournisseurDAO();
        for (Fournisseur f :
                fournisseurs) {
            fournisseurDAO.update(f);
        }

        CategorieDAO categorieDAO = new CategorieDAO();
        for (Categorie c :
                categories) {
            categorieDAO.update(c);
        }

        ProduitDAO produitDAO = new ProduitDAO();
        for (Produit p :
                produits) {
            produitDAO.update(p);
        }
    }


}
