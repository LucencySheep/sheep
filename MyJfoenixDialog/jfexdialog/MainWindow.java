package jfexdialog;

import com.jfoenix.controls.JFXButton;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainWindow extends Application {
    static Stage stage = new Stage();
    private JFXButton button = new JFXButton("??");
    private Group group = new Group(button);
    private Scene scene = new Scene(group);


    @Override
    public void start(Stage arg0) throws Exception {
        button.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent arg0) {
                MyDialog dialog = new MyDialog(stage);
            }
        });
        
        stage.setHeight(500);
        stage.setWidth(1000);
        stage.setX(500);
        stage.setY(200);
        stage.setScene(scene);
        stage.show();
        
    }
    
    public static void main(String[] args){
        launch(args);
    }
}
