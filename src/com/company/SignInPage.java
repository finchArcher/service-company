package com.company;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SignInPage {
    public static void sign_in_function(Stage stage, Scene previosScene) {
        VBox vBox = new VBox();
        TextField username = new TextField();
        TextField password = new TextField();
        Button login = new Button("sign_in");
        Button back = new Button("back");
        username.setPromptText("username");
        password.setPromptText("password");
        vBox.getChildren().addAll(back,username,password,login);
        Scene sign_scene = new Scene(vBox,800,600);
        stage.setScene(sign_scene);
        stage.show();
        login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String Susername = username.getText();
                String Spassword = password.getText();
                if(Sign.authenticate(Susername,Spassword,"users")){
                    System.out.println("congagulation");
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
