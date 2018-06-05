package com.company;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import models.User;
import network.DBInterface;
import network.JDBC;

public class RegistrationPage {
    private static GridPane root = new GridPane();
    private static Button back = new Button("back");

    //private static HBox firstRow = new HBox();
    private static Label lUsername = new Label("username");
    private static TextField tUsername = new TextField();

    //private static HBox secondRow = new HBox();
    private static Label lPassword = new Label("password");
    private static PasswordField tPassword = new PasswordField();

    private static Label usernaemError = new Label();
    private static Label passwordError = new Label();

    //private static VBox root = new VBox();
    private static Button submit = new Button("submit");




    public static void show(Stage primaryStage, Scene previosScene) {
     
        root.setVgap(3);
        root.setHgap(3);
        root.add(lUsername,0,0);
        root.add(tUsername,1,0);
        root.add(usernaemError,2,0);
        root.add(lPassword,0,1);
        root.add(tPassword,1,1);
        root.add(passwordError,2,1);
        root.add(submit,1,2);

        Scene scene = new Scene(root,previosScene.getWidth(),previosScene.getHeight());
        primaryStage.setScene(scene);
        primaryStage.show();
        
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                createUser(primaryStage,scene,new User(tUsername.getText(),tPassword.getText()));
            }
        });


        

    }
    private static void createUser(Stage stage, Scene currentScene,User user){
        if(user.getUsername().isEmpty()){
            usernaemError.setText("");
            passwordError.setText("");
            tUsername.clear();
            usernaemError.setText("username can't be empty!");
            usernaemError.setTextFill(Color.RED);
        }else if (user.getPassword().isEmpty()){
            passwordError.setText("");
            usernaemError.setText("");
            tPassword.clear();
            passwordError.setText("password can't be empty");
            passwordError.setTextFill(Color.RED);
        }else {
            if (!JDBC.checkUser(user.getUsername())){
                SignUpPage.show(stage,currentScene,user);
            }else {
                tPassword.clear();
                usernaemError.setText("your username is duplicate, please insert another one");
                usernaemError.setTextFill(Color.RED);
            }

        }
    }

}
