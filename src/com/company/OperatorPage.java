package com.company;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.util.StringConverter;
import models.Report;
import models.User;
import network.JDBC;

import java.lang.invoke.SwitchPoint;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class OperatorPage {

    private static TableView tableView;
    private static TableColumn firstClm;
    private static TableColumn secondeClm;
    private static TableColumn thirdClm;
    private static TableColumn fourthClm;
    private static TableColumn fifthClm;
    private static TableColumn sixthClm;

    private static ToggleGroup toggleGroup;
    private static RadioButton serviceProvider;
    private static RadioButton customer;
    private static RadioButton service;
    private static RadioButton profit;
    private static DatePicker from;
    private static DatePicker to;
    public static void show(Stage primaryStage, Scene previosScene){

        VBox root = new VBox();
        root.setSpacing(20);
        HBox selectReport = new HBox();
        selectReport.setSpacing(1);
        VBox vBox = new VBox();
        vBox.setSpacing(10);

        Label report = new Label("Report:");
        Button back = new Button("back");
        toggleGroup = new ToggleGroup();
        service = new RadioButton();
        service.setToggleGroup(toggleGroup);
        service.setUserData("Service");
        service.setText("Service");
        serviceProvider = new RadioButton();
        serviceProvider.setToggleGroup(toggleGroup);
        serviceProvider.setUserData("Service Provider");
        serviceProvider.setText("Service Provider");
        customer = new RadioButton();
        customer.setToggleGroup(toggleGroup);
        customer.setUserData("Customer");
        customer.setText("Customer");
        profit = new RadioButton();
        profit.setToggleGroup(toggleGroup);
        profit.setUserData("Profit");
        profit.setText("Profit");
        vBox.getChildren().addAll(service,customer,serviceProvider,profit);
        Label lChoice = new Label();
        ComboBox cChoice = new ComboBox();
        Label lFrom = new Label("From:");
        from = new DatePicker();
        Label lTo = new Label("To:");
        to = new DatePicker();

        selectReport.getChildren().addAll(back,report,vBox,lChoice,cChoice,lFrom,from,lTo,to);
        tableView = new TableView();

        Button show = new Button("show");
        show.setVisible(false);
        Label label = new Label();
        firstClm = new TableColumn("Service");
        firstClm.setCellValueFactory(new PropertyValueFactory<>("description"));
        secondeClm = new TableColumn("Customer");
        secondeClm.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        thirdClm = new TableColumn("Provider");
        thirdClm.setCellValueFactory(new PropertyValueFactory<>("provider_id"));
        fourthClm = new TableColumn("Cost");
        fourthClm.setCellValueFactory(new PropertyValueFactory<>("cost"));
        fifthClm = new TableColumn("Date");
        fifthClm.setCellValueFactory(new PropertyValueFactory<>("date"));
        sixthClm = new TableColumn("Rate");
        sixthClm.setCellValueFactory(new PropertyValueFactory<>("rate"));
        tableView.setPadding(new Insets(0,10,0,0));
        tableView.getColumns().addAll(firstClm,secondeClm,thirdClm,fourthClm,fifthClm,sixthClm);

        root.getChildren().addAll(selectReport,show,label,tableView);
        Scene currentScene = new Scene(root,previosScene.getWidth(),previosScene.getHeight());
        primaryStage.setScene(currentScene);

        toggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                ArrayList<Report> reports = new ArrayList<>();
                switch (newValue.getUserData().toString()){
                    case "Service":
                        label.setText(newValue.getUserData().toString());
                        lChoice.setText(newValue.getUserData().toString());
                        cChoice.setVisible(true);
                        cChoice.getItems().clear();
                        cChoice.getItems().addAll(JDBC.fetchAllService());
                        break;
                    case "Service Provider":
                        label.setText(newValue.getUserData().toString());
                        lChoice.setText(newValue.getUserData().toString());
                        cChoice.setVisible(true);
                        cChoice.getItems().clear();
                        cChoice.getItems().addAll(JDBC.fetchAllUser(false));
                        break;
                        case "Customer":
                        label.setText(newValue.getUserData().toString());
                            lChoice.setText(newValue.getUserData().toString());
                            cChoice.setVisible(true);
                        cChoice.getItems().clear();
                        cChoice.getItems().addAll(JDBC.fetchAllUser(true));

                            break;
                    case "Profit":
                        cChoice.setVisible(false);
                        label.setText(newValue.getUserData().toString());
                        break;
                }
                show.setVisible(true);

            }
        });

        show.setOnAction(new EventHandler<ActionEvent>() {
                             @Override
                             public void handle(ActionEvent event) {
                                 if(cChoice.getValue() != null && from.getValue() != null && to.getValue() != null){
                                     ArrayList<Report> reports = new ArrayList<>();
                                     switch (toggleGroup.getSelectedToggle().getUserData().toString()) {
                                         case "Service":

                                             reports = JDBC.fetchReportBaseOnService(cChoice.getValue().toString(),
                                                     from.getValue().toString(),to.getValue().toString());

                                             break;
                                         case "Service Provider":

                                             reports = JDBC.fetchReportBaseOnProvider(cChoice.getValue().toString(),
                                                     from.getValue().toString(),to.getValue().toString());
                                             break;
                                         case "Customer":

                                             reports = JDBC.fetchReportBaseOnCustomer(cChoice.getValue().toString(),
                                                     from.getValue().toString(),to.getValue().toString());
                                             break;
                                         case "Profit":
                                             //label.setText(newValue.getUserData().toString());
                                             break;
                                     }
                                     removeAllRows();
                                     for (Report r : reports) {
                                         tableView.getItems().add(r);
                                     }

                                 }
                             }
        });

        from.setConverter(new StringConverter<LocalDate>() {
            String pattern = "yyyy-MM-dd";
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

            {
                from.setPromptText(pattern.toLowerCase());
            }

            @Override public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        });

        to.setConverter(new StringConverter<LocalDate>() {
            String pattern = "yyyy-MM-dd";
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

            {
                to.setPromptText(pattern.toLowerCase());
            }

            @Override public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
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

    public static void removeAllRows(){
        for ( int i = 0; i<tableView.getItems().size(); i++) {
            tableView.getItems().clear();
        }
    }







}
