package com.company;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class ProviderPage {
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

        TableColumn serviceClm = new TableColumn("Service");
        TableColumn customerClm = new TableColumn("Customer");
        TableColumn costCol = new TableColumn("Cost");
        TableColumn dateCol = new TableColumn("Date");
        tableView.setPadding(new Insets(0,10,0,0));


        tableView.getColumns().addAll(serviceClm,customerClm,costCol,dateCol);


        addService = new Button("Add Service");
        signOut = new Button("Sign Out");
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
    private static void fetchTableView(){

    }
}
