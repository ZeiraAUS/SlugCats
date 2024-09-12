package Test;
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
             assertFalse(Authentication.register("testUser", "email@example.com", "password123", "password13"));
    }

    @Test
    void testRegisterSuccess() throws SQLException {
        assertTrue(Authentication.register("newUser", "newemail@example.com", "password123", "password123"));
    }//test

    @Test
    void testLoginSuccess() throws SQLException {
         assertTrue(Authentication.login("newUser", "password123"));
    }

    @Test
    void testLoginFailure() throws SQLException {//user not exist
        assertFalse(Authentication.login("testUser", "password123"));
    }

}
