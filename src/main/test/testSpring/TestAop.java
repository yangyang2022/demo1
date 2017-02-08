package testSpring;

import com.spring.aop.CalculatorProxy;
import com.spring.aop.ICalculator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/beans-aop.xml")
public class TestAop {

    @Resource
    private ICalculator calculator;


    @Test
    public void testDemo() {

        System.out.println("add: "+calculator.add(1, 2));
        System.out.println("minus: "+calculator.minus(1, 2));
        System.out.println("multi: "+calculator.multi(1, 2));
        System.out.println("divide: "+calculator.divide(1, 10));
    }

    @Test
    public void testAround() {

        System.out.println(calculator.add(1, 2));

    }

    @Test
    public void testProxy() {

        ICalculator calculator = new CalculatorProxy(this.calculator).getCalculator();
        System.out.println(calculator.add(1, 2));


    }
}
