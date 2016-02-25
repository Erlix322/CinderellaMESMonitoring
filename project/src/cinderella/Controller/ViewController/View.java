package cinderella.Controller.ViewController;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import cinderella.Main;
import cinderella.Controller.Factories.ViewFactory2;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class View extends Stage {
	FXMLLoader loader;
	VC view;
	
	
	
//	Stage histStage = new Stage();
//    FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/ToolWip.fxml"));
//    ViewFactory2.registerView("WIP", ToolWipController.class);
//    VC v = ViewFactory2.getView("WIP");
//    loader.setController(v);
    
	
	
	View(String url,String name,Class<?> clas){
		
		try {
			
			loader = new FXMLLoader(clas.getResource(url));
			ViewFactory2.registerView(name, clas);
			view = ViewFactory2.getView(name);
			loader.setController(view); 
			Parent p = loader.load();
			Scene sc = new Scene(p);
			this.setScene(sc);
			this.show();
		} catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException
				| IllegalAccessException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	
	
	public Stage getStage(){
		return this;
	}
}
