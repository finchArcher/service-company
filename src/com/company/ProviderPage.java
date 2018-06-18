package com.company;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import models.Report;
import models.User;
import network.JDBC;

import java.util.ArrayList;
import java.util.Optional;


public class ProviderPage {
    private static TableView tableView;
    private static Button addService;
    private static Button signOut;
    private static Button removeService;
    private static Button removeProfile;
    public static void show(Stage primaryStage, Scene previosScene, User user) {
        primaryStage.setTitle("Provider Page");
        tableView = new TableView();
        tableView.setMaxWidth(800);
        final Label label = new Label("Services performed:");
        label.setFont(new Font("Arial", 20));

        TableColumn serviceClm = new TableColumn("Service");
        serviceClm.setCellValueFactory(new PropertyValueFactory<>("description"));
        TableColumn customerClm = new TableColumn("Customer");
        customerClm.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        TableColumn costCol = new TableColumn("Cost");
        costCol.setCellValueFactory(new PropertyValueFactory<>("cost"));
        TableColumn dateCol = new TableColumn("Date");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        TableColumn rateCol = new TableColumn("Rate");
        rateCol.setCellValueFactory(new PropertyValueFactory<>("rate"));


        tableView.getColumns().addAll(serviceClm,customerClm,costCol,dateCol,rateCol);
        initTableView(user);

        addService = new Button("Add Service");
        removeService = new Button("Remove Service");
        signOut = new Button("Sign Out");
        removeProfile = new Button("remove Profile");
        HBox buttonRow = new HBox();
        buttonRow.setAlignment(Pos.BOTTOM_CENTER);
        buttonRow.setSpacing(20);
        buttonRow.setPadding(new Insets(10,10,0,10));
        buttonRow.getChildren().addAll(addService,removeService,signOut,removeProfile);

        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(5);
        root.setPadding(new Insets(10, 0, 0, 10));
        root.getChildren().addAll(label, tableView,buttonRow);


        Scene currentScene = new Scene(root,previosScene.getWidth(),previosScene.getHeight());
        primaryStage.setScene(currentScene);
        primaryStage.show();

        addService.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AddServicePage.show(primaryStage,currentScene,user);
            }
        });

        removeService.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                RemoveServicePage.show(primaryStage,currentScene,user);
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
    private static void initTableView(User user){
        ArrayList<Report> reports = JDBC.getServicePerformed(user);
        for(Report r : reports){
            tableView.getItems().add(r);
        }
    }
}
