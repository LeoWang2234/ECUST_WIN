import com.cheng.helper.JsoupHelper;
import org.junit.Ignore;

/**
 * Created by login on 2017/6/6.
 */
public class JsoupHelperTest {

    @Ignore
    public void praseTest() {
        String url = "http://172.20.3.90:803/srun_portal_pc.php?ac_id=7&";

        JsoupHelper helper = new JsoupHelper();
        helper.prase(url);

    }


}
