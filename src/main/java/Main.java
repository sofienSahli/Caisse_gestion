import entities.Produit;
import iservices.ProduitDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
     /*   Parent root = FXMLLoader.load(getClass().getResource("../views/sample.fxml"));
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        Scene scene = new Scene(root, screenBounds.getWidth(), screenBounds.getMaxX());
        scene.getStylesheets().add("views/Main.css");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();*/
        ProduitDAO produitDAO = new ProduitDAO() ;
        Produit produit = new Produit() ;
        produit.setId(55);
        System.out.println(produitDAO.findById(produit));
    }


    public static void main(String[] args) {
        launch(args);
    }
}
