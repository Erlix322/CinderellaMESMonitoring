package cinderella.Beans;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by note on 06.12.2015.
 */
public class ConfigLoader {
    public ConfigLoader() {
        try {
            File f = new File("Configuration.txt");
            FileOutputStream fo = new FileOutputStream(f);
            Properties properties = new Properties();
            properties.setProperty("DispatchList", "900000");
            properties.setProperty("LotProcessing", "900000");
            properties.setProperty("ToolState", "5000");

            properties.store(fo, "configuration file for all threads");

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
