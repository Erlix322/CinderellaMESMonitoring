package cinderella.Beans;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by note on 27.11.2015.
 */
public class Entity {
    StringProperty toolName;

    StringProperty dispTool;


    public Entity(String name) {
        this.toolName = new SimpleStringProperty(name);
    }

    public String getToolName() {
        return toolName.get();
    }

    public void setToolName(String toolName) {
        this.toolName.set(toolName);
    }

    public StringProperty toolNameProperty() {
        return toolName;
    }

    public String getDispTool() {
        return dispTool.get();
    }

    public void setDispTool(String dispTool) {
        this.dispTool.set(dispTool);
    }

    public StringProperty dispToolProperty() {
        return dispTool;
    }
}
