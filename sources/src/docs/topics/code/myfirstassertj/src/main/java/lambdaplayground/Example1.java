package lambdaplayground;
import java.util.function.IntUnaryOperator;
public class Example1 {
    public static void main(String[] args) {
        int number = ((IntUnaryOperator) (int x) -> x + 1).applyAsInt(1);
        System.out.println("number = " + number);
    }
}
