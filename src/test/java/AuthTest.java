import static org.junit.jupiter.api.Assertions.*;
import com.SlugCats.NewAuth.*;
import java.sql.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class AuthTest {
    private Connection connection;
    private register register;
    @BeforeEach

    public void setUp() throws SQLException {
        this.register= new register();    }


    @Test
    public void testRegisterUserExists() throws SQLException {
      // two password not equal
             assertFalse(register.register_a_user("testUser",  "password123", "password13","a","a","asa"));
    }

    @Test

    public void testRegisterSuccess() throws SQLException {
        assertTrue(register.register_a_user("NewUser",  "password123", "password123","a","asa","asa"));

    }//test ,it will failed 2th because the username already exist.


}
