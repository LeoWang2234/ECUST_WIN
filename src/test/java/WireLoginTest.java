import com.cheng.cheng.JsoupHelper;
import com.cheng.cheng.LoginWithWire;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Map;

/**
 * Created by cheng on 2017/6/6.
 */
public class WireLoginTest {

    @Ignore
    public void test() {
        JsoupHelper helper = new JsoupHelper();
        String url = "http://172.20.3.90:803/srun_portal_pc.php?ac_id=7&";

        Map<String, String> attrs = helper.prase(url);

        LoginWithWire loginWithWire = new LoginWithWire();
        try {
            loginWithWire.sendPostWire(attrs);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
