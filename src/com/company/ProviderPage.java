package com.company;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
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

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;


public class ProviderPage {
    //TODO:test initiTableView function
    private static TableView tableView;
    private static Button addService;
    private static Button signOut;
    private static Button removeService;
    private static Button removeProfile;
    public static void show(Stage stage, Scene previosScene, User user, Boolean isSignUp) {
        tableView = new TableView();
        final Label label = new Label("Services performed:");
        label.setFont(new Font("Arial", 20));

        TableColumn serviceIdClm = new TableColumn("Service ID");
        serviceIdClm.setCellValueFactory(new PropertyValueFactory<>("service_id"));
        TableColumn serviceClm = new TableColumn("Service");
        serviceClm.setCellValueFactory(new PropertyValueFactory<>("description"));
        TableColumn customerClm = new TableColumn("Customer");
        customerClm.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        TableColumn costCol = new TableColumn("Cost");
        costCol.setCellValueFactory(new PropertyValueFactory<>("cost"));
        TableColumn dateCol = new TableColumn("Date");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        tableView.setPadding(new Insets(0,10,0,0));


        tableView.getColumns().addAll(serviceClm,customerClm,costCol,dateCol);
        //initTableView();

        addService = new Button("Add Service");
        removeService = new Button("Remove Service");
        signOut = new Button("Sign Out");
        removeProfile = new Button("remove Profile");
        HBox buttonRow = new HBox();
        buttonRow.setSpacing(20);
        buttonRow.setPadding(new Insets(10,10,0,10));
        buttonRow.getChildren().addAll(addService,removeService,signOut,removeProfile);

        VBox root = new VBox();
        root.setSpacing(5);
        root.setPadding(new Insets(10, 0, 0, 10));
        root.getChildren().addAll(label, tableView,buttonRow);


        Scene currentScene = new Scene(root,previosScene.getWidth(),previosScene.getHeight());
        stage.setScene(currentScene);
        stage.show();

        addService.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AddServicePage.show(stage,currentScene,user);
            }
        });

        removeService.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                RemoveServicePage.show(stage,currentScene,user);
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
    /*private static void initTableView(){
        try {
            ArrayList<Report> reports = JDBC.fetchReport(SignInPage.user.getUsername());
            for(Report r : reports){
                tableView.getItems().add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
}
