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
import javafx.stage.Stage;
import javafx.util.StringConverter;
import models.Report;
import models.User;
import network.JDBC;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class OperatorPage {

    private static TableView reportTable;
    private static TableView userTable;

    private static TableColumn firstClm;
    private static TableColumn secondeClm;
    private static TableColumn thirdClm;
    private static TableColumn fourthClm;
    private static TableColumn fifthClm;
    private static TableColumn sixthClm;

    private static TableColumn ufirstClm;
    private static TableColumn usecondeClm;
    private static TableColumn uthirdClm;
    private static TableColumn ufourthClm;
    private static TableColumn ufifthClm;
    private static TableColumn usixthClm;
    private static TableColumn useventhClm;
    private static TableColumn ueighthClm;
    private static TableColumn uninethClm;

    private static ToggleGroup toggleGroup;
    private static RadioButton serviceProvider;
    private static RadioButton customer;
    private static RadioButton service;
    private static RadioButton profit;
    private static RadioButton user;

    private static Label lChoice;
    private static ComboBox cChoice;
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
        user = new RadioButton();
        user.setToggleGroup(toggleGroup);
        user.setUserData("User");
        user.setText("User");
        vBox.getChildren().addAll(service,customer,serviceProvider,profit,user);
        lChoice = new Label();
        cChoice = new ComboBox();
        Label lFrom = new Label("From:");
        from = new DatePicker();
        Label lTo = new Label("To:");
        to = new DatePicker();

        selectReport.getChildren().addAll(back,report,vBox,lChoice,cChoice,lFrom,from,lTo,to);


        Button show = new Button("show");
        show.setVisible(false);
        Label label = new Label();
        label.setVisible(false);

        //user table
        userTable = new TableView();
        ufirstClm = new TableColumn("FirstName");
        ufirstClm.setCellValueFactory(new PropertyValueFactory<>("first_name"));
        usecondeClm = new TableColumn("LastName");
        usecondeClm.setCellValueFactory(new PropertyValueFactory<>("last_name"));
        uthirdClm = new TableColumn("address");
        uthirdClm.setCellValueFactory(new PropertyValueFactory<>("address"));
        ufourthClm = new TableColumn("age");
        ufourthClm.setCellValueFactory(new PropertyValueFactory<>("age"));
        ufifthClm = new TableColumn("tell");
        ufifthClm.setCellValueFactory(new PropertyValueFactory<>("tell"));
        usixthClm = new TableColumn("is woman");
        usixthClm.setCellValueFactory(new PropertyValueFactory<>("sex"));
        useventhClm = new TableColumn("Username");
        useventhClm.setCellValueFactory(new PropertyValueFactory<>("username"));
        ueighthClm = new TableColumn("Rate");
        ueighthClm.setCellValueFactory(new PropertyValueFactory<>("rate"));
        uninethClm = new TableColumn("is Customer");
        uninethClm.setCellValueFactory(new PropertyValueFactory<>("customer"));

        userTable.setPadding(new Insets(0,10,0,0));
        userTable.getColumns().addAll(ufirstClm,usecondeClm,uthirdClm,ufourthClm,ufifthClm,usixthClm,
                useventhClm,ueighthClm,uninethClm);
        userTable.setVisible(false);


        //report table

        reportTable = new TableView();
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
        reportTable.setPadding(new Insets(0,10,0,0));
        reportTable.getColumns().addAll(firstClm,secondeClm,thirdClm,fourthClm,fifthClm,sixthClm);



        root.getChildren().addAll(selectReport,show,label, reportTable,userTable);
        Scene currentScene = new Scene(root,previosScene.getWidth(),previosScene.getHeight());
        primaryStage.setScene(currentScene);

        toggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                switch (newValue.getUserData().toString()){
                    case "Service":
                        reportTable.setVisible(true);
                        userTable.setVisible(false);
                        lFrom.setVisible(true);
                        from.setVisible(true);
                        lTo.setVisible(true);
                        to.setVisible(true);
                        label.setVisible(false);
                        lChoice.setText(newValue.getUserData().toString());
                        cChoice.setVisible(true);
                        cChoice.getItems().clear();
                        cChoice.getItems().addAll(JDBC.fetchAllService());
                        break;
                    case "Service Provider":
                        reportTable.setVisible(true);
                        userTable.setVisible(false);
                        lFrom.setVisible(true);
                        from.setVisible(true);
                        lTo.setVisible(true);
                        to.setVisible(true);
                        label.setVisible(false);
                        lChoice.setText(newValue.getUserData().toString());
                        cChoice.setVisible(true);
                        cChoice.getItems().clear();
                        cChoice.getItems().addAll(JDBC.fetchAllUser(false));
                        break;
                    case "Customer":
                        reportTable.setVisible(true);
                        userTable.setVisible(false);
                        lFrom.setVisible(true);
                        from.setVisible(true);
                        lTo.setVisible(true);
                        to.setVisible(true);
                        label.setVisible(false);
                        lChoice.setText(newValue.getUserData().toString());
                        cChoice.setVisible(true);
                        cChoice.getItems().clear();
                        cChoice.getItems().addAll(JDBC.fetchAllUser(true));
                        break;
                    case "Profit":
                        reportTable.setVisible(true);
                        userTable.setVisible(false);
                        lFrom.setVisible(true);
                        from.setVisible(true);
                        lTo.setVisible(true);
                        to.setVisible(true);
                        lChoice.setVisible(false);
                        cChoice.setVisible(false);
                        reportTable.setVisible(false);
                        break;
                    case "User":
                        lChoice.setText("User:");
                        cChoice.setVisible(true);
                        cChoice.getItems().addAll(JDBC.fetchAllUser(false));
                        cChoice.getItems().addAll(JDBC.fetchAllUser(true));
                        reportTable.setVisible(false);
                        userTable.setVisible(true);
                        label.setVisible(false);
                        lFrom.setVisible(false);
                        from.setVisible(false);
                        lTo.setVisible(false);
                        to.setVisible(false);
                        break;
                }
                show.setVisible(true);



            }
        });

        show.setOnAction(new EventHandler<ActionEvent>() {
                             @Override
                             public void handle(ActionEvent event) {
                                 if (toggleGroup.getSelectedToggle().getUserData().toString()=="Profit") {

                                     label.setVisible(true);
                                     label.setText("Profit:" + JDBC.calcProfit(from.getValue().toString(), to.getValue().toString()));

                                 }else if (toggleGroup.getSelectedToggle().getUserData().toString()== "User"){

                                     intiUserTable(cChoice.getValue().toString());

                                 }
                                 else if(cChoice.getValue() != null && from.getValue() != null && to.getValue() != null) {
                                     ArrayList<Report> reports;
                                     switch (toggleGroup.getSelectedToggle().getUserData().toString()) {
                                         case "Service":
                                            reports  = JDBC.fetchReportBaseOnService(cChoice.getValue().toString(),
                                                     from.getValue().toString(),to.getValue().toString());
                                             initReportTable(reports);
                                             break;
                                         case "Service Provider":
                                             reports  = JDBC.fetchReportBaseOnProvider(cChoice.getValue().toString(),
                                                     from.getValue().toString(),to.getValue().toString());
                                             initReportTable(reports);
                                             break;
                                         case "Customer":
                                             reports  = JDBC.fetchReportBaseOnCustomer(cChoice.getValue().toString(),
                                                     from.getValue().toString(),to.getValue().toString());
                                             initReportTable(reports);
                                             break;
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

    public static void initReportTable(ArrayList<Report> reports){
        removeReportTableRows();

        for (Report r : reports) {
            reportTable.getItems().add(r);
        }

    }

    public static void intiUserTable(String username){
        removeUserTableRows();
        User user = JDBC.getUsers(username);
        userTable.getItems().add(user);

    }
    public static void removeReportTableRows(){
        for (int i = 0; i< reportTable.getItems().size(); i++) {
            reportTable.getItems().clear();
        }
    }

    public static void removeUserTableRows(){
        for (int i = 0; i< userTable.getItems().size(); i++) {
            userTable.getItems().clear();
        }
    }







}
