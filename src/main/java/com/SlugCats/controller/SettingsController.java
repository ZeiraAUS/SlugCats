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

/**
 * Handles the settings view logic.
 */
public class SettingsController {
    // Components of the Settings window.
    @FXML
    private BorderPane rootPane;
    @FXML
    private ImageView logoImage;
    @FXML
    private Button backButton, saveButton, soundButton;
    @FXML
    private Label settingsLabel, volumeLabel, soundLabel;
    @FXML
    private TextField volumeField;

    // Class for configuring controller components.
    private Components components = new Components();

    /**
     * Initialize Settings window components.
     */
    @FXML
    public void initialize() {
        // Initialise and set the logo image into the image view.
        components.setLogoImage(logoImage);
        HBox headerBox = new HBox(20);
        headerBox.getChildren().addAll(
                logoImage,
                backButton,
                settingsLabel
        );
        rootPane.setTop(headerBox);

        components.setButtonImage(soundButton,"/images/folder.PNG",25);
        VBox labelBox = new VBox(21);
        labelBox.getChildren().addAll(
                volumeLabel,
                soundLabel,
                saveButton
        );
        rootPane.setLeft(labelBox);

        VBox interactiveBox  = new VBox(15);
        interactiveBox.getChildren().addAll(
                volumeField,
                soundButton
        );
        rootPane.setRight(interactiveBox);
    }

    /**
     * Transitions user back to the home view.
     * @throws IOException
     */
    @FXML
    protected void onBackButtonClick() throws IOException {
        components.changeView(backButton,"home-view.fxml");
    }

    /**
     * Opens folder to select a new custom mp3/4 sound for the alert.
     * @throws IOException
     */
    @FXML
    protected void onSoundButtonClick() throws IOException {
        //Get new alert sound.
    }


    /**
     * Saves changes to setting configurations.
     * @throws IOException
     */
    @FXML
    protected void onSaveButtonClick() throws IOException {
        //Checks all the fields, etc. and puts them into effect.
    }
}
