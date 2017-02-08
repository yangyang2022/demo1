package testSpring;


import com.spring.model.HelloWorld;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/beans-test.xml")
public class TestHelloWorld {

    @Resource
    private ApplicationContext applicationContext;

    private <T>T getBean(Class<T> clz){return applicationContext.getBean(clz);}
    @Test
    public void testDemo() {

        HelloWorld helloWorld = getBean(HelloWorld.class);
        HelloWorld helloWorld2 = getBean(HelloWorld.class);

        System.out.println(helloWorld == helloWorld2);

    }
}
