package testSpring;

import com.spring.gnericExtend.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/beans-gneric.xml")
public class TestGnerricExtends {
    

    @Resource
    private UserService userService;

    @Test
    public void testDemo() {

        userService.add();

    }
}
