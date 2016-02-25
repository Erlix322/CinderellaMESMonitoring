package cinderella.Controller.ViewController;

import java.sql.SQLException;

import cinderella.Handler.Service.ThreadHistory;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Created by Eric and Felix Brandt on 08.11.2015.
 */
public class HistoryController extends ViewController implements VC {
    @FXML
    TextArea textArea;
    @FXML
    BorderPane pane;
    Thread td;
    private String name;

    public HistoryController(String name) {
        this.name = name;
    }


    public void loadList() {
        td = new ThreadHistory(name, textArea);
        td.start();
    }

    @Override
    public void initialize() {
        //textarea = new TextArea();
        Thread td = new ThreadHistory(name, textArea);
        td.start();
    }
    
    public static VC getInstance(String name) {
        VC instance;
        instance = (VC) new HistoryController(name);
        return instance;
    }
}
