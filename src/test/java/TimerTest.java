import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import com.SlugCats.timetracking.CountDown;

public class TimerTest
{
    CountDown countDown;

    @BeforeEach
    void Initialise()
    {
        countDown = new CountDown();
    }

    @Test
    void TestSetNumbers()
    {
        countDown.setTime(23,120,65);
        int[] time = countDown.getTime();
        // hours
        assertEquals(time[0],24);
        // mins
        assertEquals(time[1],60);
        // seconds
        assertEquals(time[2],60);
    }
    @Test
    void TestResetNumbers()
    {
        countDown.setTime(2,30,25);
        countDown.resetTime();
        int[] time = countDown.getTime();
        // hours
        assertEquals(time[0],2);
        // mins
        assertEquals(time[1],30);
        // seconds
        assertEquals(time[2],25);
    }

}
