package cinderella.Handler.Databasehandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Eric and Felix Brandt on 28.11.2015.
 * 
 */
public abstract class Query implements StatementInterface{
  
	
	private PreparedStatement preparedStatement;
    private DatabaseInterface dbI;
    private ResultSet resultSet;

    /**
     * creating a prepared statement and saves it in @param preparedStatement
     */
    public abstract void prepareQuery();


	public PreparedStatement getPreparedStatement() {
		return preparedStatement;
	}

	protected void setPreparedStatement(PreparedStatement preparedStatement) {
		this.preparedStatement = preparedStatement;
	}


	public DatabaseInterface getDbI() {
		return dbI;
	}


	public void setDbI(DatabaseInterface dbI) {
		this.dbI = dbI;
	}


	public ResultSet getResultSet() {
		return resultSet;
	}


	public void setResultSet(ResultSet resultSet) {
		this.resultSet = resultSet;
	}
}
