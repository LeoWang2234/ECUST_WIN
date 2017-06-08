import com.cheng.login.LoginWireLess;
import org.junit.Ignore;

/**
 * Created by login on 2017/6/6.
 */
public class WireLessLoginTest {

    @Ignore
    public void test() throws Exception {
        LoginWireLess loginWireLess = new LoginWireLess();

        String url = "http://login.ecust.edu.cn/&arubalp=07ab5286-6c88-4ee7-997d-1563e8afb4";

        String result = loginWireLess.sendGet(url);

//        System.out.println(result);
        String[] params = loginWireLess.getMacAndIP(result);

//        System.out.println(params[0]);
//        System.out.println(params[1]);

        // 我觉得可以这样，先走无线，因为不管是连没脸线，走无线都能比较快地给到反馈
        if (params[0].equals("")) {
            System.out.println("kong");
        }
        System.out.println();

        try {
            loginWireLess.sendPostWireless(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
