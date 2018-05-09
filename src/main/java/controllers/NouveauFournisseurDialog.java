package controllers;

import Utils.TemporalyData;
import com.jfoenix.controls.JFXTextField;
import entities.Fournisseur;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import services.FournisseurDAO;


public class NouveauFournisseurDialog {
    public Circle innerCircle;
    public Circle middleCircle;
    public Circle outerCircle;
    public JFXTextField numeroFournisseur;
    public JFXTextField addressFournisseur;
    public JFXTextField mailFournisseur;
    public JFXTextField nomFournisseur;
    public Label label;

    public void update(ActionEvent actionEvent) {
        Controller.fadIn(innerCircle, 0.0, 1.0);
        Controller.fadIn(middleCircle, 0.0, 1.0);
        Controller.fadIn(outerCircle, 0.0, 1.0);
        Controller.rotate(innerCircle, 360, 0);
        Controller.rotate(middleCircle, 0, 360);
        Controller.rotate(outerCircle, 360, 0);
        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setMail(mailFournisseur.getText());
        fournisseur.setNom(nomFournisseur.getText());
        fournisseur.setTel(numeroFournisseur.getText());
        fournisseur.setAddress(addressFournisseur.getText());
        saveData(fournisseur);
    }

    public void cancel(ActionEvent actionEvent) {
        closeScene();
    }

    public synchronized void initialize() {

    }

    private void saveData(final Fournisseur fournisseur) {
        Task task = new Task() {
            @Override
            protected Object call() {
                System.out.println("runing");
                FournisseurDAO fournisseurDAO = new FournisseurDAO();
                TemporalyData.fournisseurs.add(fournisseurDAO.add(fournisseur ));
                return null;
            }
        };
        task.setOnSucceeded(event -> closeScene());
        new Thread(task).start();

    }

    private void closeScene() {

        Stage stage = (Stage) nomFournisseur.getScene().getWindow();
        stage.close();
    }


}
