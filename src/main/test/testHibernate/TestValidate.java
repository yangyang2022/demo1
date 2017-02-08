package testHibernate;

import com.hibernate.model.Car;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.metadata.BeanDescriptor;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Set;

public class TestValidate {

    private Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

        validator = factory.getValidator();
    }
    @Test
    public void testDemo() {
        Car car = new Car("Audi", "HELLO", 3, LocalDate.of(2015,1,1),true);

        car.addPart("hello");
        car.addPart("world");

        car.load(Arrays.asList("hello","world"),Arrays.asList("ni hao"));

        Set<ConstraintViolation<Car>> violations = validator.validate(car);
        System.out.println(car.getParts());

        System.out.println("size: "+violations.size());
        violations.forEach(e-> System.out.println(e.getMessage()));
    }

    @Test
    public void testBeanDescriptor() {

        BeanDescriptor beanDescriptor = validator.getConstraintsForClass(Car.class);

    }

    @Test
    public void testDemo1() {

        System.out.println("hello world");

    }
}
