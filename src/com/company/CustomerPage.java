package com.company;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import models.User;
import network.JDBC;

import java.util.Optional;

public class CustomerPage {


    public static void show(Stage stage, Scene previosScene, User user, boolean b) {
        HBox root = new HBox();
        Button signOut = new Button("Sign out");
        Button removeProfile = new Button("removeProfile");
        Label lSerivce = new Label("Service:");
        ComboBox service = new ComboBox();
        service.getItems().addAll(JDBC.fetchTable("services"));
        Button request = new Button("Request");
        root.getChildren().addAll(lSerivce,service,request,signOut,removeProfile);
        root.setSpacing(10);

        Scene currentScene = new Scene(root,previosScene.getWidth(),previosScene.getHeight());
        stage.setScene(currentScene);
        stage.setTitle("Add Service window");

        request.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });

        signOut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(previosScene);
                stage.show();
            }
        });

        removeProfile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("remove user");
                alert.setHeaderText("Removing user "+user.getUsername()+" :");
                alert.setContentText("Are you sure about this?");
                Optional<ButtonType> result = alert.showAndWait();
                if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
                    JDBC.removeUser(user);
                    stage.setScene(previosScene);
                    stage.show();
                }
            }
        });
    }

}
