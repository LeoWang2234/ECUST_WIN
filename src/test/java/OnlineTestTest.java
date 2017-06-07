import com.cheng.cheng.OnlineTest;
import org.junit.Test;

/**
 * Created by cheng on 2017/6/7.
 */
public class OnlineTestTest
{

    OnlineTest onlineTest = new OnlineTest();

    @Test
    public void test() {

        while (true) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            onlineTest.sendGet("http://www.163.com");

            System.out.println(OnlineTest.connected);

        }

    }


}
