/**
 * Created by cheng on 2017/6/8.
 */
public class ExceptionTest {
    public static void main(String[] args) throws InterruptedException {

//        String string = test();
//        System.out.println(string);

        for (int i = 0; i < 100; i++) {
            Thread.sleep(1000);
            if (i == 50) {
                try {
                    throw new Exception();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.println(" ----- "  + i + " ----- ");

        }

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


