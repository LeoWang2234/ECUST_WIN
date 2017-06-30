import com.cheng.login.OnlineStatus;
import org.junit.Test;

/**
 * Created by login on 2017/6/7.
 */
public class OnlineTestTest
{

    OnlineStatus onlineTest = new OnlineStatus();

    @Test
    public void test() {

        while (true) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            onlineTest.sendGet();

            System.out.println(OnlineStatus.connected);

        }

    }


}
