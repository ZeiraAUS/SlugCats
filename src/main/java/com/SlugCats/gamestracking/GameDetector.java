package com.SlugCats.gamestracking;

import javax.swing.*;
import java.io.File;

public class GameDetector {
    private String gameName;

    /**
     * Opens a file chooser dialog to allow the user to select a file.
     *
     * @return The selected File object, or null if no file is selected.
     */
    public File choosefile() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        }
        return null;
    }

    /**
     * Detects whether the selected file is a valid game executable.
     *
     * @param file The File object to check.
     * @return A message indicating whether the file is a game executable or not.
     */
    public String detectGame(File file) {
        if (file == null || !file.exists()) {
            return "No game detected";
        }

        if (file.isFile() && file.getName().endsWith(".exe")) {
            gameName = file.getName();
            return "Executable Game File: " + gameName;
        }

        return "No game detected";
    }

    /**
     * Returns the name of the detected game executable file.
     *
     * @return The name of the game executable, or null if no game has been detected.
     */
    public String getGameName() {
        return gameName;
    }
}
