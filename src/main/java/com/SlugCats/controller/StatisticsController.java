package com.SlugCats.controller;

import com.SlugCats.Main;
import com.SlugCats.gamestracking.GameDetector;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import com.SlugCats.DAOs.*;
import com.SlugCats.Models.User;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class StatisticsController {
    // Components of the Statistics window.
    @FXML
    private BorderPane rootPane;
    @FXML
    private ImageView logoImage;
    @FXML
    private Button backButton;
    @FXML
    private Label statisticsLabel;
    @FXML
    private Tab dailyTab;
    @FXML
    private Label dailyStatLabel;
    @FXML
    private Tab weeklyTab;
    @FXML
    private Label weeklyStatLabel;
    @FXML
    private Tab monthlyTab;
    @FXML
    private Label monthlyStatLabel;
    @FXML
    private Tab yearlyTab;
    @FXML
    private Label yearlyStatLabel;
    @FXML
    private Tab gameTab;
    @FXML
    private Button selectButton;
    @FXML
    private Label gameLabel;
    @FXML
    private Label gameStatLabel;

    private String displayName;

    @FXML
    public void initialize() {
        // Initialise and set the logo image into the image view.
        Image logo = new Image(getClass().getResource("/images/snailcat.PNG").toString(),true);
        logoImage.setImage(logo);
        HBox headerBox = new HBox(20);
        headerBox.getChildren().addAll(
                logoImage,
                backButton,
                statisticsLabel
        );
        rootPane.setTop(headerBox);

        Insets tabPadding = new Insets(25.0,0.0,0.0,15.0);
        dailyStatLabel.setPadding(tabPadding);
        weeklyStatLabel.setPadding(tabPadding);
        monthlyStatLabel.setPadding(tabPadding);
        yearlyStatLabel.setPadding(tabPadding);
        Image selectGame = new Image(getClass().getResource("/images/folder.PNG").toString(),true);
        ImageView selectView = new ImageView(selectGame);
        selectView.setFitHeight(25);
        selectView.setPreserveRatio(true);
        selectButton.setGraphic(selectView);
        HBox selectGameBox = new HBox();
        selectGameBox.getChildren().addAll(
                selectButton,
                gameLabel
        );
        VBox gameTabBox = new VBox();
        gameStatLabel.setPadding(new Insets(15.0,0.0,0.0,0.0));
        gameTabBox.getChildren().addAll(
                selectGameBox,
                gameStatLabel
        );
        gameTabBox.setPadding(tabPadding);
        gameTab.setContent(gameTabBox);
        TabPane statisticsPane = new TabPane();
        statisticsPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        statisticsPane.setTabMinHeight(200.0);
        statisticsPane.setTabMinWidth(165);
        statisticsPane.getTabs().addAll(
                dailyTab,
                weeklyTab,
                monthlyTab,
                yearlyTab,
                gameTab
        );
        rootPane.setCenter(statisticsPane);
    }

    @FXML
    protected void onBackButtonClick() throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("home-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Main.WIDTH, Main.HEIGHT);
        stage.setResizable(false);
        stage.setScene(scene);
    }

    @FXML
    protected void onSelectButtonClick() throws IOException {
        GameDetector gameDetector = new GameDetector();
        File selectedFile = gameDetector.choosefile();

        String selectedGameTitle = "No Game Detected";
        if (selectedFile != null && selectedFile.exists()) {
            String gameName = selectedFile.getName();

            int lastDotIndex = gameName.lastIndexOf('.');
            if (lastDotIndex != -1) {
                displayName = gameName.substring(0, lastDotIndex);
            } else {
                displayName = gameName;
            }

            // Spaces for visual clarity.
            selectedGameTitle = "   Selected Game: " + displayName;
        } else {
            selectedGameTitle = "   Selected Game: No Game Detected";
        }

        // Update the label to show just the game name
        gameLabel.setText(selectedGameTitle);
    }

    // NOTE TO TEAM: So these methods detect when a tab has been selected.
    // Put logic here to load in the statistics for each tab. You can use \n for the labels for next line as well.
    @FXML
    protected void onDailyTabSelection() throws IOException {
        String dailyStats = "[Insert Daily Stats Here]";

        dailyStatLabel.setText(dailyStats.toString());
    }

    @FXML
    protected void onWeeklyTabSelection() throws IOException {
        String weeklyStats = "[Insert Weekly Stats Here]";

        //Logic to get and set weekly stats here etc.

        weeklyStatLabel.setText(weeklyStats);
    }

    @FXML
    protected void onMonthlyTabSelection() throws IOException {
        String monthlyStats = "[Insert Monthly Stats Here]";

        //Logic to get and set monthly stats here etc.

        monthlyStatLabel.setText(monthlyStats);
    }

    @FXML
    protected void onYearlyTabSelection() throws IOException {
        String yearlyStats = "[Insert Yearly Stats Here]";

        //Logic to get and set yearly stats here etc.

        yearlyStatLabel.setText(yearlyStats);
    }

    @FXML
    protected void onGameTabSelection() throws IOException {
        String gameStats = "[Insert Game Specific Stats Here]";
        // If user has yet to select a game.
        if (displayName == null || displayName.isEmpty()) {
            gameStats = "^^^ Select an application to view specific statistics.";
        }
        else {

            //Logic to get and set game specific stats here etc.

        }

        gameStatLabel.setText(gameStats);
    }
}
