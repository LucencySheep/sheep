package jfxdialog;

import com.jfoenix.controls.JFXButton;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

//自己设计一个提示框
public class MyDialog extends Application {

    // 创建一个窗口
    private Stage dialogWindows = new Stage();
    private Stage mainWindows;
    // 创建组容器
    private Group group = new Group();
    // 创建场景
    private Scene scene = new Scene(group);
    // 创建按钮
    private JFXButton button = new JFXButton("确定");
    // 创建提示标签
    private Label titleLabel = new Label("提示");
    // 创建提示语句
    private Label message = new Label(".......");
    // 创建StringBuilder，拼接字符串
    private StringBuilder builder = new StringBuilder();

    public MyDialog(Stage mainWindows) {
        try {

            this.mainWindows = mainWindows;
            this.start(dialogWindows);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 用户自己在创建对象的时候给出 提示信息 和 按钮的字
    public MyDialog(Stage mainWindows,String buttonMsg, String labelMsg) {
        try {
            this.mainWindows = mainWindows;
            button.setText(buttonMsg);
            message.setText(labelMsg);
            this.start(dialogWindows);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //设置按钮信息与提示信息
    public void setButtonAndLabelMsg(String buttonMsg, String labelMsg) {
            button.setText(buttonMsg);
            message.setText(labelMsg);
    }

    // 执行窗体的主入口
    @Override
    public void start(Stage window) throws Exception {
        this.setFontOnSoOn();
        this.addListener(window);
        this.addElement();
        this.setSelf(window);
    }

    //设置提示标签
    public void setTitleColor(String RGB){
        titleLabel.setStyle(
            "-fx-font-size: 25;" + 
            "-fx-font-family: bold;"+
            "-fx-text-fill:"+RGB+";"
        );
    }
    //设置提示标签的相对于提示框的位置
    public void setTitleXAndY(double x,double y){
        titleLabel.setLayoutX(x);
        titleLabel.setLayoutY(y);
    }

    //设置提示信息的大小
    public void setMessageSize(int size){
        message.setStyle(
            "-fx-font-size: "+size+";"
        );
    }
    //设置提示信息的颜色
    public void setMessageColor(String RGB){
        message.setStyle(
            "-fx-text-fill: "+RGB+";"
        );
    }

    //设置按钮的字体大小
    public void setButtonSize(int size){
        button.setStyle("-fx-font-size: "+size+";");
    }
    //设置按钮的字体颜色
    public void setButtonTextColor(String RGB){
        button.setStyle("-fx-text-fill: "+RGB+";");
    }
    //设置按钮的背景颜色
    public void setButtonBackgroundColor(String RGB){
        button.setStyle("-fx-background-color: "+RGB+";");
    }

    //设置提示框的背景颜色
    public void setBackgroundColor(String RGB){
        scene.setFill(Paint.valueOf(RGB));
    }
    //=========================================================
    // 设置组件大小等等
    private void setFontOnSoOn() {
        // 设置按钮
        button.setLayoutX(368);
        button.setLayoutY(189);
        button.setPrefSize(120, 45);
        button.setStyle("-fx-font-size: 18;" + "-fx-background-color: #bdd5fa;" + "-fx-text-fill: #0b6dff");

        // 设置提示标签
        titleLabel.setStyle("-fx-font-size: 25;" + "-fx-font-family: bold;");
        titleLabel.setLayoutX(20);
        titleLabel.setLayoutY(15);

        // 设置提示信息的标签
        message.setStyle("-fx-font-size: 24;" + "-fx-font-family : bold;");
        //如果提示信息太多了，则进行递归算法换行
        String labelMessage = message.getText();// 获取提示信息
        if(labelMessage.length() >= 25){
            message.setText(this.controlMessageLine(labelMessage).toString());
        }
        message.setLayoutX(45);
        message.setLayoutY(80);

        // 设置场景背景颜色
        scene.setFill(Paint.valueOf("#FFFFFF"));
        
    }

    // 添加按钮事件监听器
    private void addListener(Stage window) {
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                window.close();
            }
        });

    }

    //往组容器里添加组件 group是空布局 所以我们的组件可以通过调整组件自身的x和y值来确定窗体里的位置
    private void addElement() { 
        group.getChildren().addAll(button,titleLabel,message);
        // scene.getStylesheets().add(MyDialog.class.getResource("MyDialog.css").toExternalForm());
        dialogWindows.setScene(scene);
    }

    // 设置窗口的初始化以及大小等等
    private void setSelf(Stage window) {
       //通过这个简单的计算，让提示框始终靠近在主窗体中间显示
        window.setX(mainWindows.getX()+(mainWindows.getWidth()/3.8));
        window.setY(mainWindows.getY()+(mainWindows.getHeight()/3.8));
        window.setWidth(500);
        window.setHeight(240);
        window.setAlwaysOnTop(true);
        window.initStyle(StageStyle.UNDECORATED);//将标题栏去掉
        // 设置提示窗口与传进来的主窗口的关系 
        window.initModality(Modality.APPLICATION_MODAL);//设置这个窗口为对话框模式
        window.show();
    }

    //设置提示信息如果超出一定的字数就换行
    private StringBuilder controlMessageLine(String labelMessage){
        String aheadMessage = "";
        String nextMessage = "";
        if (labelMessage.length() >= 25) {
            aheadMessage = labelMessage.substring(0, 25);
            nextMessage = labelMessage.substring(25);
            builder.append(aheadMessage).append("\n");
            if(nextMessage.length() >= 25){
                this.controlMessageLine(nextMessage);
            }else{
                builder.append(nextMessage);
            }
        }
        return builder;
    }
    
} 
