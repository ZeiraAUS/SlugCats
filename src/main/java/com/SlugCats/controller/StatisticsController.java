package com.SlugCats.controller;

import com.SlugCats.Models.GameTime;
import com.SlugCats.gamestracking.GameDetector;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import com.SlugCats.DAOs.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class StatisticsController {
    // Components of the Statistics window.
    @FXML
    private BorderPane rootPane;
    @FXML
    private ImageView logoImage;
    @FXML
    private Button backButton, selectButton;
    @FXML
    private Label statisticsLabel, dailyStatLabel, monthlyStatLabel, yearlyStatLabel, gameLabel, gameStatLabel;
    @FXML
    private Tab dailyTab, monthlyTab, yearlyTab;

    private String displayName;

    // Class for configuring controller components.
    private Components components = new Components();

    GameTimeDAO _gameTimeRepo = new GameTimeDAO();
    GameDAO _gameRepo = new GameDAO();

    /**
     * Initialize Statistics window components.
     */
    @FXML
    public void initialize() {
        // Initialise and set the logo image into the image view.
        components.setLogoImage(logoImage);
        HBox headerBox = new HBox(20);
        headerBox.getChildren().addAll(
                logoImage,
                backButton,
                statisticsLabel
        );
        rootPane.setTop(headerBox);

        Insets tabPadding = new Insets(25.0,0.0,0.0,15.0);
        dailyStatLabel.setPadding(tabPadding);
        monthlyStatLabel.setPadding(tabPadding);
        yearlyStatLabel.setPadding(tabPadding);
        components.setButtonImage(selectButton,"/images/folder.PNG",25);
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
        TabPane statisticsPane = new TabPane();
        statisticsPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        statisticsPane.setTabMinHeight(200.0);
        statisticsPane.setTabMinWidth(215);
        statisticsPane.getTabs().addAll(
                dailyTab,
                monthlyTab,
                yearlyTab
        );
        rootPane.setCenter(statisticsPane);
    }

    /**
     * Transitions user back to home view.
     * @throws IOException
     */
    @FXML
    protected void onBackButtonClick() throws IOException {
        components.changeView(backButton,"home-view.fxml");
    }

    /**
     * Allows user to select a specific game to view the statistics of.
     * @throws IOException
     */
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

    /**
     * When user selects the daily tab, display statistics for that day.
     * @throws IOException
     */
    @FXML
    protected void onDailyTabSelection() throws IOException {
        List<GameTime> dailyGameTimeList = GetGameTimesThisDay();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        StringBuilder dailyStats = new StringBuilder();
        dailyStats.append("Game Name, Total Play Time, Date and Time");
        dailyStats.append("\n");
        for (GameTime gameTime : dailyGameTimeList) {
            dailyStats.append(_gameRepo.GetGame(gameTime.getGameId()).getGameName());
            dailyStats.append(", ");
            dailyStats.append(gameTime.getTotalPlaytime());
            dailyStats.append(", ");
            dailyStats.append(gameTime.getCreatedDateTime().format(formatter));
            dailyStats.append("\n");
        }

        dailyStatLabel.setText(dailyStats.toString());
    }

    /**
     * When user selects the monthly tab, display statistics for that month.
     * @throws IOException
     */
    @FXML
    protected void onMonthlyTabSelection() throws IOException {
        List<GameTime> monthGameTimeList = GetGameTimesThisMonth();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        StringBuilder monthlyStats = new StringBuilder();
        monthlyStats.append("Game Name, Total Play Time, Date and Time");
        monthlyStats.append("\n");
        for (GameTime gameTime : monthGameTimeList) {
            monthlyStats.append(_gameRepo.GetGame(gameTime.getGameId()).getGameName());
            monthlyStats.append(", ");
            monthlyStats.append(gameTime.getTotalPlaytime());
            monthlyStats.append(", ");
            monthlyStats.append(gameTime.getCreatedDateTime().format(formatter));
            monthlyStats.append("\n");
        }

        monthlyStatLabel.setText(monthlyStats.toString());
    }

    /**
     * When user selects the yearly tab, display statistics for that year.
     * @throws IOException
     */
    @FXML
    protected void onYearlyTabSelection() throws IOException {
        List<GameTime> yearGameTimeList = GetGameTimesThisYear();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        StringBuilder yearlyStats = new StringBuilder();
        yearlyStats.append("Game Name, Total Play Time, Date and Time");
        yearlyStats.append("\n");
        for (GameTime gameTime : yearGameTimeList) {
            yearlyStats.append(_gameRepo.GetGame(gameTime.getGameId()).getGameName());
            yearlyStats.append(", ");
            yearlyStats.append(gameTime.getTotalPlaytime());
            yearlyStats.append(", ");
            yearlyStats.append(gameTime.getCreatedDateTime().format(formatter));
            yearlyStats.append("\n");
        }

        yearlyStatLabel.setText(yearlyStats.toString());
    }

    private List<GameTime> GetGameTimesThisDay()
    {
        List<GameTime> sortedGameTimeList = new ArrayList<>();

        List<GameTime> gameTimeList = _gameTimeRepo.GetGameTimeListByUser(LoginController.user.getUserId());
        if (!gameTimeList.isEmpty()) {
            for (GameTime gameTime : gameTimeList) {
                if (gameTime.getCreatedDateTime().isBefore(LocalDateTime.now()) &&
                        gameTime.getCreatedDateTime().isAfter(LocalDateTime.now().withHour(0).withMinute(0))) {
                    sortedGameTimeList.add(gameTime);
                }
            }
        }

        return sortedGameTimeList;
    }

    private List<GameTime> GetGameTimesThisMonth()
    {
        List<GameTime> sortedGameTimeList = new ArrayList<>();

        List<GameTime> gameTimeList = _gameTimeRepo.GetGameTimeListByUser(LoginController.user.getUserId());
        if (!gameTimeList.isEmpty()) {
            for (GameTime gameTime : gameTimeList) {
                if (gameTime.getCreatedDateTime().isBefore(LocalDateTime.now()) &&
                        gameTime.getCreatedDateTime().isAfter(LocalDateTime.now().withDayOfMonth(1))) {
                    sortedGameTimeList.add(gameTime);
                }
            }
        }

        return sortedGameTimeList;
    }

    private List<GameTime> GetGameTimesThisYear()
    {
        List<GameTime> sortedGameTimeList = new ArrayList<>();

        List<GameTime> gameTimeList = _gameTimeRepo.GetGameTimeListByUser(LoginController.user.getUserId());
        if (!gameTimeList.isEmpty()) {
            for (GameTime gameTime : gameTimeList) {
                if (gameTime.getCreatedDateTime().isBefore(LocalDateTime.now()) &&
                        gameTime.getCreatedDateTime().isAfter(LocalDateTime.now().withMonth(1).withDayOfMonth(1))) {
                    sortedGameTimeList.add(gameTime);
                }
            }
        }

        return sortedGameTimeList;
    }
}
