package testSpring;

import com.spring.aop.Employee;
import com.yangyang.util.NameUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/beans-jdbc.xml")
public class TestJdbcTemplate {

    @Resource
    private DataSource dataSource;

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Resource
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private String table_employee = "t_employee";

    @Test
    public void testConnection() throws SQLException {

        System.out.println(dataSource.getConnection());

        System.out.println(jdbcTemplate);

    }

    @Test
    public void testUpdate() {
        String sql = "UPDATE t_employee SET NAME = ? WHERE id= ? ";
        jdbcTemplate.update(sql,"adminUpdate",3);
    }

    @Test
    public void testBatchUpdate() {
        //batch insert/update/delete

        Random random = new Random();
        List<String> names = NameUtil.getNames(10000);
        List<Object[]> objects = names.stream()
                .map(e -> new Object[]{e, e + "@qq.com", (random.nextFloat() * 1000), (1 + random.nextInt(8))})
                .collect(Collectors.toList());

        String sql = "insert into t_employee(name,email,salary,dept_id) values(?,?,?,?)";
        jdbcTemplate.batchUpdate(sql,objects);
    }


    //获取整个对象
    @Test
    public void testQueryForObject() {
        String sql = "select id,name,email,salary,dept_id from t_employee where id =?";

        //不是使用这个方法,而是使用BeanPropertyRowMapper
        //Employee employee = jdbcTemplate.queryForObject(sql, Employee.class, 10504);

        RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<>(Employee.class);

        Employee employee = jdbcTemplate.queryForObject(sql, rowMapper,3);
        System.out.println(employee.getName()+" : "+employee.getDepartment());

    }

    //获取单个列的查询,做统计查询
    @Test
    public void testObjectForObject() {

        String sql = "select count(id) from "+table_employee;
        Long coount = jdbcTemplate.queryForObject(sql, Long.class);
        System.out.println(coount);

    }

    @Test
    public void testNameJdbc() {

        String sql = "insert into t_employee(NAME,EMAIL,SALARY,dept_id) values(:name,:email,:salary,:dept_id)";

        Map<String,Object> params = new HashMap<>();
        params.put("name","testName");
        params.put("email","test@qq.com");
        params.put("salary","123123.4");
        params.put("dept_id","3");
        namedParameterJdbcTemplate.update(sql,params);
    }

    @Test
    public void testNameJdbc2() {
        
    }

}
