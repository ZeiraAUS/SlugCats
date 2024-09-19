import com.SlugCats.DAOs.GameDAO;
import com.SlugCats.DAOs.SpeedrunTimeDAO;
import com.SlugCats.DAOs.UserDAO;
import com.SlugCats.DatabaseConnection;
import com.SlugCats.Models.Game;
import com.SlugCats.Models.GameTime;
import com.SlugCats.Models.SpeedrunTime;
import com.SlugCats.Models.User;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Order(4)
public class SpeedrunTimesTest {
    UserDAO userDAO = new UserDAO();
    GameDAO gameDAO = new GameDAO();
    SpeedrunTimeDAO speedrunTimeDAO = new SpeedrunTimeDAO();

    @Test
    @Order(1)
    public void CreateAndGetSpeedrunTimeTest()
    {
        SpeedrunTime test = new SpeedrunTime(1, 1, 1, 100, 100);

        speedrunTimeDAO.AddSpeedrunTime(test);

        SpeedrunTime result = speedrunTimeDAO.GetSpeedrunTime(1);

        assertEquals(test.toString(), result.toString());
    }

    @Test
    @Order(2)
    public void GetSpeedrunTimeTableTest()
    {
        List<SpeedrunTime> speedrunTimeList = new ArrayList<>();

        SpeedrunTime test1 = new SpeedrunTime(1, 1, 1, 100, 100);

        SpeedrunTime test2 = new SpeedrunTime(2, 2, 2, 500, 500);

        speedrunTimeList.add(test1);
        speedrunTimeList.add(test2);

        speedrunTimeDAO.AddSpeedrunTime(test2);

        List<SpeedrunTime> resultList = speedrunTimeDAO.GetSpeedrunTimeList();

        assertEquals(speedrunTimeList.toString(), resultList.toString());
    }

    @Test
    @Order(3)
    public void GetSpeedrunTimeListByUserTest()
    {
        SpeedrunTime speedrunTime = new SpeedrunTime(1, 1, 1, 100, 100);
        List<SpeedrunTime> test = new ArrayList<>();
        test.add(speedrunTime);

        List<SpeedrunTime> result = speedrunTimeDAO.GetSpeedrunTimeListByUser(1);

        assertEquals(test.toString(), result.toString());
    }

    @Test
    @Order(4)
    public void GetSpeedrunTimeListByGameTest()
    {
        SpeedrunTime speedrunTime = new SpeedrunTime(2, 2, 2, 500, 500);
        List<SpeedrunTime> test = new ArrayList<>();
        test.add(speedrunTime);

        List<SpeedrunTime> result = speedrunTimeDAO.GetSpeedrunTimeListByGame(2);

        assertEquals(test.toString(), result.toString());
    }

    @Test
    @Order(4)
    public void UpdateGameTimeTest()
    {
        SpeedrunTime test = speedrunTimeDAO.GetSpeedrunTime(1);
        test.setLastRunTime(111);

        speedrunTimeDAO.UpdateSpeedrunTime(test);
        SpeedrunTime result = speedrunTimeDAO.GetSpeedrunTime(1);

        assertEquals(111, result.getLastRunTime());
    }

    @Test
    @Order(5)
    public void DeleteUserTest()
    {
        speedrunTimeDAO.DeleteSpeedrunTime(1);
        SpeedrunTime result = speedrunTimeDAO.GetSpeedrunTime(1);

        assertNull(result);
    }
}