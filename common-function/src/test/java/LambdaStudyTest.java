import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author Huang Tianci
 * @date 2017/11/22
 */
public class LambdaStudyTest {

    @Test
    public void test() {
        List<String> list = Arrays.asList("a","b","c");
        System.out.println(list.size());
    }

    @Test
    public void test1() {
        Arrays.asList("a", "b","c").forEach(e -> System.out.println(e));
    }

    @Test
    public void test2() {
        final String a = "a";
        Arrays.asList(1.0, 2.0, 3.0).forEach((Double e) -> {
            System.out.println(e + a);
        });
    }

    @Test
    public void test3() {
        List<Integer> list = Arrays.asList(2, 1, 3);
        list.forEach(e -> System.out.printf(e.toString()));
        System.out.println();
        list.sort((e1,e2) -> {
            System.out.println("e1:" + e1);
            System.out.println("e2:" + e2);
            System.out.println("--------");
            //return e1.compareTo(e2);
            return e2.compareTo(e1);
        });
        System.out.println();
        list.forEach(e -> System.out.printf(e.toString()));
    }

    @Test
    public void testInterface() {
        Deaulable deaulable = DefaultFactory.create(DefaulableImpl::new);
        System.out.println(deaulable.notRequired());

        System.out.println(StringInterfaceTest.create(String::new));

    }
}
