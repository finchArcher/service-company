package com.company;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import models.Report;
import network.DBInterface;

import java.sql.SQLException;
import java.util.ArrayList;


public class ProviderPage {
    //TODO: create function for buttons , test initiTableView function
    private static TableView tableView;
    private static Button addService;
    private static Button signOut;
    private static Button removeService;
    private static Button removeProfile;
    public static void show(Stage stage, Scene previosScene) {
        tableView = new TableView();
        Button back = new Button("back");


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
        signOut = new Button("DBInterface Out");
        HBox firstRow = new HBox();
        firstRow.setSpacing(20);
        firstRow.setPadding(new Insets(10,10,0,10));
        firstRow.getChildren().addAll(addService,signOut);

        removeService = new Button("Remove Service");
        removeProfile = new Button("remove Profile");
        HBox secondRow = new HBox();
        firstRow.setSpacing(20);
        firstRow.setPadding(new Insets(10,10,0,10));
        firstRow.getChildren().addAll(removeService,removeProfile);

        VBox root = new VBox();
        root.setSpacing(5);
        root.setPadding(new Insets(10, 0, 0, 10));
        root.getChildren().addAll(back,label, tableView,firstRow,secondRow);


        Scene scene = new Scene(root,previosScene.getWidth(),previosScene.getHeight());
        stage.setScene(scene);
        stage.show();

        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(previosScene);
                stage.show();
            }
        });

    }
    private static void initTableView(){
        try {
            ArrayList<Report> reports = DBInterface.fetchReport(SignInPage.user.getUsername());
            for(Report r : reports){
                tableView.getItems().add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
