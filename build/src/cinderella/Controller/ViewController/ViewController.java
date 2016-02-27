package cinderella.Controller.ViewController;

import javafx.stage.Stage;

/**
 * Created by note on 14.11.2015.
 */

public abstract class ViewController implements VC{
    public Stage stage;
    public abstract void initialize();
}
