package cinderella.Controller.ViewController;

import cinderella.Handler.Databasehandler.ScheduleEventQuery;
import cinderella.Handler.Databasehandler.StatementInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.SQLException;

/**
 * Created by note on 06.12.2015.
 */
public class ScheduleEventController implements VC {
    public String name;
    @FXML
    String Maint, Idle;
    @FXML
    Button submit;
    @FXML
    AnchorPane rootSchedEvent;
    @FXML
    DatePicker dueDate;
    @FXML
    TextField hours, minutes;
    @FXML
    ChoiceBox choiceBox;
Stage stage;
    ScheduleEventController(String name,Stage stage) {
        this.name = name;
        this.stage = stage;
    }

    public static VC getInstance(String name,Stage stage) throws SQLException {
        VC instance;
        instance = new ScheduleEventController(name,stage);
        return instance;
    }

    @FXML
    public void submit() {
        String event;
        String date;
        date = dueDate.getValue().toString() + " " + hours.getText() + ":" + minutes.getText() + ":" + "00";
        System.out.println(date);
        
        if (((String)choiceBox.getValue()).equals("GoMAINT:")) {
            event = "GoMAINT:";
        } else {
            event = "GoIDLE:";
        }
        System.out.println(name +" "+ date + " " + event);
        StatementInterface si = new ScheduleEventQuery(name, date, event,stage);
        si.getResult();
        
        
    }

    @FXML
    public void initialize() {
    	rootSchedEvent.getStylesheets().add("/resources/SchedEventView.css");
    }
}
