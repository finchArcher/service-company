package com.company;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import models.Service;
import models.User;
import network.JDBC;

import java.util.ArrayList;

public class RemoveServicePage {
    public static void show(Stage stage, Scene previosScene, User user) {
        HBox root = new HBox();
        Button back = new Button("back");
        Label lSerivce = new Label("Service:");
        ComboBox service = new ComboBox();
        service.getItems().addAll(JDBC.fetch("services",user));
        Button remove = new Button("Remove");
        root.getChildren().addAll(back,lSerivce,service,remove);
        root.setSpacing(10);

        Scene currentScene = new Scene(root,previosScene.getWidth(),previosScene.getHeight());
        stage.setScene(currentScene);
        stage.setTitle("Add Service window");

        remove.setOnAction(new EventHandler<ActionEvent>() {
            //TODO:check service deleted or not;
            @Override
            public void handle(ActionEvent event) {
                JDBC.removeService(service.getValue().toString(),user);
                stage.setScene(previosScene);
                stage.show();
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
