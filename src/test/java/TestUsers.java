import com.SlugCats.DAOs.UserDAO;
import com.SlugCats.DatabaseConnection;
import com.SlugCats.Models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TestUsers {
    UserDAO userDAO = new UserDAO();

    @BeforeAll
    public static void Startup()
    {
        Connection connection = DatabaseConnection.getInstance();
    }

    @BeforeEach
    public void RebuildTable()
    {
        DatabaseConnection.DropTables();
        DatabaseConnection.CreateTables();
    }

    @Test
    public void CreateAndGetUserTest()
    {
        User test = new User(1, "JohnSmith", "Password",
                "John", "Smith", "Who@email.com");

        userDAO.AddUser(test);

        User result = userDAO.GetUser(1);

        assertEquals(test.toString(), result.toString());
    }

    @Test
    public void GetUserTableTest()
    {
        List<User> userList = new ArrayList<>();

        User test1 = new User(1, "JohnSmith", "Password",
                "John", "Smith", "Who@email.com");

        User test2 = new User(2, "JaneSmith", "Password",
                "Jane", "Smith", "Who@email.com");

        userList.add(test1);
        userList.add(test2);

        userDAO.AddUser(test1);
        userDAO.AddUser(test2);

        List<User> resultList = userDAO.GetUserList();

        assertEquals(userList.toString(), resultList.toString());
    }

    @Test
    public void GetUserByUsernamePassword()
    {
        User test = new User(1, "JohnSmith", "Password",
                "John", "Smith", "Who@email.com");

        userDAO.AddUser(test);
        User result = userDAO.GetUserByUsernamePassword("JohnSmith", "Password");

        assertEquals(test.toString(), result.toString());
    }

    @Test
    public void UpdateUserTest()
    {
        User test = new User(1, "JohnSmith", "Password",
                "John", "Smith", "Who@email.com");

        userDAO.AddUser(test);

        test.setUserName("SmithJohn");

        userDAO.UpdateUser(test);
        User result = userDAO.GetUser(1);

        assertEquals("SmithJohn", result.getUserName());
    }

    @Test
    public void DeleteUserTest()
    {
        User test = new User(1, "JohnSmith", "Password",
                "John", "Smith", "Who@email.com");

        userDAO.AddUser(test);
        userDAO.DeleteUser(1);
        User result = userDAO.GetUser(1);

        assertNull(result);
    }
}
