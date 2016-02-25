package cinderella.Handler.Databasehandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by note on 28.11.2015.
 */
public class ToolWipQuery implements StatementInterface {
    DatabaseInterface dbi;
    PreparedStatement preparedStatement;
    Connection connection;

    @Override
    public ResultSet getResult() {

        try {
            dbi = new PostgresConnect();
            ResultSet resultSet;
            preparedStatement = dbi.getConnection().prepareStatement( "select p.tool, SUM(l.pieces) from lot l,ptime p\n"+
            		 "where l.state = 'WAIT' AND l.disptool is null AND p.product = l.product AND p.route = l.route AND p.oper = l.oper\n"+
            		 "group by p.tool");
            resultSet = preparedStatement.executeQuery();
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void setDBInterface(DatabaseInterface dbInterface) {

    }
}
