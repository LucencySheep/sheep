package jfxdialog;

import com.jfoenix.controls.JFXButton;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class MainWindow extends Application {
    static Stage stage = new Stage();
    private JFXButton button = new JFXButton("按钮");
    private Group group = new Group(button);
    private Scene scene = new Scene(group);


    @Override
    public void start(Stage arg0) throws Exception {
        button.setPrefSize(150, 50);
        button.setLayoutX(400);
        button.setLayoutY(200);
        button.setStyle("-fx-background-color: #FFFFE0;"+"-fx-text-fill: #FF0000");
        button.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent arg0) {
                MyDialog dialog = new MyDialog(stage);
                // dialog.setBackgroundColor("");
                // dialog.setButtonAndLabelMsg("", "");
                // dialog.setButtonBackgroundColor("");
                // dialog.setButtonSize(0);
                // dialog.setButtonTextColor("");
                // dialog.setMessageColor("");
                // dialog.setMessageSize(0);
                // dialog.setTitleColor("");
                // dialog.setTitleXAndY(0, 0);
            }
        });
        //设置窗口
        scene.setFill(Paint.valueOf("#FFFFE0"));
        stage.getIcons().add(new Image("/img/cloud.jpg"));
        stage.setHeight(500);
        stage.setWidth(1000);
        stage.setX(500);
        stage.setY(200);
        stage.setScene(scene);
        stage.show();
        
    }
    
    
}
