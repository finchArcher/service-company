package com.company;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FirstPage extends Application {

    public static void main(String... args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        Button sign_in = new Button("Sign in");
        Button sign_up = new Button("Sign up");
        vBox.setPadding(new Insets(10,10,10,10));
        vBox.getChildren().addAll(sign_in,sign_up);
        Scene scene = new Scene(vBox,800,600);
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
                SignUpPage.show(primaryStage);
            }
        });
    }




}
