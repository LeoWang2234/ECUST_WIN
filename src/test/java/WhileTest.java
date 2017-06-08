/**
 * Created by login on 2017/6/7.
 */
public class WhileTest {
    public static void main(String[] args) {
        int count = 1;
        while (true) {

            if (count < 1000) {
                count++;
                continue;
            }
            System.out.println(count);
            break;

        }
    }
}
