package com.cheng.login;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;


public class HeFengWeather {
    static String[] city = {"XuHui", "DongPing"};
    static int[] day = {0, 1, 2, 3, 4};   //哪一天的天气
    static String weather;  //保存天气情况
    static String tmp;  // 保存实时温度
    static URL ur;
    static int count = 0;

    // 得到当时的天气
    public static List<String> getweather()   //获取天气函数
    {

        List<String> weathers = new ArrayList<String>();
        try {
            String result = "";
            String type = "hourly";
            for (String cityName : city) {
                boolean isAfterTenOclock = new Date().getHours() >= 22;
                if (isAfterTenOclock) {
                    type = "now";
                }
                ur = new URL("https://free-api.heweather.com/v5/"+type+"?city=" + cityName + "&key=ba02047e23334f31ba5e0c329357c2d5");
                HttpURLConnection connection = (HttpURLConnection) ur.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();

                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream(), "UTF-8"));
                String readLine = null;
                StringBuffer response = new StringBuffer();
                while (null != (readLine = bufferedReader.readLine())) {
                    response.append(readLine);
                }

                bufferedReader.close();
                String responseBody = response.toString();
                cityName = cityName.equals("XuHui") ? "徐汇" : "东平";
                if (isAfterTenOclock) {
                    weathers = praseJsonNow(responseBody, weathers, cityName);
                }else {
                    weathers = praseJsonHourly(responseBody, weathers, cityName);
                }
            }
            System.out.println(weathers);
            return weathers;

        } catch (Exception e) {
            System.out.println("获取天气失败:" + e);
        }
        return weathers;
    }

    // 解析每小时的预报
    public static List<String> praseJsonHourly(String str, List<String> weathers, String cityName) {
        JSONObject obj = new JSONObject(str.toString());
        JSONArray arr = (JSONArray) obj.get("HeWeather5");

        System.out.println(str);
        JSONObject obj2 = new JSONObject(arr.get(0).toString());
        JSONArray arr2 = (JSONArray) obj2.get("hourly_forecast");
        String weather1 = "";
        StringBuffer result = new StringBuffer().append(cityName).append(" ");

        // 如果接口返回多于2条数据，则只取两条
        for (int i = 0; i < (2 > arr2.length() ? 1 : 2); i++) {
            JSONObject temp = new JSONObject(arr2.get(i).toString());

            JSONObject cond = new JSONObject(temp.get("cond").toString());
            // 拿到天气,天气若是有两个，则添加两次
            if (i == 0) {
                weather1 = (String) cond.get("txt");
                result.append(cond.get("txt"));
                // 数据只有一条，只能在这儿拿温度
                if (arr2.length() == 1) {
                    result.append(" ").append(temp.get("tmp")).append("℃");
                }
            } else {
                String weather2 = (String) cond.get("txt");
                if (!weather1.equals(weather2)) {
                    result.append("转").append(weather2);
                }
                // 返回的数据多于一条，在这儿取到温度
                result.append(" ").append(temp.get("tmp")).append("℃");
            }
        }
        weathers.add(result.toString());
        System.out.println(weathers.toString());
        return weathers;
    }


    // 解析 now 的实时天气
    public static List<String> praseJsonNow(String str, List<String> weathers, String cityName) {
        JSONObject obj = new JSONObject(str.toString());
        JSONArray arr = (JSONArray) obj.get("HeWeather5");

//        System.out.println(arr);
        JSONObject obj2 = new JSONObject(arr.get(0).toString());
        JSONObject obj3 = new JSONObject(obj2.get("now").toString());
        String weather1 = "";
        String temperature = "";
        weather1 = new JSONObject(obj3.get("cond").toString()).getString("txt");
        temperature = obj3.getString("tmp");

        StringBuffer result = new StringBuffer().append(cityName).append(" ").append(weather1).append(" ")
                .append(temperature).append("℃");

        weathers.add(result.toString());
//        System.out.println(weathers.toString());
        return weathers;
    }

    public static void main(String[] arg) {

        String strNow = "{\"HeWeather5\":[{\"basic\":{\"city\":\"上海\",\"cnty\":\"中国\",\"id\":\"CN101020100\",\"lat\":\"31.23170662\",\"lon\":\"121.47264099\",\"update\":{\"loc\":\"2017-07-02 00:49\",\"utc\":\"2017-07-01 16:49\"}},\"now\":{\"cond\":{\"code\":\"104\",\"txt\":\"阴\"},\"fl\":\"29\",\"hum\":\"85\",\"pcpn\":\"0\",\"pres\":\"1005\",\"tmp\":\"25\",\"vis\":\"10\",\"wind\":{\"deg\":\"130\",\"dir\":\"北风\",\"sc\":\"微风\",\"spd\":\"7\"}},\"status\":\"ok\"}]}";

//        String str = "{\"HeWeather5\":[{\"basic\":{\"city\":\"徐汇\",\"cnty\":\"中国\",\"id\":\"CN101021200\",\"lat\":\"31.17997360\",\"lon\":\"121.43752289\",\"update\":{\"loc\":\"2017-07-01 23:49\",\"utc\":\"2017-07-01 15:49\"}},\"hourly_forecast\":[{\"cond\":{\"code\":\"305\",\"txt\":\"小雨\"},\"date\":\"2017-07-02 01:00\",\"hum\":\"84\",\"pop\":\"28\",\"pres\":\"1005\",\"tmp\":\"26\",\"wind\":{\"deg\":\"164\",\"dir\":\"东南风\",\"sc\":\"3-4\",\"spd\":\"22\"}},{\"cond\":{\"code\":\"101\",\"txt\":\"多云\"},\"date\":\"2017-07-02 04:00\",\"hum\":\"89\",\"pop\":\"24\",\"pres\":\"1006\",\"tmp\":\"25\",\"wind\":{\"deg\":\"159\",\"dir\":\"东南风\",\"sc\":\"微风\",\"spd\":\"14\"}},{\"cond\":{\"code\":\"300\",\"txt\":\"阵雨\"},\"date\":\"2017-07-02 07:00\",\"hum\":\"85\",\"pop\":\"39\",\"pres\":\"1008\",\"tmp\":\"26\",\"wind\":{\"deg\":\"175\",\"dir\":\"南风\",\"sc\":\"微风\",\"spd\":\"14\"}},{\"cond\":{\"code\":\"309\",\"txt\":\"毛毛雨/细雨\"},\"date\":\"2017-07-02 10:00\",\"hum\":\"76\",\"pop\":\"63\",\"pres\":\"1007\",\"tmp\":\"28\",\"wind\":{\"deg\":\"185\",\"dir\":\"南风\",\"sc\":\"微风\",\"spd\":\"16\"}},{\"cond\":{\"code\":\"309\",\"txt\":\"毛毛雨/细雨\"},\"date\":\"2017-07-02 13:00\",\"hum\":\"68\",\"pop\":\"24\",\"pres\":\"1006\",\"tmp\":\"30\",\"wind\":{\"deg\":\"165\",\"dir\":\"东南风\",\"sc\":\"微风\",\"spd\":\"16\"}},{\"cond\":{\"code\":\"103\",\"txt\":\"晴间多云\"},\"date\":\"2017-07-02 16:00\",\"hum\":\"70\",\"pop\":\"25\",\"pres\":\"1006\",\"tmp\":\"30\",\"wind\":{\"deg\":\"162\",\"dir\":\"东南风\",\"sc\":\"3-4\",\"spd\":\"17\"}},{\"cond\":{\"code\":\"300\",\"txt\":\"阵雨\"},\"date\":\"2017-07-02 19:00\",\"hum\":\"82\",\"pop\":\"58\",\"pres\":\"1007\",\"tmp\":\"27\",\"wind\":{\"deg\":\"162\",\"dir\":\"东南风\",\"sc\":\"3-4\",\"spd\":\"17\"}},{\"cond\":{\"code\":\"300\",\"txt\":\"阵雨\"},\"date\":\"2017-07-02 22:00\",\"hum\":\"87\",\"pop\":\"68\",\"pres\":\"1008\",\"tmp\":\"26\",\"wind\":{\"deg\":\"166\",\"dir\":\"东南风\",\"sc\":\"3-4\",\"spd\":\"19\"}}],\"status\":\"ok\"}]}";
//        String str = "{\"HeWeather5\":[{\"basic\":{\"city\":\"徐汇\",\"cnty\":\"中国\",\"id\":\"CN101021200\",\"lat\":\"31.17997360\",\"lon\":\"121.43752289\",\"update\":{\"loc\":\"2017-07-01 23:49\",\"utc\":\"2017-07-01 15:49\"}},\"hourly_forecast\":[{\"cond\":{\"code\":\"305\",\"txt\":\"小雨\"},\"date\":\"2017-07-02 01:00\",\"hum\":\"84\",\"pop\":\"28\",\"pres\":\"1005\",\"tmp\":\"26\",\"wind\":{\"deg\":\"164\",\"dir\":\"东南风\",\"sc\":\"3-4\",\"spd\":\"22\"}}],\"status\":\"ok\"}]}";
//        String strKong = "{\"HeWeather5\":[{\"basic\":{\"city\":\"徐汇\",\"cnty\":\"中国\",\"id\":\"CN101021200\",\"lat\":\"31.17997360\",\"lon\":\"121.43752289\",\"update\":{\"loc\":\"2017-07-01 23:49\",\"utc\":\"2017-07-01 15:49\"}},\"hourly_forecast\":[],\"status\":\"ok\"}]}";
//        praseJsonNow(strNow, new ArrayList<String>(), "东平");
//        System.out.println(getweather());

        getweather();

    }


}