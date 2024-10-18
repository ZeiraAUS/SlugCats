package com.SlugCats.controller;

import com.SlugCats.Main;
import com.SlugCats.NewAuth.LoginStatus;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The Components class handles regularly user controller code.
 */
public class Components {
    public Components() {

    }

    /**
     * Sets the logo's image.
     * @param logoImage The image view where the logo will be set.
     */
    protected void setLogoImage(ImageView logoImage) {
        Image logo = new Image(getClass().getResource("/images/snailcat.PNG").toString(),true);
        logoImage.setImage(logo);
    }

    /**
     * Sets an image for a button.
     * @param button Button for the graphic to go.
     * @param url Link for the image.
     * @param size Height of image (while maintaining scale).
     */
    protected void setButtonImage(Button button, String url, int size){
        Image image = new Image(getClass().getResource(url).toString(),true);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(size);
        imageView.setPreserveRatio(true);
        button.setGraphic(imageView);
    }

    /**
     * Change view through button action click.
     * @param button Button that changes the view.
     * @param view View user is changed to.
     * @throws IOException Exception thrown when fxml view is unable to load.
     */
    protected void changeView(Button button, String view) throws IOException {
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(view));
        Scene scene = new Scene(fxmlLoader.load(), Main.WIDTH, Main.HEIGHT);
        stage.setResizable(false);
        LoginStatus.logout();
        stage.setScene(scene);
    }
}
