import java.sql.Connection;

import com.SlugCats.auth.*;
import com.SlugCats.auth.DatabaseConnection;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class DatabaseConnectionTest {

    @Test
    public   void testGetInstanceNotNull() {

        Connection connection = DatabaseConnection.getInstance();
        assertNotNull(connection, "databace not null");
    }


    @Test
    public void testSingletonPattern() {

        Connection firstInstance = DatabaseConnection.getInstance();
        Connection secondInstance = DatabaseConnection.getInstance();
        assertSame(firstInstance, secondInstance, "two connections should be the same");
    }
}