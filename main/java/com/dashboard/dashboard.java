
package com.dashboard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.json.JSONObject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class dashboard extends Application{

    private static String weatherApiKey = // Enter API Key;
    private static String weatherApiUrl = "https://api.openweathermap.org/data/2.5/weather";

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
        launchWeatherScreenButton.setTranslateY(60);
        launchWeatherScreenButton.setOnAction(e -> weatherFrame());        

        VBox dashboardFrame = new VBox();
        dashboardFrame.getChildren().addAll(headingLabel,launchWeatherScreenButton);
        

        Scene scene = new Scene(dashboardFrame, 600, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
    }

    // Weather Frame Method

    public void weatherFrame() {
        
        Stage weatherFrame = new Stage();

        VBox weatherBox = new VBox();
        
        Scene weatherScene = new Scene(weatherBox, 600, 600);
        weatherFrame.setScene(weatherScene);
        weatherFrame.show();
    }

    // Method to connect to the API 

    public void searchWeather(String weatherUserInput) {

        try {
            
            URI uri = new URI(buildWeatherApiUrl(weatherUserInput));
            URL url = uri.toURL();

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                String line;

                StringBuffer response = new StringBuffer();

                while ((line = reader.readLine())!=null) {
                    response.append(line);
                    
                }

                parseWeatherData(response.toString());
                
            }


        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private static String buildWeatherApiUrl(String weatherUserInput) {
        return String.format("%s?q=%s&appid=%s&units=metric", weatherApiUrl, weatherUserInput, weatherApiKey);
    }

    private void parseWeatherData(String getWeatherData){

        JSONObject json = new JSONObject(getWeatherData);
        


    }

    
}