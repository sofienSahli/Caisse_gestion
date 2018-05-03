package controllers;

import entities.Categorie;
import entities.Fournisseur;
import entities.Produit;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import services.CategorieDAO;
import services.FournisseurDAO;
import services.ProduitDAO;

import java.util.ArrayList;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
       /* Parent root = FXMLLoader.load(getClass().getResource("/sample.fxml"));
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        Scene scene = new Scene(root, screenBounds.getWidth(), screenBounds.getMaxX());
        scene.getStylesheets().add("Main.css");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    */
        ProduitDAO produitDAO = new ProduitDAO();
        ArrayList<Produit> produits = (ArrayList<Produit>) produitDAO.findAll();
        System.out.println(produits.get(0).getFournisseur().toString() +" \n" +produits.get(0).getCategorie().getName());

    }


    public static void main(String[] args) {
        launch(args);
    }
}
