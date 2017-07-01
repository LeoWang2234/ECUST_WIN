package com.cheng.login;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Weather {
    //    static String[] city = {"北京", "天津", "上海", "重庆", "石家庄", "太原", "沈阳", "长春", "哈尔滨", "南京", "杭州", "合肥", "福州", "南昌", "济南", "郑州", "武汉", "长沙", "广州", "海口", "成都", "贵阳", "昆明", "西安", "兰州", "西宁", "拉萨", "南宁", "呼和浩特", "银川", "乌鲁木齐", "香港", "台北", "澳门"};  //各个城市
    static String[] city = {"上海", "泰安"};
    static int[] day = {0, 1, 2, 3, 4};   //哪一天的天气
    static String weather;  //保存天气情况
    static String high;  //保存当天最高温度
    static String low;  //保存当天最低温度
    static URL ur;
    static int count = 0;
    static {
        try {

        } catch (Exception e) {
        }
    }

    public static List<String> getweather()   //获取天气函数
    {

        List<String> weathers = new ArrayList<String>();
        try {
            DocumentBuilderFactory domfac = DocumentBuilderFactory.newInstance();  //相关这个类的使用，可以去网上搜索，下同，不做详细介绍
            DocumentBuilder dombuilder = domfac.newDocumentBuilder();
            Document doc;
            Element root;
            NodeList books;
            String result = "";
            for (String cityName : city) {
                ur = new URL("http://php.weather.sina.com.cn/xml.php?city=" + cityName + "&password=DJOYnieT8234jlsK&day=" + 0);
                doc = dombuilder.parse(ur.openStream());
                root = doc.getDocumentElement();
                books = root.getChildNodes();
                for (Node node = books.item(1).getFirstChild(); node != null; node = node.getNextSibling()) {
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        if (node.getNodeName().equals("status1")) weather = node.getTextContent();  //获取到天气情况
                        else if (node.getNodeName().equals("temperature1")) high = node.getTextContent();  //获取到最高温度
                        else if (node.getNodeName().equals("temperature2")) low = node.getTextContent();   //获取到最低温度
                    }
                }
                result = cityName + " " + weather + " " + low + "℃~" + high + "℃";
                weathers.add(result);
            }
            return weathers;

        } catch (Exception e) {
            System.out.println("获取天气失败:" + e);
        }
        return null;
    }

    public static void main(String[] arg) {
        new Weather().getweather();  //主接口函数调用执行方法
    }

} 