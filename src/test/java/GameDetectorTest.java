import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;

import com.SlugCats.gamestracking.GameDetector;

public class GameDetectorTest {
    GameDetector gameDetector;

    @BeforeEach
    void Initialise() {
        gameDetector = new GameDetector();
    }

    @Test
    void TestDetectExeFile() {
        //Before testing this out, please create JUnit configuration with chrome.exe path in environment variables
        String chromePath = System.getenv("CHROME_EXE_PATH");
        assertNotNull(chromePath, "CHROME_EXE_PATH environment variable is not set");

        File exeFile = new File(chromePath);
        String result = gameDetector.detectGame(exeFile);

        assertEquals("Executable Game File: chrome.exe", result);
        assertEquals("chrome.exe", gameDetector.getGameName());
    }

    @Test
    void TestDetectNonExeFile() {
        File txtFile = new File("document.txt");

        String result = gameDetector.detectGame(txtFile);

        assertEquals("No game detected", result);

        assertNull(gameDetector.getGameName());
    }

    @Test
    void TestNoFileSelected() {
        String result = gameDetector.detectGame(null);

        assertEquals("No game detected", result);

        assertNull(gameDetector.getGameName());
    }
}
