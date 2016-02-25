package cinderella.Handler.Databasehandler;


import cinderella.Beans.User;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Created by note on 06.12.2015.
 */
public class ScheduleEventQuery implements StatementInterface {
    PreparedStatement preparedStatement;
    DatabaseInterface dbI;
    ResultSet resultSet;
    String toolName;
    String dateTime;
    String event;
    Stage stage;

    /**
     * 
     * @param toolName : tool for which a certain event should be started
     * @param datetime : day on which the event should start
     * @param event    : GoMAINT: or GoIDLE:
     * @param stage	   : needed for closing the scene in case of failure
     */
    public ScheduleEventQuery(String toolName, String datetime, String event,Stage stage) {
        this.toolName = toolName;
        dateTime = datetime;
        this.event = event;
        this.stage = stage;
    }

    @Override
    public ResultSet getResult() {
        dbI = new PostgresConnect();
        try {
            Timestamp ts = Timestamp.valueOf(dateTime);

            preparedStatement = dbI.getConnection().prepareStatement("insert into schedevents(entity,event,due,requestor)\n" +
                    "values(?,?,?,?)");
            preparedStatement.setString(1, toolName);
            preparedStatement.setTimestamp(3, ts);
            preparedStatement.setString(2, event);
            preparedStatement.setString(4, User.getUser());
            preparedStatement.execute();
            dbI.getConnection().close();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setHeaderText("Erfolgreich eingetragen!");
            alert.setContentText(toolName +" "+ dateTime + " " + event + '\n' + "wurde eingetragen!");
            alert.show();
            stage.close();
            return null;
        } catch (SQLException e) {
        	 Alert alert = new Alert(AlertType.INFORMATION);
             alert.setHeaderText("Fehler beim Eintragen!");
             alert.show();
             e.printStackTrace();
             stage.close();
        }
        return null;
    }

    @Override
    public void setDBInterface(DatabaseInterface dbInterface) {

    }
}
