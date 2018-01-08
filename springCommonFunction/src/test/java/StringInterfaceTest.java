import java.util.function.Supplier;

/**
 * @author Huang Tianci
 * @date 2017/11/22
 */
public interface StringInterfaceTest {

    static String create(Supplier<String> age) {
        return age.get();
    }
}
