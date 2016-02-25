package cinderella.Handler.Databasehandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Eric and Felix Brandt on 28.10.2015.
 */
public class EntityQuery extends Query {

	  
    
    /**
     * returns all tools in the MES as a List
     * @return
     */
    @Override
    public ResultSet getResult() {
    	prepareQuery();    	
        return getResultSet();
    }

    @Override
    public void setDBInterface(DatabaseInterface databaseInterface) {
        this.setDbI(databaseInterface);
    }
    @Override
    public void prepareQuery(){
    	try{
        setPreparedStatement(getDbI().getConnection().prepareStatement("select tool, state from tool"));
        setResultSet(getPreparedStatement().executeQuery());
        getDbI().getConnection().close();
    	}catch(SQLException e){}
    }
}
