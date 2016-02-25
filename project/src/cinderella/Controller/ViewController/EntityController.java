package cinderella.Controller.ViewController;


import cinderella.Controller.Factories.ViewFactory2;
import cinderella.Handler.Service.Tool_Service;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

/**
 * Created by note on 28.10.2015.
 */

/**
 * Created by Eric and Felix Brandt on 27.10.2015.
 * This Class represents the Controller for one Tool of the application.
 * Structure is described in a .fxml document
 */
public class EntityController implements VC {

    public String name;

    @FXML
    public Label label, state;

    @FXML
    public VBox innerPaneEntityView;

    /**
     * <br> parameter is required to identify each tool
     *
     * @param name
     * @throws SQLException
     */
    public EntityController(String name) throws SQLException {
        this.name = name;
    }

    public static VC getInstance(String name) throws SQLException {
        VC instance;
        instance = new EntityController(name);
        return instance;
    }

     /**
     * <br> shows history of the last processed lots by this tool
     *
     * @throws IOException
     */
    @FXML
    public void scheduleEvent() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, IOException {
       
    	Stage histStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/ScheduleEventView.fxml"));
        ViewFactory2.registerView(name, ScheduleEventController.class);

        VC v = ViewFactory2.getView(name,histStage);
        //* schreit nach auslagerung in Klasse 'ControllerLoader'*//*
        loader.setController(v);
        Parent p = loader.load();
        //   p.getStylesheets().add("cinderella/styles/style.css");
        Scene sc = new Scene(p);
        histStage.setScene(sc);
        histStage.setTitle(name);
        histStage.show();
    }

    @FXML
    public void showHist() throws IOException, SQLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    	View v = new View("/resources/Lot_ProcessingHistory.fxml",name,HistoryController.class);
    	v.getStage().setTitle(name);
//    	Stage histStage = new Stage();
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/Lot_ProcessingHistory.fxml"));
//        ViewFactory2.registerView(name, HistoryController.class);
//        VC v = ViewFactory2.getView(name);
//        loader.setController(v);
//        Parent p = loader.load();
//        Scene sc = new Scene(p);
//        histStage.setTitle(label.getText());
//        histStage.setScene(sc);
//        histStage.show();
    }

    public void showDisp() throws SQLException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, IOException {
    	View v = new View("/resources/DispatchView.fxml", name, DispatchController.class);
    	v.getStage().setTitle(name);
//    	Stage histStage = new Stage();
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/DispatchView.fxml"));
//        ViewFactory2.registerView(name, DispatchController.class);
//
//        VC v = ViewFactory2.getView(name);
//        loader.setController(v);
//        Parent p = loader.load();
//        Scene sc = new Scene(p);
//        histStage.setScene(sc);
//        histStage.setTitle(name);
//        histStage.show();

    }

    @FXML
    public void initialize() {
        innerPaneEntityView.setId("PROC");
        label.setText(name);
        state.setText("yolo");
    }
}
