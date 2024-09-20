import com.SlugCats.DAOs.GameDAO;
import com.SlugCats.DAOs.GameTimeDAO;
import com.SlugCats.DAOs.UserDAO;
import com.SlugCats.DatabaseConnection;
import com.SlugCats.Models.Game;
import com.SlugCats.Models.GameTime;
import com.SlugCats.Models.User;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Order(3)
public class GameTimesTest {
    UserDAO userDAO = new UserDAO();
    GameDAO gameDAO = new GameDAO();
    GameTimeDAO gameTimeDAO = new GameTimeDAO();

    @Test
    @Order(1)
    public void CreateAndGetGameTimeTest()
    {
        GameTime test = new GameTime(1, 1, 1, 100, 100);

        gameTimeDAO.AddGameTime(test);

        GameTime result = gameTimeDAO.GetGameTime(1);

        assertEquals(test.toString(), result.toString());
    }

    @Test
    @Order(2)
    public void GetGameTimeTableTest()
    {
        List<GameTime> gameTimeList = new ArrayList<>();

        GameTime test1 = new GameTime(1, 1, 1, 100, 100);

        GameTime test2 = new GameTime(2, 2, 2, 500, 500);

        gameTimeList.add(test1);
        gameTimeList.add(test2);

        gameTimeDAO.AddGameTime(test2);

        List<GameTime> resultList = gameTimeDAO.GetGameTimeList();

        assertEquals(gameTimeList.toString(), resultList.toString());
    }

    @Test
    @Order(3)
    public void GetGameTimeListByUserTest()
    {
        GameTime gameTime = new GameTime(1, 1, 1, 100, 100);
        List<GameTime> test = new ArrayList<>();
        test.add(gameTime);

        List<GameTime> result = gameTimeDAO.GetGameTimeListByUser(1);

        assertEquals(test.toString(), result.toString());
    }

    @Test
    @Order(4)
    public void UpdateGameTimeTest()
    {
        GameTime test = gameTimeDAO.GetGameTime(1);
        test.setLastSessionPlaytime(111);

        gameTimeDAO.UpdateGameTime(test);
        GameTime result = gameTimeDAO.GetGameTime(1);

        assertEquals(111, result.getLastSessionPlaytime());
    }

    @Test
    @Order(5)
    public void DeleteUserTest()
    {
        gameTimeDAO.DeleteGameTime(1);
        GameTime result = gameTimeDAO.GetGameTime(1);

        assertNull(result);
    }
}