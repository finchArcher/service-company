package com.company;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
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



public class AddServicePage{

    public static void show(Stage primaryStage, Scene previosScene, User user) {
        primaryStage.setTitle("Add Service");
        HBox root = new HBox();
        root.setSpacing(10);
        root.setAlignment(Pos.CENTER);
        Button back = new Button("Back");
        Label lSerivce = new Label("Service:");
        ComboBox services = new ComboBox();
        services.setEditable(true);
        services.getItems().addAll(JDBC.fetchTable("services"));
        Label lCost = new Label("Cost:");
        TextField tCost = new TextField();
        Button add = new Button("Add Serive");
        root.getChildren().addAll(back,lSerivce,services,lCost,tCost,add);
        Scene currentScene = new Scene(root,previosScene.getWidth(),previosScene.getHeight());
        primaryStage.setScene(currentScene);

        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Service service = new Service(user.getUsername(),
                                            services.getValue().toString(),
                                            Integer.parseInt(tCost.getText()));
                JDBC.insert(service);
                tCost.clear();
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
