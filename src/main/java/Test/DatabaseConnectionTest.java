package Test;


import java.sql.Connection;

import auth.*;
import org.junit.jupiter.api.BeforeEach;
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