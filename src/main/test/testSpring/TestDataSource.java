package testSpring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/beans-test.xml")
public class TestDataSource {

    @Resource
    private ApplicationContext applicationContext;

    @Resource
    private DataSource dataSource;

    @Test
    public void testConnection() throws SQLException {

        System.out.println(dataSource.getConnection());

    }

}
