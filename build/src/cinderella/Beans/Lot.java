package cinderella.Beans;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by note on 02.12.2015.
 */
public class Lot {
    StringProperty lotid;
    StringProperty enteroper;
    StringProperty priority;

    public Lot() {
        lotid = new SimpleStringProperty();
        enteroper = new SimpleStringProperty();
        priority = new SimpleStringProperty();

    }

    public String getLotid() {
        return lotid.get();
    }

    public void setLotid(String lotid) {
        this.lotid.set(lotid);
    }

    public StringProperty lotidProperty() {
        return lotid;
    }

    public String getEnteroper() {
        return enteroper.get();
    }

    public void setEnteroper(String enteroper) {
        this.enteroper.set(enteroper);
    }

    public StringProperty enteroperProperty() {
        return enteroper;
    }

    public String getPriority() {
        return priority.get();
    }

    public void setPriority(String priority) {
        this.priority.set(priority);
    }

    public StringProperty priorityProperty() {
        return priority;
    }
}
