package cinderella.Handler.Service;

import cinderella.Controller.ViewController.EntityController;
import cinderella.Controller.ViewController.ViewController;
import cinderella.Handler.Databasehandler.EntityQuery;
import cinderella.Handler.Databasehandler.PostgresConnect;
import cinderella.Handler.Databasehandler.StatementInterface;
import javafx.concurrent.Task;

import java.sql.ResultSet;
import java.util.Map;

/**
 * Created by note on 07.11.2015.
 */

public class State_Thread extends Task {
    ViewController vc;
    EntityController et;
    Map<String, EntityController> map;
    private ResultSet rs;

    //Constructor
    public State_Thread() {

    }

    @Override
    protected Object call() throws Exception {
        //Send request to the database
        StatementInterface st = new EntityQuery();
        st.setDBInterface(new PostgresConnect());


        rs = st.getResult();
        //System.out.println("hallo");
        //fetching results
        while (rs.next()) {

            et = Tool_Service.map.get(rs.getString(1));

            et.state.setText(rs.getString(2));
            //updates the pane of certain "EntityController" - panels in the MainWindow
            if (rs.getString(2).equals("UKN")) {
                et.innerPaneEntityView.setId("UKN");
            }
            if (rs.getString(2).equals("PROC")) {
                et.innerPaneEntityView.setId("PROC");
            }
            if (rs.getString(2).equals("IDLE")) {
                et.innerPaneEntityView.setId("IDLE");
            }
            if(rs.getString(2).equals("DOWN")){
            	et.innerPaneEntityView.setId("DOWN");
            }
            if(rs.getString(2).equals("MAINT")){
            	et.innerPaneEntityView.setId("MAINT");
            }
           


        }
        System.out.println("Updated!");
        return null;
    }
}
