package com.company;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.User;
import network.JDBC;

public class SignInPage {
    public static User user;
    public static void show(Stage primaryStage, Scene previosScene) {
        primaryStage.setTitle("Sign in Page");
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(5);
        Label login = new Label("Login");
        TextField username = new TextField();
        username.setMaxWidth(150);
        PasswordField password = new PasswordField();
        password.setMaxWidth(150);
        Button sign_in = new Button("Sign in");
        Button back = new Button("Back");
        back.setAlignment(Pos.BOTTOM_LEFT);
        username.setPromptText("username");
        password.setPromptText("password");
        root.getChildren().addAll(login,username,password,sign_in,back);
        Scene currentScene = new Scene(root,previosScene.getWidth(),previosScene.getHeight());
        primaryStage.setScene(currentScene);
        primaryStage.show();
        sign_in.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                user = new User(username.getText(),password.getText());
                if(username.getText().equals("admin") && password.getText().equals("admin")) {
                    OperatorPage.show(primaryStage, previosScene);
                }else if(JDBC.authenticate(user.getUsername(),user.getPassword(),"users")){
                   if(JDBC.isCustomer(user.getUsername(),user.getPassword())){
                       username.setText("");
                       password.setText("");
                       CustomerPage.show(primaryStage,currentScene,user);
                   }else {
                       username.setText("");
                       password.setText("");
                       ProviderPage.show(primaryStage,currentScene,user);

                   }
                }


            }
        });
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(previosScene);
                primaryStage.show();
            }
        });

    }
}
