package com.SlugCats.controller;

import com.SlugCats.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.SlugCats.DAOs.*;
import com.SlugCats.Models.User;

import java.io.IOException;

public class SettingsController {
    // Components of the Settings window.
    @FXML
    private BorderPane rootPane;
    @FXML
    private ImageView logoImage;
    @FXML
    private Button backButton;
    @FXML
    private Label settingsLabel;
    @FXML
    private Button saveButton;
    @FXML
    private Label volumeLabel;
    @FXML
    private Label soundLabel;
    @FXML
    private Label visibilityLabel;
    @FXML
    private Label startLabel;
    @FXML
    private Label pauseLabel;
    @FXML
    private Label resetLabel;
    @FXML
    private Label themeLabel;
    @FXML
    private TextField volumeField;
    @FXML
    private Button soundButton;
    @FXML
    private TextField visibilityField;
    @FXML
    private Button startSetButton;
    @FXML
    private Button pauseSetButton;
    @FXML
    private Button resetSetButton;
    @FXML
    private ComboBox themeCBox;

    @FXML
    public void initialize() {
        // Initialise and set the logo image into the image view.
        Image logo = new Image(getClass().getResource("/images/snailcat.PNG").toString(),true);
        logoImage.setImage(logo);
        HBox headerBox = new HBox(20);
        headerBox.getChildren().addAll(
                logoImage,
                backButton,
                settingsLabel
        );
        rootPane.setTop(headerBox);

        // Set graphic to custom alert sound button.
        Image folder = new Image(getClass().getResource("/images/folder.PNG").toString(),true);Image selectGame = new Image(getClass().getResource("/images/folder.PNG").toString(),true);
        ImageView selectView = new ImageView(folder);
        selectView.setFitHeight(25);
        selectView.setPreserveRatio(true);
        soundButton.setGraphic(selectView);
        VBox labelBox = new VBox(21);
        labelBox.getChildren().addAll(
                volumeLabel,
                soundLabel,
                visibilityLabel,
                startLabel,
                pauseLabel,
                resetLabel,
                themeLabel,
                saveButton
        );
        rootPane.setLeft(labelBox);

        themeCBox.getItems().addAll(
                "Light",
                "Dark",
                "Deuteranopia",
                "Protanopia",
                "Tritanopia"
        );
        VBox interactiveBox  = new VBox(15);
        interactiveBox.getChildren().addAll(
                volumeField,
                soundButton,
                visibilityField,
                startSetButton,
                pauseSetButton,
                resetSetButton,
                themeCBox
        );
        rootPane.setRight(interactiveBox);
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
    protected void onSoundButtonClick() throws IOException {
        //Detect user input for new reset key bind.
    }

    @FXML
    protected void onStartSetButtonClick() throws IOException {
        //Detect user input for new start key bind.
    }

    @FXML
    protected void onPauseSetButtonClick() throws IOException {
        //Detect user input for new pause key bind.
    }

    @FXML
    protected void onResetSetButtonClick() throws IOException {
        //Detect user input for new reset key bind.
    }

    @FXML
    protected void onSaveButtonClick() throws IOException {
        //Checks all the fields, etc. and puts them into effect.
    }
}
