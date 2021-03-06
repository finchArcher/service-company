package com.company;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import models.Report;
import models.Service;
import models.User;
import network.JDBC;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Optional;

public class CustomerPage {


    public static void show(Stage primaryStage, Scene previosScene, User user) {
        primaryStage.setTitle("Customer Page");
        HBox root = new HBox();
        root.setAlignment(Pos.CENTER);
        Button signOut = new Button("Sign out");
        Button removeProfile = new Button("removeProfile");
        Label lSerivce = new Label("Service:");
        ComboBox comboBox = new ComboBox();
        comboBox.getItems().addAll(JDBC.fetchTable("services"));
        Button request = new Button("Request");
        root.getChildren().addAll(lSerivce,comboBox,request,signOut,removeProfile);
        root.setSpacing(10);

        Scene currentScene = new Scene(root,previosScene.getWidth(),previosScene.getHeight());
        primaryStage.setScene(currentScene);

        request.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                operator(user,comboBox.getValue().toString());
            }
        });

        signOut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(previosScene);
                primaryStage.show();
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
                    primaryStage.setScene(previosScene);
                    primaryStage.show();
                }
            }
        });
    }

    private static void operator(User user,String service){
        //find best option for chosen service
        Service result = JDBC.fetchServices(service);

        //get rate from user
        ChoiceDialog<Integer> rateDialog = new ChoiceDialog<>(3,1,2,3,4,5,6,7,8,9,10);
        rateDialog.setTitle("Rate");
        rateDialog.setHeaderText("How was your service");
        rateDialog.setContentText("Rate:");
        rateDialog.showAndWait();
        int rate = rateDialog.getSelectedItem();

        //add report of service to database
        Report report = new Report(result.getProvider_id(),user.getUsername(),
                                    service,result.getCost(),
                                    new Timestamp((System.currentTimeMillis()))
                                        ,rate);

        JDBC.insert(report);

        //update rate of service provider
        updateRate(result.getProvider_id(),rate);

    }

    private static void updateRate(String provider_id,int newRate) {
        ArrayList<Integer> sumRate = JDBC.fetchRate(provider_id);
        int rate = (sumRate.get(1) + newRate)/(sumRate.get(0)+1);
        JDBC.updateRate(provider_id,rate);
    }

}
