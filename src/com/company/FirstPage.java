package com.company;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FirstPage extends Application {

    public static void main(String... args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        primaryStage.setTitle("WELLCOME TO SPMS");
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        Label wellcome = new Label("Wellcome to Spms");
        Button sign_in = new Button("Sign in");
        Button sign_up = new Button("Sign up");
        vBox.setPadding(new Insets(10,10,10,10));
        vBox.getChildren().addAll(wellcome,sign_in,sign_up);
        Scene scene = new Scene(vBox,1000,600);
        primaryStage.setScene(scene);

        primaryStage.show();

        sign_in.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                SignInPage.show(primaryStage,scene);
            }
        });
        sign_up.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SignUpPage.show(primaryStage,scene);
            }
        });
    }




}
