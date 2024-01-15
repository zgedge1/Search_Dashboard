
package com.dashboard;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Dashboard extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    // Method to show inital dashboard to show search options

    public void start(Stage primaryStage) {

        
        primaryStage.setTitle("Dashboard");

        // Actionbutton to launch weather search screen: 
        
        Button launchWeatherScreen = new Button("Search Weather");
        launchWeatherScreen.setOnAction(e -> weatherFrame());

        VBox dashboardFrame = new VBox();
        dashboardFrame.getChildren().addAll(launchWeatherScreen);

        Scene scene = new Scene(dashboardFrame, 600, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void weatherFrame() {
        
        Stage weatherFrame = new Stage();

        VBox weatherBox = new VBox();
        
        Scene weatherScene = new Scene(weatherBox, 600, 600);
        weatherFrame.setScene(weatherScene);
        weatherFrame.show();
    }

    
}