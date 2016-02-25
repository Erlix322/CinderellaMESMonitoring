package cinderella.Handler.Service;


import cinderella.Controller.ViewController.EntityController;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by note on 07.11.2015.
 */
public class Tool_Service {
    public static Map<String, EntityController> map = new HashMap<>();

    public static void addTool(EntityController tool) {
        map.put(tool.name, tool);
    }

    public static void removeTool(String tool) {
        map.remove(tool);
    }

    ;

    public static EntityController getTool(String tool) {
        return map.get(tool);
    }

}
