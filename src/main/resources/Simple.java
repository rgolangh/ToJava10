import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A simple java class with 2 local variables declaration in the constructor.
 * A test should use those for conversion to 'var' and  analyze the result.
 */
public class Simple {

    private int x = 42;

    public Simple() {
        // foo comment
        int age = 1;
        HashMap<String, String> map = new ConcurrentHashMap<>();
    }

    public static void main(String[] args) {
        System.out.println(new Simple());
    }
}