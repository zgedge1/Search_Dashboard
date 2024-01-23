
package com.dashboard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class dashboard extends Application{

    //news API Info

    private static String newsApiKey = "f8bc21d568ba450e94b0a6bb37d82c68";
    private static String newsApiUrl = "https://newsapi.org/v2/everything";


    //news Search Bar
    private TextArea newsSearchField; 

    // News Results

    private ImageView newsImage0;

    private Label titleLbl0;
    private Label titleLbl1;
    private Label titleLbl2;
    
    private Label descriptionLbl0; 
    private Label descriptionLbl1;
    private Label descriptionlabel2;

    // Stock Search Frmae

    private TextArea stockSearchFame;

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


        // Actionbutton to launch news search screen: 
        
        Button launchnewsScreenButton = new Button("Search News");
        launchnewsScreenButton.setMaxWidth(280);
        launchnewsScreenButton.setMaxHeight(200);
        launchnewsScreenButton.setTranslateX(160);
        launchnewsScreenButton.setTranslateY(40);
        launchnewsScreenButton.setOnAction(e -> newsFrame());
        
        // Actionbutton to launch stock screen:

        Button launchStockScreenButton = new Button("Search Stocks");
        launchStockScreenButton.setMaxWidth(280);
        launchStockScreenButton.setMaxHeight(200);
        launchStockScreenButton.setMaxHeight(200);
        launchStockScreenButton.setTranslateX(160);
        launchStockScreenButton.setTranslateY(60);
        launchStockScreenButton.setOnAction(e -> openStockWindow());

        VBox dashboardFrame = new VBox();
        dashboardFrame.getChildren().addAll(headingLabel,launchnewsScreenButton, launchStockScreenButton);
        

        Scene scene = new Scene(dashboardFrame, 600, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
    }

    // news Frame Method

    public void newsFrame() {
        
        // news Frame
        Stage newsFrame = new Stage();
        

        //Fonts
        Font fontHeadingLabel = Font.font("arial", FontWeight.BOLD, 20);
        Font fontResuleLabel = Font.font("arial", 18);

        //news Search Bar
        newsSearchField = new TextArea();
        newsSearchField.setTranslateX(250);
        newsSearchField.setTranslateY(25);
        newsSearchField.setMaxWidth(310);
        newsSearchField.setMaxHeight(0);

        //Buttons:

        Button searchButton = new Button("Search");
        searchButton.setTranslateX(360);
        searchButton.setTranslateY(50);
        searchButton.setOnAction(e -> searchnews(newsSearchField.getText()));

        //Labels

        Label heading_Label = new Label("Search Current news");
        heading_Label.setFont(fontHeadingLabel);
        heading_Label.setTranslateX(270);
        heading_Label.setTranslateY(10);

        // NewsResult Labels:

        newsImage0 = new ImageView();
        newsImage0.setFitHeight(200);
        newsImage0.setFitWidth(200);
        

        titleLbl0 = new Label();
        titleLbl0.setTranslateX(80);
        titleLbl0.setTranslateY(90);
        titleLbl0.setFont(fontResuleLabel);
        titleLbl0.setWrapText(true);
        titleLbl0.setPrefWidth(300);

        titleLbl1 = new Label();
        titleLbl1.setTranslateX(80);
        titleLbl1.setTranslateY(250);
        titleLbl1.setFont(fontResuleLabel);
        titleLbl1.setWrapText(true);
        titleLbl1.setPrefWidth(300);
        
        descriptionLbl0 = new Label();
        descriptionLbl0.setTranslateX(400);
        descriptionLbl0.setTranslateY(-10);
        descriptionLbl0.setFont(fontResuleLabel);
        descriptionLbl0.setWrapText(true);
        descriptionLbl0.setPrefWidth(300);

        descriptionLbl1 = new Label();
        descriptionLbl1.setTranslateX(400);
        descriptionLbl1.setTranslateY(80);
        descriptionLbl1.setFont(fontResuleLabel);
        descriptionLbl1.setWrapText(true);
        descriptionLbl1.setPrefWidth(300);;

        

        VBox newsBox = new VBox();
        newsBox.getChildren().addAll(heading_Label, newsSearchField, searchButton, titleLbl0, titleLbl1 ,descriptionLbl0, descriptionLbl1 ,newsImage0);
        
        Scene newsScene = new Scene(newsBox, 800, 800);
        newsFrame.setScene(newsScene);
        newsFrame.show();
        newsFrame.setResizable(false);
    }

    // Method to build URL with user input

    public void searchnews(String newsUserInput) {

        try {
            
            URI uri = new URI(buildnewsApiUrl(newsUserInput));
            URL url = uri.toURL();

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                String line;

                StringBuffer response = new StringBuffer();

                while ((line = reader.readLine())!=null) {
                    response.append(line);
                    
                }

                parsenewsData(response.toString());
                
            }


        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
        // Build API url with the user input

    private static String buildnewsApiUrl(String newsUserInput) {
        return String.format(newsApiUrl + "?q=" + newsUserInput + "&apiKey=" + newsApiKey);
    }

    //  Display news Data
    private void parsenewsData(String getnewsData){

        JSONObject json = new JSONObject(getnewsData);

        JSONArray articleArr = json.getJSONArray("articles");
        JSONObject zero = articleArr.getJSONObject(0);
        JSONObject one = articleArr.getJSONObject(1);

        //String imageurl0 = zero.getString("urlToImage");
        String title0 = zero.getString("title");
        String description0 = zero.getString("description");

        String title1 = one.getString("title");
        String description1 = one.getString("description");


        titleLbl0 .setText(title0);
        descriptionLbl0.setText(description0);

        titleLbl1.setText(title1);
        descriptionLbl1.setText(description1);
        
        //displayNewsImage(imageurl0);

    }

    private void openStockWindow() {

        Stage stockWindowStage = new Stage();

        Font fontStockHeading = Font.font("Arial", FontWeight.BLACK, 20);

        Label stockHeadingLabel = new Label("Search Today's Stocks");
        stockHeadingLabel.setTranslateX(180);
        stockHeadingLabel.setTranslateY(20);
        stockHeadingLabel.setFont(fontStockHeading);

        stockSearchFame = new TextArea();
        stockSearchFame.setTranslateX(150);
        stockSearchFame.setTranslateY(40);
        stockSearchFame.setMaxWidth(300);
        stockSearchFame.setMaxHeight(10);

        Button stockSearchButton = new Button("Search");
        stockSearchButton.setTranslateX(270);
        stockSearchButton.setTranslateY(70);

        VBox stockBox = new VBox();
        stockBox.getChildren().addAll(stockHeadingLabel, stockSearchFame, stockSearchButton);

        Scene stockScene = new Scene(stockBox, 600, 600);
        stockWindowStage.setScene(stockScene);
        stockWindowStage.show();
        stockWindowStage.setResizable(false);


    }

    private void searchStock(String stockUserInput) {

        try {
            URI stockURI = new URI(buildStockApi(stockUserInput));
            URL stockURL = stockURI.toURL();

            HttpURLConnection StockUrlconnection = (HttpURLConnection) stockURL.openConnection();

            if (StockUrlconnection.getResponseCode() == HttpURLConnection.HTTP_OK) {

                BufferedReader stockReader = new BufferedReader(new InputStreamReader(StockUrlconnection.getInputStream()));

                String stockLine;

                StringBuffer stockApiResponse = new StringBuffer();

                while ((stockLine = stockReader.readLine())!=null) {
                    stockApiResponse.append(stockLine);
                }

                parseStockData(stockApiResponse.toString());



                
            }


        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    private static String buildStockApi(String stockUserInput) {
        return String.format(stockUserInput, null)

    }

    private void parseStockData(String getData) {
        
    }

    
}