package com.SlugCats.gamestracking;

import javax.swing.*;
import java.io.File;

public class GameDetector {
    private String gameName;

    public File choosefile() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        }
        return null;
    }

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

    public String getGameName() {
        return gameName;
    }
}
