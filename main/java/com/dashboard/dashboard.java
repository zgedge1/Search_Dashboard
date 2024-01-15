
package com.dashboard;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class dashboard extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    // Method to show inital dashboard to show search options

    public void start(Stage primaryStage) {

        
        primaryStage.setTitle("Dashboard");


        // Fonts
        Font fontHeadingLabel = Font.font("Arial", FontWeight.BOLD, 20);

        // Heading Label
        Label headingLabel = new Label("Select a category to search");
        headingLabel.setFont(fontHeadingLabel);
        headingLabel.setTranslateX(150);
        headingLabel.setTranslateY(10);


        // Actionbutton to launch weather search screen: 
        
        Button launchWeatherScreenButton = new Button("Search Weather");

        launchWeatherScreenButton.setMaxWidth(280);
        launchWeatherScreenButton.setMaxHeight(200);
        launchWeatherScreenButton.setTranslateX(150);
        launchWeatherScreenButton.setTranslateY(80);
        launchWeatherScreenButton.setOnAction(e -> weatherFrame());        

        VBox dashboardFrame = new VBox();
        dashboardFrame.getChildren().addAll(headingLabel,launchWeatherScreenButton);
        

        Scene scene = new Scene(dashboardFrame, 600, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
    }

    public void weatherFrame() {
        
        Stage weatherFrame = new Stage();

        VBox weatherBox = new VBox();
        
        Scene weatherScene = new Scene(weatherBox, 600, 600);
        weatherFrame.setScene(weatherScene);
        weatherFrame.show();
    }

    
}