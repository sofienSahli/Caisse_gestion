package controllers;

import Utils.TemporalyData;
import entities.Categorie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import services.CategorieDAO;

public class NouvelleCategorieController {
    @FXML
    public TextField nomCategorie;
   public static Controller controller ;
    public void AjouterCategorie(ActionEvent actionEvent) {
        Categorie categorie = new Categorie();
        categorie.setName(nomCategorie.getText());
        CategorieDAO categorieDAO = new CategorieDAO();

        TemporalyData.categories.add(categorieDAO.add(categorie));
        controller.loadScene(Controller.PRODUIT_URL);

    }
}
