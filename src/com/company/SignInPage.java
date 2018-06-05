package com.company;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.User;
import network.JDBC;

public class SignInPage {
    public static User user;
    public static void show(Stage stage, Scene previosScene) {
        VBox vBox = new VBox();
        TextField username = new TextField();
        TextField password = new TextField();
        Button sign_in = new Button("sign_in");
        Button back = new Button("back");
        username.setPromptText("username");
        password.setPromptText("password");
        vBox.getChildren().addAll(back,username,password,sign_in);
        Scene currentScene = new Scene(vBox,800,600);
        stage.setScene(currentScene);
        stage.show();
        sign_in.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                user = new User(username.getText(),password.getText());
                if(JDBC.authenticate(user.getUsername(),user.getPassword(),"users")){
                   if(JDBC.isCustomer(user.getUsername(),user.getPassword())){
                       username.setText("");
                       password.setText("");
                       CustomerPage.show(stage,currentScene,user,false);
                   }else {
                       username.setText("");
                       password.setText("");
                       ProviderPage.show(stage,currentScene,user,false);

                   }
                }

            }
        });
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(previosScene);
                stage.show();
            }
        });

    }
}
