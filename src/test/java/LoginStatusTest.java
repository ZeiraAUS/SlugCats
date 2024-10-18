import static org.junit.jupiter.api.Assertions.*;

import com.SlugCats.NewAuth.login_status;
import com.SlugCats.NewAuth.register;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.SlugCats.Models.User;

import java.sql.Connection;
import java.sql.SQLException;

public class LoginStatusTest {
    private com.SlugCats.NewAuth.register register;
    private login_status loginStatus;
    private User testUser;

    @BeforeEach
    public void setUp() {
        loginStatus = new login_status();
        this.register= new register();
        // Assuming User class has a constructor that takes username and password

    }
    @Test
    public void testRegisterUserExists() throws SQLException {
        // two password not equal
        assertFalse(register.register_a_user("testUser",  "password123", "password13","a","a","asa"));
    }

    @Test

    public void testRegisterSuccess() throws SQLException {
        assertTrue(register.register_a_user("NewUser",  "password123", "password123","a","asa","asa"));

    }

    @Test
    public void testLoginSuccess() {
        // Simulate a successful login directly
        boolean result = loginStatus.is_login("NewUser", "password123");

        // Assert the login status and username
        assertTrue(result, "Login should be successful");
        assertEquals("NewUser", loginStatus.gotUsername(), "Username should match");
        assertTrue(loginStatus.gotLogin_status(), "Login status should be true");
    }


    @Test
    public void testLoginFailure() {
        boolean result = loginStatus.is_login("invalidUser", "wrongPassword");
        assertFalse(result, "Login should fail for invalid credentials");
        assertNull(loginStatus.getUser(), "User should be null after failed login");
        assertFalse(loginStatus.gotLogin_status(), "Login status should be false after failed login");
    }

}


