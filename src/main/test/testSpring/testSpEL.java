package testSpring;

import com.spring.model.Car;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/beans-test.xml")
public class testSpEL {

    @Resource
    private ApplicationContext applicationContext;

    private <T>T getBean(Class<T> clz){return applicationContext.getBean(clz);}

    @Test
    public void testSpel1() {

        //Address address = getBean(Address.class);
        //System.out.println(address);

        //Car car = getBean(Car.class);
        //System.out.println(car);

        //Person person = getBean(Person.class);
        //System.out.println(person);

        //Car car = applicationContext.getBean("carFactory", Car.class);
        //System.out.println(car);

        Car car2 = applicationContext.getBean("car2", Car.class);
        System.out.println(car2);


    }

}
