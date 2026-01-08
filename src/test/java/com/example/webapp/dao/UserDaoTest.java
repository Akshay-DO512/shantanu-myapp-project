
package com.example.webapp.dao;

import com.example.webapp.model.User;
import org.junit.jupiter.api.*;
import java.sql.*;
import java.util.Properties;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class UserDaoTest {

    static Properties props;

    @BeforeAll
    static void setup() throws Exception {
        props = new Properties();
        props.load(UserDaoTest.class.getClassLoader()
                .getResourceAsStream("db-test.properties"));

        try (Connection con = UserDao.getConnection(props);
             Statement st = con.createStatement()) {
            st.execute("CREATE TABLE users(id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(100), email VARCHAR(100))");
        }
    }

    @Test
    void testSaveUser() {
        User user = new User("JUnit", "test@test.com");
        assertDoesNotThrow(() -> UserDao.save(user, props));
    }
}
