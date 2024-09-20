import com.SlugCats.DAOs.UserDAO;
import com.SlugCats.DatabaseConnection;
import com.SlugCats.Models.User;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Order(1)
public class UsersTest {
    UserDAO userDAO = new UserDAO();

    @BeforeAll
    public static void RebuildTable()
    {
        DatabaseConnection.getInstance();
        DatabaseConnection.DropTables();
        DatabaseConnection.CreateTables();
    }

    @Test
    @Order(1)
    public void CreateAndGetUserTest()
    {
        User test = new User(1, "JohnSmith", "Password",
                "John", "Smith", "Who@email.com");

        userDAO.AddUser(test);

        User result = userDAO.GetUser(1);

        assertEquals(test.toString(), result.toString());
    }

    @Test
    @Order(2)
    public void GetUserTableTest()
    {
        List<User> userList = new ArrayList<>();

        User test1 = new User(1, "JohnSmith", "Password",
                "John", "Smith", "Who@email.com");

        User test2 = new User(2, "JaneSmith", "Password",
                "Jane", "Smith", "Who@email.com");

        userList.add(test1);
        userList.add(test2);

        userDAO.AddUser(test2);

        List<User> resultList = userDAO.GetUserList();

        assertEquals(userList.toString(), resultList.toString());
    }

    @Test
    @Order(3)
    public void GetUserByUsernamePassword()
    {
        User test = new User(1, "JohnSmith", "Password",
                "John", "Smith", "Who@email.com");

        User result = userDAO.GetUserByUsernamePassword("JohnSmith", "Password");

        assertEquals(test.toString(), result.toString());
    }

    @Test
    @Order(4)
    public void UpdateUserTest()
    {
        User test = userDAO.GetUser(1);
        test.setUserName("SmithJohn");

        userDAO.UpdateUser(test);
        User result = userDAO.GetUser(1);

        assertEquals("SmithJohn", result.getUserName());
    }

    @Test
    @Order(5)
    public void DeleteUserTest()
    {
        User test = new User(3, "DeleteMe", "Password",
                "Delete", "Me", "No");

        userDAO.AddUser(test);

        userDAO.DeleteUser(3);
        User result = userDAO.GetUser(3);

        assertNull(result);
    }
}