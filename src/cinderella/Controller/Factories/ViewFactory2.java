package cinderella.Controller.Factories;

import cinderella.Controller.ViewController.VC;
import javafx.stage.Stage;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Eric und Felix on 24.02.2016.
 */
public class ViewFactory2 {
    private static Map<String, Class<?>> registeredView = new HashMap<String, Class<?>>();

    public static void registerView(String viewID, Class<?> productClass) {
        registeredView.put(viewID, productClass);
    }

    public static VC getView(String viewID) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> c = (Class<?>) registeredView.get(viewID);
        Method m = c.getMethod("getInstance", String.class);
        VC v = (VC) m.invoke(null, viewID);
        //VC v = (VC) m.invoke(null,viewID,stage);
        return v;
    }
    public static VC getView(String viewID, Stage stage) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> c = (Class<?>) registeredView.get(viewID);
        Method m = c.getMethod("getInstance", String.class, Stage.class);
        VC v = (VC) m.invoke(null, viewID,stage);
        return v;
    }
    
    
}
