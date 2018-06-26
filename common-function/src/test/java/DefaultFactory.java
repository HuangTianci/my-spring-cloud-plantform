import java.util.function.Supplier;

/**
 * @author Huang Tianci
 * @date 2017/11/22
 */
public interface DefaultFactory {

    static Deaulable create(Supplier<Deaulable> supplier) {
        return supplier.get();
    }
}
