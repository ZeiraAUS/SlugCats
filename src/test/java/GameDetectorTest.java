import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;

import com.SlugCats.gamestracking.GameDetector;

public class GameDetectorTest {
    GameDetector gameDetector;

    @BeforeEach
    public void Initialise() {
        gameDetector = new GameDetector();
    }

    @Test
    public void TestDetectExeFile() {
        File mockExeFile = new File("chrome.exe") {
            @Override
            public boolean exists() {
                return true;
            }

            @Override
            public boolean isFile() {
                return true;
            }

            @Override
            public String getName() {
                return "chrome.exe";
            }
        };

        String result = gameDetector.detectGame(mockExeFile);

        assertEquals("Executable Game File: chrome.exe", result);
        assertEquals("chrome.exe", gameDetector.getGameName());
    }

    @Test
    public void TestDetectNonExeFile() {
        File txtFile = new File("document.txt");

        String result = gameDetector.detectGame(txtFile);

        assertEquals("No game detected", result);

        assertNull(gameDetector.getGameName());
    }

    @Test
    public void TestNoFileSelected() {
        String result = gameDetector.detectGame(null);

        assertEquals("No game detected", result);

        assertNull(gameDetector.getGameName());
    }
}
