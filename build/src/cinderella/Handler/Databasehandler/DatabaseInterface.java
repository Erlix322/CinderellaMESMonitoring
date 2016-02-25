package cinderella.Handler.Databasehandler;

import java.sql.Connection;

/**
 * Created by Eric and Felix on 28.10.2015.
 * Defines the Interface for DatabaseConnection.
 * 
 *
 * @author Eric and Felix Brandt
 * @version 1.0
 */
public interface DatabaseInterface {
    public Connection getConnection();
}
