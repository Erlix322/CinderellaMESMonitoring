package cinderella.Handler.Databasehandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * returns lotid, enteroper and priority for certain tools
 * 
 * Created by Eric and Felix on 20.02.2016
 * 
 */
public class DispatchListQuery extends Query {
   
	String limit;
    String offset;
    String orderclause;
    String wherestring;

    public DispatchListQuery() {
        limit = "50";
        offset = "0";
        wherestring = "";
    }

    /**
     * 
     * @param limit  defines how many entities should be fetched
     * @param offset 
     * @param name	 toolname which you're looking for
     */
    public DispatchListQuery(String limit, String offset, String name) {
        this.limit = limit;
        this.offset = offset;
        wherestring = name;
        orderclause = "startdate";
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


	@Override
	public void prepareQuery() {
		 try { 
			 this.setPreparedStatement(getDbI().getConnection().prepareStatement(" select l.lotid, l.enteroper,l.priority from lot l,ptime p\n" +
	                    "                    where l.state = 'WAIT' AND l.disptool is null AND p.tool = ? AND p.product = l.product AND p.route = l.route AND p.oper = l.oper\n" +
	                    "                    order by l.priority,l.enteroper"+
	                    "					 LIMIT ? OFFSET ?"));

	            getPreparedStatement().setString(1, wherestring);
	            getPreparedStatement().setInt(2, Integer.valueOf(limit));
	            getPreparedStatement().setInt(3, Integer.valueOf(offset));
	            setResultSet(getPreparedStatement().executeQuery());
	            getDbI().getConnection().close();
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		
	}
}
