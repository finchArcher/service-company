package com.company;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import models.User;
import network.JDBC;

public class SignUpPage {

    private static GridPane root;
    private static Button back;
    private static Label lIsCustomer;
    private static CheckBox cIsCustomer;
    private static Label lFirstname;
    private static TextField tFirstnaem;
    private static Label lLastname;
    private static TextField tLastname;
    private static Label lAddress;
    private static TextField tAddress;
    private static Label lTell;
    private static TextField tTell;
    private static Label lSex;
    private static HBox radioGroup;
    private static ToggleGroup sex;
    private static RadioButton male;
    private static RadioButton female;
    private static Label lAge;
    private static TextField tAge;

    private static Label lUsername = new Label("username");
    private static TextField tUsername = new TextField();

    private static Label lPassword = new Label("password");
    private static PasswordField tPassword = new PasswordField();

    private static Label usernaemError = new Label();
    private static Label passwordError = new Label();

    private static Button signUp;
    private static User user;


    public static void show(Stage stage, Scene previosScene) {
        root = new GridPane();
        root.setHgap(3);
        root.setVgap(12);

        back = new Button("back");
        lIsCustomer = new Label("Are you customer?");
        cIsCustomer = new CheckBox();
        lFirstname = new Label("First name:");
        tFirstnaem = new TextField();
        lLastname = new Label("Last name:");
        tLastname = new TextField();
        lAddress = new Label("Address:");
        tAddress = new TextField();
        lTell = new Label("Tell:");
        tTell =new TextField();
        lSex = new Label("Sex:");
        radioGroup = new HBox();
        sex = new ToggleGroup();
        male = new RadioButton("male");
        male.setToggleGroup(sex);
        male.setUserData(true);
        female = new RadioButton("female");
        female.setToggleGroup(sex);
        female.setUserData(false);
        radioGroup.getChildren().addAll(male,female);
        lAge = new Label("Age:");
        tAge = new TextField();
        signUp = new Button("Sign up");
        root.add(back,0,0);
        root.add(lIsCustomer,0,1);
        root.add(cIsCustomer,1,1);
        root.add(lFirstname,0,2);
        root.add(tFirstnaem,1,2);
        root.add(lLastname,0,3);
        root.add(tLastname,1,3);
        root.add(lAddress,0,4);
        root.add(tAddress,1,4);
        root.add(lSex,0,5);
        root.add(radioGroup,1,5);
        root.add(lAge,0,6);
        root.add(tAge,1,6);
        root.add(lTell,0,7);
        root.add(tTell,1,7);

        root.add(lUsername,0,9);
        root.add(tUsername,1,9);
        root.add(usernaemError,2,9);
        root.add(lPassword,0,10);
        root.add(tPassword,1,10);
        root.add(passwordError,2,10);
        root.add(signUp,1,11);



        Scene currentScene = new Scene(root,previosScene.getWidth(),previosScene.getHeight());
        stage.setScene(currentScene);
        stage.show();

        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(previosScene);
                stage.show();
            }
        });
        signUp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //TODO: check data for valid
                user = new User(tUsername.getText(),tPassword.getText());

                if(createUser(user)){
                    if (addUser(user)){
                        if (user.isCustomer()){
                            CustomerPage.show(stage,currentScene,user,true);
                        }else {
                            ProviderPage.show(stage,currentScene,user,true);
                        }
                    }
                }
            }
        });
    }
    private static boolean checksex(ToggleGroup toggleGroup){
        if (toggleGroup.getSelectedToggle() != null)
            return (boolean)toggleGroup.getSelectedToggle().getUserData();
        else {
            return false;
        }
    }

    private static boolean addUser(User user){
        user.setCustomer(cIsCustomer.isSelected());
        user.setFirst_name(tFirstnaem.getText());
        user.setLast_name(tLastname.getText());
        user.setAddress(tAddress.getText());
        user.setTell(tTell.getText());
        user.setSex(checksex(sex));
        user.setAge(tAge.getText());
        user.setRate(3);
        return JDBC.insert("users",user) == 1;

    }

    private static boolean createUser(User user){
        if(user.getUsername().isEmpty()){
            usernaemError.setText("");
            passwordError.setText("");
            tUsername.clear();
            usernaemError.setText("username can't be empty!");
            usernaemError.setTextFill(Color.RED);
        }else if (user.getPassword().isEmpty()){
            passwordError.setText("");
            usernaemError.setText("");
            tPassword.clear();
            passwordError.setText("password can't be empty");
            passwordError.setTextFill(Color.RED);
        }else {
            if (!JDBC.checkUser(user.getUsername())){
                return true;
            }else {
                tPassword.clear();
                lPassword.setText("");
                usernaemError.setText("your username is duplicate, please insert another one");
                usernaemError.setTextFill(Color.RED);
            }

        }
        return false;
    }

}
