package cinderella.Controller.ViewController;

import cinderella.Beans.Lot;
import cinderella.Handler.Databasehandler.DispatchListQuery;
import cinderella.Handler.Databasehandler.PostgresConnect;
import cinderella.Handler.Databasehandler.StatementInterface;
import cinderella.Handler.Service.DispatchList;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by note on 27.11.2015.
 */
public class DispatchController implements VC {

    @FXML
    TableView tableView;
    @FXML
    TableColumn tableColumn, t2, t3;
    @FXML
    Label vonbis;
    boolean flag = true;
    int timetoSleep;
    String name;
    int count;
    ObservableList<Lot> ol = FXCollections.observableArrayList();

    DispatchController(String name) {
        this.name = name;
    }

    public static VC getInstance(String name) throws SQLException {
        VC instance;
        instance = new DispatchController(name);
        return instance;
    }

    @FXML
    public void initialize() throws InterruptedException, IOException {

        InputStream fi = DispatchController.class.getResourceAsStream("/resources/Configuration.txt");
        Properties properties = new Properties();
        properties.load(fi);
        timetoSleep = Integer.parseInt(properties.getProperty("DispatchList"));
        count = 0;
        startThread();
        loadData("50", "0");
        loadTableView();
        vonbis.setText("0 - 50");
    }

    private void startThread() {
        Thread td = new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {                 
                            DispatchList st = new DispatchList(ol, count, name, tableColumn, t2, t3);
                            new Thread(st).run();

                        }
                    });
                    try {
                        Thread.sleep(timetoSleep);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        td.start();
    }

    @FXML
    public void next() {
        ol.clear();
        System.out.println("NEXT: " + count);
        count = count + 50;
        loadData("50", String.valueOf(count));
        loadTableView();
        int c2 = count;
        if (count == 50) {
            vonbis.setText("" + 50 + " - " + 100);
        } else {
            c2 = c2 + 50;
            vonbis.setText("" + count + " - " + c2);
        }
    }

    public void previous() {
        if (count > 0) {
            ol.clear();
            count = count - 50;
            System.out.println("PREVIVOUS: " + count);
            loadData("50", String.valueOf(count));
            loadTableView();
            int c2 = count;
            c2 = c2 + 50;
            vonbis.setText("" + count + " - " + c2);
        }
    }

    private void loadData(String limit, String offset) {
        try {
            StatementInterface st = new DispatchListQuery(limit, offset, name);
            st.setDBInterface(new PostgresConnect());
            ResultSet rs = st.getResult();
            while (rs.next()) {
                Lot l = new Lot();
                System.out.println(rs.getString(1));
                l.setLotid(rs.getString(1));
                l.setEnteroper(rs.getString(2));
                l.setPriority(rs.getString(3));
                ol.add(l);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadTableView() {
        tableColumn.setCellValueFactory(new PropertyValueFactory<Lot, String>("lotid"));
        t2.setCellValueFactory(new PropertyValueFactory<Lot, String>("enteroper"));
        t3.setCellValueFactory(new PropertyValueFactory<Lot, String>("priority"));


        tableColumn.getTableView().setItems(ol);
        t2.getTableView().setItems(ol);
        t3.getTableView().setItems(ol);

    }

    public void setVonBis(String von, String bis) {

    }
}
