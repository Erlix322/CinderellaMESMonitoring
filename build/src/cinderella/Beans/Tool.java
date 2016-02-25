package cinderella.Beans;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by note on 28.11.2015.
 */
public class Tool {
    StringProperty toolName;
    IntegerProperty summeTool;

    public Tool() {
        toolName = new SimpleStringProperty();
        summeTool = new SimpleIntegerProperty();
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

    public int getSummeTool() {
        return summeTool.get();
    }

    public void setSummeTool(int summeTool) {
        this.summeTool.set(summeTool);
    }

    public IntegerProperty summeToolProperty() {
        return summeTool;
    }
}
