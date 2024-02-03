
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
import javafx.beans.binding.StringBinding;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
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

    //Stock API Info

    private static String stockApiUrl = "https://api.twelvedata.com/time_series";
    private static String stockApiKey = "b31fcc2718da460a83388bca924328c7";


    //Weather API info
    
    private static String weatherApiUrl = "https://api.openweathermap.org/data/2.5/weather";
    private static String weatherApiKey = "df9f6adc7d89e0757fdee3c72ae7b5eb";

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

    // Stock Search Frame

    private TextArea stockSearchFame;

    private Label stockTitle;
    private Label highLabel;
    private Label lowLabel;
    private Label openLabel;
    private Label closeLabel;

    private Label stockPriceHighResult;
    private Label stockPriceLowResult;
    private Label stockPriceOpenResult;
    private Label stockPriceCloseResult;

    // Search Weather Frame 

    private TextArea searchWeatherField;

    private Label resultWeatheArea;
    private Label resultWindArea;
    private Label resultHumidity;


    public static void main(String[] args) {
        launch(args);
    }

    public static void setRoot(String root) {

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

        // Actions to launch weather screen


        Button launchWeatherScreenButton = new Button("Search Weather");
        launchWeatherScreenButton.setMaxWidth(280);
        launchWeatherScreenButton.setMaxHeight(200);
        launchWeatherScreenButton.setTranslateX(160);
        launchWeatherScreenButton.setTranslateY(80);
        launchWeatherScreenButton.setOnAction(e -> launchWeatherScreen());

        VBox dashboardFrame = new VBox();
        dashboardFrame.getChildren().addAll(headingLabel,launchnewsScreenButton, launchStockScreenButton, launchWeatherScreenButton);
        

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
        Font stockTitleFont = Font.font("Arial", FontWeight.BOLD, 20);

        Label stockHeadingLabel = new Label("Search Today's Stocks");
        stockHeadingLabel.setTranslateX(180);
        stockHeadingLabel.setTranslateY(20);
        stockHeadingLabel.setFont(fontStockHeading);

        stockSearchFame = new TextArea();
        stockSearchFame.setTranslateX(150);
        stockSearchFame.setTranslateY(40);
        stockSearchFame.setMaxWidth(300);
        stockSearchFame.setMaxHeight(10);

        //stockTitle = new Label("Current Stock Information For: ");
        //stockTitle.setFont(stockTitleFont);
        //stockTitle.setTranslateX(140);
        //stockTitle.setTranslateY(140);

        openLabel = new Label("Opening Price:");
        openLabel.setTranslateX(80);
        openLabel.setTranslateY(110);
        openLabel.setFont(stockTitleFont);

        highLabel = new Label("High Price:");
        highLabel.setTranslateX(80);
        highLabel.setTranslateY(170);
        highLabel.setFont(stockTitleFont);

        lowLabel = new Label("Low Price");
        lowLabel.setTranslateX(80);
        lowLabel.setTranslateY(230);
        lowLabel.setFont(stockTitleFont);

        closeLabel = new Label("Closing Price: ");
        closeLabel.setTranslateX(80);
        closeLabel.setTranslateY(300);
        closeLabel.setFont(stockTitleFont);

        stockPriceOpenResult = new Label();
        stockPriceOpenResult.setTranslateX(340);
        stockPriceOpenResult.setTranslateY(10);
        stockPriceOpenResult.setFont(stockTitleFont);

        stockPriceHighResult = new Label();
        stockPriceHighResult.setTranslateX(340);
        stockPriceHighResult.setTranslateY(70);
        stockPriceHighResult.setFont(stockTitleFont);
        
        stockPriceLowResult = new Label();
        stockPriceLowResult.setTranslateX(340);
        stockPriceLowResult.setTranslateY(130);
        stockPriceLowResult.setFont(stockTitleFont);
        
        stockPriceCloseResult = new Label();
        stockPriceCloseResult.setTranslateX(340);
        stockPriceCloseResult.setTranslateY(200);
        stockPriceCloseResult.setFont(stockTitleFont);

        Button stockSearchButton = new Button("Search");
        stockSearchButton.setTranslateX(270);
        stockSearchButton.setTranslateY(60);
        stockSearchButton.setOnAction(e -> searchStock(stockSearchFame.getText()));


        VBox stockBox = new VBox();
        stockBox.getChildren().addAll(stockHeadingLabel, stockSearchFame, stockSearchButton, openLabel, highLabel,
        lowLabel, closeLabel, stockPriceOpenResult, stockPriceHighResult, stockPriceLowResult, stockPriceCloseResult);

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
        
        String symbols = stockUserInput;
        String interval = "1min";

        return String.format("%s?symbol=%s&interval=%s&apikey=%s", stockApiUrl, symbols, interval, stockApiKey);

    }

    private void parseStockData(String getStockData) {

        JSONObject stockJson = new JSONObject(getStockData);
        JSONArray valueArr = stockJson.getJSONArray("values");
        
        JSONObject zeroStockObject = valueArr.getJSONObject(0);



        String openStockPrice = zeroStockObject.getString("open");
        String highStockPrice = zeroStockObject.getString("high");
        String lowStockPrice = zeroStockObject.getString("low");
        String closeStockPrice = zeroStockObject.getString("close");

        Double openStringtoConvert = Double.parseDouble(openStockPrice);
        Double highStringConvert = Double.parseDouble(highStockPrice);
        Double lowStringConvert = Double.parseDouble(lowStockPrice);
        Double closeStringConvert = Double.parseDouble(closeStockPrice);

        String formatOpenPrice = String.format("$%.2f", openStringtoConvert);
        String formatHighPrice = String.format("$%.2f", highStringConvert);
        String formatLowPrice = String.format("$%.2f", lowStringConvert);
        String formatClosePrice = String.format("$%.2f", closeStringConvert);

        stockPriceOpenResult.setText(formatOpenPrice);
        stockPriceHighResult.setText(formatHighPrice);
        stockPriceLowResult.setText(formatLowPrice);
        stockPriceCloseResult.setText(formatClosePrice);

    }

    public void launchWeatherScreen() {

        Font lblFont = Font.font("arial", FontWeight.BOLD, 20);
        
        Stage weatherStage = new Stage();

        searchWeatherField = new TextArea();
        searchWeatherField.setMaxHeight(40);
        searchWeatherField.setMaxWidth(200);
        searchWeatherField.setTranslateX(190);
        searchWeatherField.setTranslateY(30);

        Button searchWeatherButton = new Button("Search Weather");
        searchWeatherButton.setTranslateX(230);
        searchWeatherButton.setTranslateY(60);

        Label temp_lbl = new Label("Temperature: ");
        temp_lbl.setTranslateX(60);
        temp_lbl.setTranslateY(120);
        temp_lbl.setFont(lblFont);

        Label windLbl = new Label("Wind Speed: ");
        windLbl.setTranslateX(60);
        windLbl.setTranslateY(170);
        windLbl.setFont(lblFont);

        Label humidityLbl = new Label("Humidity: ");
        humidityLbl.setTranslateX(60);
        humidityLbl.setTranslateY(220);
        humidityLbl.setFont(lblFont);
        

        VBox weatherBox = new VBox();
        weatherBox.getChildren().addAll(searchWeatherField, searchWeatherButton, temp_lbl, windLbl, humidityLbl);
        
        Scene weatherScene = new Scene(weatherBox, 600, 600);
        weatherStage.setScene(weatherScene);
        weatherStage.show();
        
    }

    public void searchWeather(String searchWeatherField) throws URISyntaxException {

        URI uri = new URI(buildWeatherApi(searchWeatherField));
    }

    private String buildWeatherApi(String searchWeatherField) {

        return String.format(searchWeatherField, null);

        
        
    }
    
    
    
} 