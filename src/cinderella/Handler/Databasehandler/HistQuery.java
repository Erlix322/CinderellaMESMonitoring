package cinderella.Handler.Databasehandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Eric and Felix on 08.11.2015.
 */
public class HistQuery extends Query {

 
    String name;
    /**
     * @param name toolname
     * @throws SQLException 
     */
    public HistQuery(String name) {
      this.name = name;
    }

    @Override
    public ResultSet getResult() {
    	prepareQuery();
        return getResultSet();
    }

    @Override
    public void setDBInterface(DatabaseInterface dbInterface) {
        this.setDbI(dbInterface);
    }
//

	@Override
	public void prepareQuery() {
		try{
		this.setPreparedStatement(this.getDbI().getConnection().prepareStatement("select timestamp,l.product,l.lotid,note from events24\n" +
                "JOIN lot l\n" +
                "ON l.lotid=entity\n" +
                "where note like ? AND event like 'START%' AND timestamp > '2016-02-14'"));
		this.getPreparedStatement().setString(1, "%" + name + "%");
        this.setResultSet(getPreparedStatement().executeQuery());
        System.out.println("huhu");
        this.getDbI().getConnection().close();
		}catch(SQLException e){e.printStackTrace();}
	}
}
