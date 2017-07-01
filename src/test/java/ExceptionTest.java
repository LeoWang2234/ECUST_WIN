/**
 * Created by cheng on 2017/6/8.
 */
public class ExceptionTest {
    public static void main(String[] args) {

        String string = test();
        System.out.println(string);

    }

    public static String test() {
        try {
            throw new Exception();
        } catch (Exception e) {
            System.out.println("exception");
//            e.printStackTrace();
        }
        return "test";
    }
}


