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
        countDown.setTime(10,5,50);
        int[] time = countDown.GetTime();
        assertEquals(time[0],10);
        assertEquals(time[1],5);
        assertEquals(time[2],50);
    }

}
