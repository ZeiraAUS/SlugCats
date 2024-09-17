import static org.junit.jupiter.api.Assertions.*;

import auth.Authentication;

import java.sql.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class AuthTest {
    private Connection connection;
    private Authentication Authentication;

    @BeforeEach
    void setUp() throws SQLException {
        Authentication = new Authentication();
    }

    @Test
    void testRegisterUserExists() throws SQLException {
      // two password not equal
             assertFalse(Authentication.register("testUser",  "password123", "password13","a","a","asa"));
    }

    @Test
    void testRegisterSuccess() throws SQLException {
        assertTrue(Authentication.register("NewUser",  "password123", "password123","a","asa","asa"));
    }//test ,it will failed 2th because the username already exist.

    @Test
    void testLoginSuccess() throws SQLException {
         assertTrue(Authentication.login("NewUser", "password123"));
    }

    @Test
    void testLoginFailure() throws SQLException {//user not exist
        assertFalse(Authentication.login("testUser", "password123"));
    }

}
