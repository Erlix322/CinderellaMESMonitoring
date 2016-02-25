package cinderella.Handler.Service;

import cinderella.Beans.Lot;
import cinderella.Handler.Databasehandler.DispatchListQuery;
import cinderella.Handler.Databasehandler.PostgresConnect;
import cinderella.Handler.Databasehandler.StatementInterface;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by note on 05.12.2015.
 */
public class DispatchList extends Task {

    ObservableList ol;
    TableColumn[] tableColumns;
    int count = 0;
    String name;

    public DispatchList(ObservableList observableList, int count, String name, TableColumn... tableColumn) {
        this.ol = observableList;
        this.count = count;
        this.name = name;
        tableColumns = new TableColumn[tableColumn.length];
        int i = 0;
        for (TableColumn tableColumn1 : tableColumn) {
            tableColumns[i] = tableColumn1;
            i++;
        }
    }

    @Override
    protected Object call() throws Exception {
       /* StatementInterface st = new DispatchListQuery();*/
        loadData("50", String.valueOf(count));
        loadTableView();
        System.out.println("thredsssssssssssssssssssssssssssssssssssssssssss");
        return null;
    }

    private void loadData(String limit, String offset) {
        System.out.println("COUntER:                                                               " + count);
        ol.clear();
        try {            
        	StatementInterface st = new DispatchListQuery(limit, offset, name);
            st.setDBInterface(new PostgresConnect());
            ResultSet rs = st.getResult();
            while (rs.next()) {
                Lot l = new Lot();
                l.setLotid(rs.getString(1));
                l.setEnteroper(rs.getString(2));
                l.setPriority(rs.getString(3));
                System.out.println(rs.getString(1));
                ol.add(l);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadTableView() {
        tableColumns[0].setCellValueFactory(new PropertyValueFactory<Lot, String>("lotid"));
        tableColumns[1].setCellValueFactory(new PropertyValueFactory<Lot, String>("enteroper"));
        tableColumns[2].setCellValueFactory(new PropertyValueFactory<Lot, String>("priority"));


        tableColumns[0].getTableView().setItems(ol);
        tableColumns[1].getTableView().setItems(ol);
        tableColumns[2].getTableView().setItems(ol);

    }
}
