import org.junit.Test;

import java.io.IOException;
import java.net.*;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    @Test
    public void netIsAvailable() {
        try {
            final URL url = new URL("http://www.baidu.com");
            final URLConnection conn = url.openConnection();
            conn.connect();
            System.out.println("connected");
        } catch (Exception e) {
            System.out.println("not connected");
        }
    }

    @Test
    public void testInet() {
        Socket sock = new Socket();
        InetSocketAddress addr = new InetSocketAddress("www.baidu.com", 80);
        try {
            sock.connect(addr, 3000);
            System.out.println("connected");
        } catch (IOException e) {
            System.out.println("not connected");
        } finally {
            try {
                sock.close();
            } catch (IOException e) {
            }
        }
    }

    @Test
    public void test2() throws UnknownHostException, IOException {
        try {
            try {
                URL url = new URL("http://www.baidu.com");
                System.out.println(url.getHost());
                HttpURLConnection con = (HttpURLConnection) url
                        .openConnection();
                con.connect();
                if (con.getResponseCode() == 200) {
                    System.out.println("Connection established!!");
                }
            } catch (Exception exception) {
                System.out.println("No Connection");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3() {
        String txt = "[234]";
        System.out.println(txt);

        String re1 = "(\\[.*?\\])";    // Square Braces 1
        String re2 = "(4)";    // Any Single Digit 1

        Pattern p = Pattern.compile(re1 + re2, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        Matcher m = p.matcher(txt);
        if (m.find()) {
            String sbraces1 = m.group(1);
            String d1 = m.group(2);
            System.out.print("(" + sbraces1.toString() + ")" + "(" + d1.toString() + ")" + "\n");
        }
    }
}