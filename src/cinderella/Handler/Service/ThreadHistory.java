package cinderella.Handler.Service;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TextArea;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by note on 08.11.2015.
 */
public class ThreadHistory extends Thread implements Runnable {

    private String name;
    private StringProperty str;
    private TaskHist ts;
    private TextArea ta;
    private Thread td;
    private int timetoSleep;

    public ThreadHistory(String name, TextArea ta) {

        try {

            InputStream fi = ThreadHistory.class.getResourceAsStream("/resources/Configuration.txt");
            Properties properties = new Properties();
            properties.load(fi);
            timetoSleep = Integer.parseInt(properties.getProperty("LotProcessing"));
            this.name = name;
            this.ta = ta;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void run() {
        str = new SimpleStringProperty();
        //Thread.currentThread().isInterrupted();


        while (true) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    ts = new TaskHist(name);
                    td = new Thread(ts);
                    td.run();
                    ta.textProperty().bind(ts.messageProperty());

                }
            });

            try {
                Thread.sleep(timetoSleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


    }
}

