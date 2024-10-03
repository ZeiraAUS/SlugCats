package com.SlugCats.gamestracking;

import javax.swing.*;
import java.io.File;

public class GameDectector {
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
        if (file != null) {
            String fileName = file.getName();
            if (fileName.endsWith(".exe")) {
                gameName = file.getName();
            }
        }

        return "No game detected";
    }

    public String getGameName() {
        return gameName;
    }
}
