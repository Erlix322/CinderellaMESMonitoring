package cinderella.Handler.Databasehandler;

import java.sql.ResultSet;

/**
 * Created by Eric and Felix on 28.10.2015.
 */
public interface StatementInterface {
	/**
	 * returns a resultset for each query
	 * @return
	 */
    public ResultSet getResult();
    /**
     * setup the way to connect to the database e.g PostgresConnect for postgres driver
     * @param dbInterface
     */
    public void setDBInterface(DatabaseInterface dbInterface);
}
