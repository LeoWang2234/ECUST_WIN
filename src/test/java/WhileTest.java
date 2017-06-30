import org.junit.Test;

import java.util.Date;

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


    @Test
    public void test() {
        Date date = new Date();
        date.getTime();
        System.out.println(date);
        System.out.println(date.getHours());
        System.out.println(date.getMinutes());
        System.out.println(date.getSeconds());
    }
}
