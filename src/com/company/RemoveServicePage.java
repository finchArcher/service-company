package com.company;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import models.User;
import network.JDBC;


public class RemoveServicePage {
    public static void show(Stage primaryStage, Scene previosScene, User user) {
        primaryStage.setTitle("Remove Service");
        HBox root = new HBox();
        root.setSpacing(10);

        root.setAlignment(Pos.CENTER);
        Button back = new Button("Back");
        Label lSerivce = new Label("Service:");
        ComboBox service = new ComboBox();
        service.getItems().addAll(JDBC.fetchUserService("services",user));
        Button remove = new Button("Remove");
        root.getChildren().addAll(back,lSerivce,service,remove);

        Scene currentScene = new Scene(root,previosScene.getWidth(),previosScene.getHeight());
        primaryStage.setScene(currentScene);
        primaryStage.show();

        remove.setOnAction(new EventHandler<ActionEvent>() {
            //TODO:check service deleted or not;
            @Override
            public void handle(ActionEvent event) {
                JDBC.removeService(service.getValue().toString(),user);
                primaryStage.setScene(previosScene);
                primaryStage.show();
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
