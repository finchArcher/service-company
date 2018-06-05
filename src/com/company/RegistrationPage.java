package com.company;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.User;
import network.DBInterface;

public class RegistrationPage {
    private static GridPane root = new GridPane();
    private static Button back = new Button("back");

    //private static HBox firstRow = new HBox();
    private static Label lUsername = new Label("username");
    private static TextField tUsername = new TextField();

    //private static HBox secondRow = new HBox();
    private static Label lPassword = new Label("password");
    private static TextField tPassword = new TextField();

    //private static VBox root = new VBox();
    private static Button submit = new Button("submit");




    public static void show(Stage primaryStage, Scene previosScene) {
      /*  firstRow.getChildren().addAll(lUsername,tUsername);
        firstRow.setSpacing(10.0);
        secondRow.getChildren().addAll(lPassword,tPassword);
        secondRow.setSpacing(10.0);
        root.getChildren().addAll(firstRow,secondRow,submit);*/
        root.setVgap(3);
        root.setHgap(2);
        root.add(lUsername,0,0);
        root.add(tUsername,1,0);
        root.add(lPassword,0,1);
        root.add(tPassword,1,1);
        root.add(submit,1,2);

        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                createUser(new User(lUsername.getText(),tPassword.getText()));
            }
        });


        Scene scene = new Scene(root,previosScene.getWidth(),previosScene.getHeight());
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    private static void createUser(User newUser){
        //TODO:add else clause
        if (!DBInterface.authenticate(newUser.getUsername(),newUser.getPassword(),"users")){
            SignUpPage.show(newUser);
        }
    }

}
