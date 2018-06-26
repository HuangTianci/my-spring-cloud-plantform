package reference;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author Huang Tianci
 * @date 2017/11/22
 */
public class TestCar {

    @Test
    public void test() {
        final Car car = Car.create( Car::new );
        final List< Car > cars = Arrays.asList( car );
        cars.forEach( Car::collide );
        cars.forEach( Car::repair );

        final Car police = Car.create( Car::new );
        cars.forEach( police::follow );
    }

}
