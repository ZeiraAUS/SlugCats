import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import com.SlugCats.timetracking.playtimetracker;
import java.time.Duration;
import java.time.LocalDateTime;
import java.io.ByteArrayInputStream;

public class PlayTimeTrackerTest {
    private playtimetracker tracker;

    @BeforeEach
    public void setUp() {
        tracker = new playtimetracker("idea64.exe");
    }

    //Test
//    @Test
//    public void testTrackPlayTime() {
//        tracker.trackPlayTime();
//
//        assertNotNull(tracker);
//    }

    @Test
    public void testGetProcessStartTime() {
        String startTime = playtimetracker.getProcessStartTime("idea64.exe");

        assertNotNull(startTime);
        assertFalse(startTime.isEmpty());
    }

    @Test
    public void testParseDateTime() {
        String wmicDateTime = "20240101123000";
        LocalDateTime parsedDateTime = playtimetracker.parseDateTime(wmicDateTime);

        assertNotNull(parsedDateTime);
        assertEquals(2024, parsedDateTime.getYear());
        assertEquals(1, parsedDateTime.getMonthValue());
        assertEquals(1, parsedDateTime.getDayOfMonth());
        assertEquals(12, parsedDateTime.getHour());
        assertEquals(30, parsedDateTime.getMinute());
    }
}