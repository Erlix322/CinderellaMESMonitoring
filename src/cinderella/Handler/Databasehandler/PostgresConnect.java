/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinderella.Handler.Databasehandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import cinderella.Beans.User;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * class to connect to PostgreSQL
 * @author Eric Brandt
 */
public class PostgresConnect implements DatabaseInterface {
    private String dbURL;
    private Connection myConn;
    private String user;
    private String pass;

       
    public PostgresConnect() {
      dbURL = "jdbc:postgresql://192.168.1.20:5432/vwp";
      this.user = User.getUser();
      this.pass = User.getPassword();
      System.out.println("Connectiondata: "+user +":"+ pass);
      System.out.println("Connectiondata: "+user +":"+ pass);
      setupConn();
    }

    private void setupConn() {
        try {
            myConn = DriverManager.getConnection(dbURL, user, pass);

            System.out.println(myConn.toString());
        } catch (SQLException ex) {
        	
            Logger.getLogger(PostgresConnect.class.getName()).log(Level.SEVERE, null, ex);
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Passwort oder Usernamen prüfen!");
            alert.show();
        }
    }

    /**
     * returns an open connection for pending queries
     * @return
     */
    @Override
    public Connection getConnection() {
        return myConn;
    }
}
