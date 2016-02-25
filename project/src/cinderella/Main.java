package cinderella;

import cinderella.Controller.ViewController.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application {
	
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/resources/LoginView.fxml"));
        loader.setController(new LoginController(primaryStage));
        Parent root = loader.load();
        primaryStage.initStyle(StageStyle.UNDECORATED);
        Scene sc = new Scene(root);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        sc.getStylesheets().add("/resources/style.css");
        sc.setFill(null);
        primaryStage.setScene(sc);
        primaryStage.show();


    }
}
