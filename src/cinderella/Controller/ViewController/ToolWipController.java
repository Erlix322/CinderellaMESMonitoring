package cinderella.Controller.ViewController;

import cinderella.Beans.Tool;
import cinderella.Handler.Databasehandler.StatementInterface;
import cinderella.Handler.Databasehandler.ToolWipQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by note on 28.11.2015.
 */
public class ToolWipController extends ViewController implements VC {
    @FXML
    TableView tableView;
    @FXML
    TableColumn<Tool, String> toolColumn, wipColumn;
    ObservableList<Tool> list = FXCollections.observableArrayList();

    public static VC getInstance(String name) {
        VC instance;
        instance = new ToolWipController();
        return instance;
    }

    public void loadTableData() {
        StatementInterface st = new ToolWipQuery();
        ResultSet rs = st.getResult();
        try {
            while (rs.next()) {
                Tool tool = new Tool();
                tool.setToolName(rs.getString(1));
                tool.setSummeTool(rs.getInt(2));
                list.add(tool);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadTableView() {
        toolColumn.setCellValueFactory(new PropertyValueFactory<Tool, String>("toolName"));
        wipColumn.setCellValueFactory(new PropertyValueFactory<Tool, String>("summeTool"));

        toolColumn.getTableView().setItems(list);
        wipColumn.getTableView().setItems(list);


    }

    @FXML
    public void initialize() {
        loadTableData();
        loadTableView();

    }

}
