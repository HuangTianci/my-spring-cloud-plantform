package reference;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.function.Supplier;

/**
 * @author Huang Tianci
 * @date 2017/11/22
 */
public class Car {

    public static Car create(Supplier<Car> supplier) {
        return supplier.get();
    }

    public static void collide(final Car car) {
        System.out.println("collide: " + car.toString());
    }

    public void follow(final Car car) {
        System.out.println("Follow the: " + car.toString());
    }

    public void repair() {
        System.out.println("Repair: " + this.toString());
    }

}
