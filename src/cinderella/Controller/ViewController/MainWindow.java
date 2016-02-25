package cinderella.Controller.ViewController;

import cinderella.Controller.Factories.ViewFactory2;
import cinderella.Handler.Databasehandler.EntityQuery;
import cinderella.Handler.Databasehandler.PostgresConnect;
import cinderella.Handler.Databasehandler.StatementInterface;
import cinderella.Handler.Service.State_Thread;
import cinderella.Handler.Service.Tool_Service;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Eric and Felix Brandt on 27.10.2015.
 * This Class represents the Controller for the main window of the application.
 */
public class MainWindow implements VC {

    Stage stage;
    StatementInterface statementInterface;
    State_Thread st;
    ResultSet resultSet;
    
    @FXML
    BorderPane root;
    @FXML
    ScrollPane scrollpane;
    @FXML
    TilePane tile;
    @FXML
    Button WIP, DISPATCH, CONFIG;
    int timetoSleep;

    double screenWidth;


    /**
     * <br> constructor requires the stage of the former ViewController(e.g. login window)
     * if no former stage is available declare a new one in your main application class
     *
     * @param stage
     * @throws InterruptedException
     */
    public MainWindow(Stage stage) throws InterruptedException {
        
    	this.stage = stage;
        stage.setTitle("Cinderella");
        stage.getIcons().add(new Image("/resources/Cinderellaaa.JPG"));
        // ts = new Tool_Service();
        //Korrektes Beenden der Nebenlaeufigen Threads/Tasks
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.out.println("HUHU");
            	Platform.exit();
                System.exit(0);
            }
        });
      
    }

    private void loadToolList() throws SQLException, IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
       statementInterface = new EntityQuery();
       statementInterface.setDBInterface(new PostgresConnect());
       this.resultSet = statementInterface.getResult();
        long time = System.nanoTime();
        while (resultSet.next()) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/EntityView.fxml"));
            String name = resultSet.getString(1);

            ViewFactory2.registerView(name, EntityController.class);
            VC instance;
            instance = ViewFactory2.getView(name);
            Tool_Service.addTool((EntityController) instance);
            loader.setController(instance);
            Pane p = loader.load();
            p.getStylesheets().add("/resources/style.css");
            tile.getChildren().add(p);
        }
        tile.setAlignment(Pos.TOP_CENTER);
        tile.setHgap(10);
        tile.setVgap(10);
        System.out.println(ViewFactory2.getView("DEP1001"));
//        long time2 = System.nanoTime();
//        System.out.println("Zeit1: " + time + "Zeit2: " + time2);
//        System.out.println("Differenz: " + (time2 - time));
//        System.out.println("Sekunden: " + ((double) ((time2 - time) / 1000000000.0)));
    }

    private void startThread() {
        Thread td = new Thread(new Runnable() {
            @Override
            public void run() {
                //Thread.currentThread().isInterrupted();
                while (true) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            st = new State_Thread();
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
    public void showConfig() {

    }



    @FXML
    public void showWIP() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, IOException {
        new View("/resources/ToolWip.fxml", "WIP", ToolWipController.class);
//    	  Stage histStage = new Stage();
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/ToolWip.fxml"));
//        ViewFactory2.registerView("WIP", ToolWipController.class);
//        VC v = ViewFactory2.getView("WIP");
//        loader.setController(v);
        
//        Parent p = loader.load();
//        Scene sc = new Scene(p);
//        histStage.setScene(sc);
//        histStage.show();
    }

    @FXML
    public void initialize() {

        //BackgroundThread zu Anforderung 2

        try {
            InputStream cl = MainWindow.class.getResourceAsStream("/resources/Configuration.txt");


            Properties properties = new Properties();
            properties.load(cl);
            timetoSleep = Integer.parseInt(properties.getProperty("ToolState"));

            root.getStylesheets().add("/resources/style.css");
            loadToolList();
            startThread();
        } catch (SQLException | IOException | ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }


    }

    public void dragEvent(MouseEvent event) {
        Button b = (Button) event.getSource();
        b.getStyleClass().add("buttonHover");
    }

    public void mouseExited(MouseEvent event) {
        Button b = (Button) event.getSource();
        b.getStyleClass().clear();
        b.getStyleClass().add("button");
    }

    public void buttonClicked(MouseEvent event) {
        Button b = (Button) event.getSource();
        b.getStyleClass().add("buttonClicked");
    }
    //muss noch generalisiert werden!!!
    public static VC getInstance(String name,Stage stage) throws SQLException, InterruptedException {
        VC instance;
        instance = (VC) new MainWindow(stage);
        return instance;
    }


}



