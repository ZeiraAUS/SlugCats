package com.SlugCats.controller;

import com.SlugCats.Main;
import com.SlugCats.Models.GameTime;
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
import java.time.LocalDateTime;
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
    private Label statisticsLabel, dailyStatLabel, weeklyStatLabel, monthlyStatLabel, yearlyStatLabel, gameLabel, gameStatLabel;
    @FXML
    private Tab dailyTab, monthlyTab, yearlyTab, gameTab;

    private String displayName;

    // Class for configuring controller components.
    private Components components = new Components();

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
        gameTab.setContent(gameTabBox);
        TabPane statisticsPane = new TabPane();
        statisticsPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        statisticsPane.setTabMinHeight(200.0);
        statisticsPane.setTabMinWidth(215);
        statisticsPane.getTabs().addAll(
                dailyTab,
                monthlyTab,
                yearlyTab,
                gameTab
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
        String dailyStats = "[Insert Daily Stats Here]";

        dailyStatLabel.setText(dailyStats.toString());
    }

    /**
     * When user selects the monthly tab, display statistics for that month.
     * @throws IOException
     */
    @FXML
    protected void onMonthlyTabSelection() throws IOException {
        String monthlyStats = "[Insert Monthly Stats Here]";

        //Logic to get and set monthly stats here etc.

        monthlyStatLabel.setText(monthlyStats);
    }

    /**
     * When user selects the yearly tab, display statistics for that year.
     * @throws IOException
     */
    @FXML
    protected void onYearlyTabSelection() throws IOException {
        String yearlyStats = "[Insert Yearly Stats Here]";

        //Logic to get and set yearly stats here etc.

        yearlyStatLabel.setText(yearlyStats);
    }

    /**
     * When user selects the game tab, display statistics for the game tab once they have also selected a game to view.
     * @throws IOException
     */
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

    private List<GameTime> GetGameTimesThisMonth()
    {
        GameTimeDAO _repo = new GameTimeDAO();
        List<GameTime> sortedGameTimeList = new ArrayList<>();

        List<GameTime> gameTimeList = _repo.GetGameTimeListByUser(LoginController.user.getUserId());
        if (!gameTimeList.isEmpty()) {
            for (int i = 0; i < gameTimeList.size(); i++) {
                GameTime gameTime = gameTimeList.get(i);

                if (gameTime.getCreatedDateTime().isBefore(LocalDateTime.now()) &&
                        gameTime.getCreatedDateTime().isAfter(LocalDateTime.now().withDayOfMonth(1))) {
                    sortedGameTimeList.add(gameTime);
                }
            }
        }

        return sortedGameTimeList;
    }
}
