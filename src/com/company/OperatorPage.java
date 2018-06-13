package com.company;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class OperatorPage {

    private static TableView tableView;
    private static ToggleGroup toggleGroup;
    private static RadioButton serviceProvider;
    private static RadioButton customer;
    private static RadioButton service;
    private static RadioButton profit;
    public static void show(Stage primaryStage, Scene previosScene){
        VBox root = new VBox();
        root.setSpacing(20);
        HBox selectReport = new HBox();
        selectReport.setSpacing(20);
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        Label report = new Label("Report:");

        toggleGroup = new ToggleGroup();
        service = new RadioButton();
        service.setToggleGroup(toggleGroup);
        service.setText("Service");
        serviceProvider = new RadioButton();
        serviceProvider.setToggleGroup(toggleGroup);
        serviceProvider.setText("Service Provider");
        customer = new RadioButton();
        customer.setToggleGroup(toggleGroup);
        customer.setText("Customer");
        profit = new RadioButton();
        profit.setToggleGroup(toggleGroup);
        profit.setText("Profit");
        vBox.getChildren().addAll(service,customer,serviceProvider,profit);
        selectReport.getChildren().addAll(report,vBox);
        tableView = new TableView();

        Label label = new Label("Services performed:");
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
        tableView.setPadding(new Insets(0,10,0,0));
        tableView.getColumns().addAll(serviceClm,customerClm,costCol,dateCol,rateCol);
        //initTableView(user);
        root.getChildren().addAll(selectReport,label,tableView);
        Scene currentScene = new Scene(root,previosScene.getWidth(),previosScene.getHeight());
        primaryStage.setScene(currentScene);
    }
}
