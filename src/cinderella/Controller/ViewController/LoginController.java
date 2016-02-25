package cinderella.Controller.ViewController;


import cinderella.Main;
import cinderella.Beans.User;
import cinderella.Controller.Factories.ViewFactory2;
import cinderella.Handler.Databasehandler.DatabaseInterface;
import cinderella.Handler.Databasehandler.PostgresConnect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class LoginController extends ViewController {
    @FXML
    TextField username;
    
    @FXML
    PasswordField password;
    
    @FXML
    Pane innerPaneLogin;

    DatabaseInterface dbI;
    public LoginController(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void login(ActionEvent event) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, IOException  {
    	System.out.println(username.getText() + password.getText());
    	new User(username.getText(),password.getText());
    	      
    	System.out.println("LoginLogik!");
        Stage s = new Stage();
        FXMLLoader l = new FXMLLoader(Main.class.getResource("/resources/MainWindow.fxml"));
        
        ViewFactory2.registerView("mainWindow",MainWindow.class);
        VC v = ViewFactory2.getView("mainWindow",s);
        l.setController(v);
        Parent root = l.load();
        Scene sc = new Scene(root, Screen.getPrimary().getBounds().getWidth(), Screen.getPrimary().getBounds().getHeight());
        s.setScene(sc);
        s.show();                     
        stage.close();
    }


    @FXML
    public void close() {
        System.exit(0);
    }

    @Override
    public void initialize() {

    }
    
    private boolean isLoginValid(){
    	dbI = new PostgresConnect();  
    	dbI.getConnection();
    	return true;
    }
   
   
}
