import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import NewAuth.*;
import Models.User;

class LoginStatusTest {

    private login_status loginStatus;
    private User testUser;

    @BeforeEach
    void setUp() {
        loginStatus = new login_status();
        // Assuming User class has a constructor that takes username and password

    }


    @Test
    void testLoginSuccess() {
        // Simulate a successful login directly
        boolean result = loginStatus.is_login("NewUser", "password123");

        // Assert the login status and username
        assertTrue(result, "Login should be successful");
        assertEquals("NewUser", loginStatus.gotUsername(), "Username should match");
        assertTrue(loginStatus.gotLogin_status(), "Login status should be true");
    }


    @Test
    void testLoginFailure() {
        boolean result = loginStatus.is_login("invalidUser", "wrongPassword");
        assertFalse(result, "Login should fail for invalid credentials");
        assertNull(loginStatus.getUser(), "User should be null after failed login");
        assertFalse(loginStatus.gotLogin_status(), "Login status should be false after failed login");
    }

    @Test
    void testLogout() {
        // Simulate a successful login
        loginStatus.is_login("testUser", "password");

        // Perform logout
        loginStatus.logout();

        assertNull(loginStatus.getUser(), "User should be null after logout");
        assertFalse(loginStatus.gotLogin_status(), "Login status should be false after logout");
        assertNull(loginStatus.gotUsername(), "Username should be null after logout");
    }
}
