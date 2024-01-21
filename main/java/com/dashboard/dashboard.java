
package com.dashboard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.StringBinding;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class dashboard extends Application{


    //News API Info:

    private static String apiKey = "f8bc21d568ba450e94b0a6bb37d82c68";
    private static String apiUrl = "https://newsapi.org/v2/everything";

    //Weather API Info
    private static String weatherApiKey = "df9f6adc7d89e0757fdee3c72ae7b5eb";
    private static String weatherApiUrl = "https://api.openweathermap.org/data/2.5/weather";

    //News API Entry

    private TextArea newsApiEntry;

    //News Search Bar
    private TextArea newsSearchField; 

    //News Search Labels
    private Label authorLabel;

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


        // Actionbutton to launch news screen: 
        
        Button launchNewsScreenButton = new Button("Search News");

        launchNewsScreenButton.setMaxWidth(280);
        launchNewsScreenButton.setMaxHeight(200);
        launchNewsScreenButton.setTranslateX(160);
        launchNewsScreenButton.setTranslateY(40);
        launchNewsScreenButton.setOnAction(e -> newsFrame());        

        VBox dashboardFrame = new VBox();
        dashboardFrame.getChildren().addAll(headingLabel,launchNewsScreenButton);
        

        Scene scene = new Scene(dashboardFrame, 600, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
    }

    // News Frame Method

    public void newsFrame() {
        
        // News Frame
        Stage newsFrame = new Stage();
        

        //Fonts
        Font fontHeadingLabel = Font.font("arial", FontWeight.BOLD, 20);
        Font fontResultLavel = Font.font("arial", 20);
        
                
        //Labels

        Label heading_Label = new Label("Search Current News");
        heading_Label.setFont(fontHeadingLabel);
        heading_Label.setTranslateX(280);
        heading_Label.setTranslateY(20);

    
        //Entries
        newsSearchField = new TextArea();
        newsSearchField.setTranslateX(250);
        newsSearchField.setTranslateY(40);
        newsSearchField.setMaxWidth(310);
        newsSearchField.setMaxHeight(0);

        //newsApiEntry = new TextArea();
        //newsApiEntry.setMaxWidth(310);
        //newsApiEntry.setMaxHeight(0);
        //newsApiEntry.setTranslateX(240);
        //newsApiEntry.setTranslateY(-80);


        //Buttons:

        Button newsSearchButton = new Button("Search News");
        newsSearchButton.setTranslateX(340);
        newsSearchButton.setTranslateY(60);
        newsSearchButton.setOnAction(e -> searchNews(newsSearchField.getText()));

        // Result Labels

        authorLabel = new Label("Author");
        authorLabel.setFont(fontResultLavel);
        authorLabel.setTranslateX(150);
        authorLabel.setTranslateY(100);

        VBox newsBox = new VBox();
        newsBox.getChildren().addAll(heading_Label, newsSearchField, newsSearchButton, authorLabel);
        
        Scene newsScene = new Scene(newsBox, 800, 500);
        newsFrame.setScene(newsScene);
        newsFrame.show();
        newsFrame.setResizable(false);
    }

    // Method to build URL with user input

    public void searchNews (String userInput) {

        try {
            
            URI uri = new URI(buildApiUrl(userInput));
            URL url = uri.toURL();

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                String line;

                StringBuffer response = new StringBuffer();

                while ((line = reader.readLine())!=null) {
                    response.append(line);
                    
                }

                parseNewsData(response.toString());
                
            } else {

            }


        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static String buildApiUrl(String userInput) {
        return String.format(apiUrl + "?q=" + userInput + "&apiKey=" + apiKey);
    }

    private void parseNewsData(String newsData) {

        JSONObject json = new JSONObject(newsData);
        
        JSONArray articleArr = json.getJSONArray("articles");
        JSONObject zero = articleArr.getJSONObject(0);

        String author0 = zero.getString("author");

        authorLabel.setText(author0);



    }
    

    
}