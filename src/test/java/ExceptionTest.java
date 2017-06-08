/**
 * Created by cheng on 2017/6/8.
 */
public class ExceptionTest {
    public static void main(String[] args) {
        while (true) {
            try {
                int a = 1;
                throw new Exception();

            } catch (Exception e) {

            } finally {
                try {
                    throw new Exception();
                } catch (Exception e) {
                    System.out.println("¼ÌÐøÖ´ÐÐ");
                }
            }
        }
    }
}


