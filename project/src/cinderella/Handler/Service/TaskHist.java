package cinderella.Handler.Service;

import cinderella.Handler.Databasehandler.HistQuery;
import cinderella.Handler.Databasehandler.PostgresConnect;
import cinderella.Handler.Databasehandler.StatementInterface;
import javafx.concurrent.Task;

import java.sql.ResultSet;

/**
 * Created by note on 08.11.2015.
 */
public class TaskHist extends Task {
    ResultSet rs;
    String name;

    public TaskHist(String name) {
        this.name = name;
    }

    @Override
    protected Object call() throws Exception {
        StatementInterface st = new HistQuery(name);
        st.setDBInterface(new PostgresConnect());
        rs = st.getResult();
        String ausg = "";
        while (rs.next()) {
            ausg += rs.getString(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + "\n";
            updateMessage(ausg);
        }

        // ta.clear();
        // ta.appendText(ausg);
        //System.out.println(ausg);

        return null;
    }


}
