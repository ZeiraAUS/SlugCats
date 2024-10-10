import com.SlugCats.DAOs.GameDAO;
import com.SlugCats.DatabaseConnection;
import com.SlugCats.Models.Game;
import com.SlugCats.Models.User;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Order(2)
public class GamesTest {
    GameDAO gameDAO = new GameDAO();

    @Test
    @Order(1)
    public void CreateAndGetGameTest()
    {
        Game test = new Game(1, "Rain World", "1234");

        gameDAO.AddGame(test);

        Game result = gameDAO.GetGame(1);

        assertEquals(test.toString(), result.toString());
    }

    @Test
    @Order(2)
    public void GetGameTableTest()
    {
        List<Game> gameList = new ArrayList<>();

        Game test1 = new Game(1, "Rain World", "1234");

        Game test2 = new Game(2, "World of Warcraft", "5678");

        gameList.add(test1);
        gameList.add(test2);

        gameDAO.AddGame(test2);

        List<Game> resultList = gameDAO.GetGameList();

        assertEquals(gameList.toString(), resultList.toString());
    }

    @Test
    @Order(3)
    public void UpdateGameTest()
    {
        Game test = gameDAO.GetGame(1);
        test.setGameName("Hollow Knight");

        gameDAO.UpdateGame(test);
        Game result = gameDAO.GetGame(1);

        assertEquals("Hollow Knight", result.getGameName());
    }

    @Test
    @Order(4)
    public void DeleteUserTest()
    {
        Game test = new Game(3, "Concord", "1234");

        gameDAO.AddGame(test);

        gameDAO.DeleteGame(3);
        Game result = gameDAO.GetGame(3);

        assertNull(result);
    }
}