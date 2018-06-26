/**
 * @author Huang Tianci
 * @date 2017/11/22
 */
public interface Deaulable {

    default String notRequired() {
        return "Default implementation";
    }
}
