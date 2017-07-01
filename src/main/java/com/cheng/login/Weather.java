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
    //    static String[] city = {"����", "���", "�Ϻ�", "����", "ʯ��ׯ", "̫ԭ", "����", "����", "������", "�Ͼ�", "����", "�Ϸ�", "����", "�ϲ�", "����", "֣��", "�人", "��ɳ", "����", "����", "�ɶ�", "����", "����", "����", "����", "����", "����", "����", "���ͺ���", "����", "��³ľ��", "���", "̨��", "����"};  //��������
    static String[] city = {"�Ϻ�", "̩��"};
    static int[] day = {0, 1, 2, 3, 4};   //��һ�������
    static String weather;  //�����������
    static String high;  //���浱������¶�
    static String low;  //���浱������¶�
    static URL ur;
    static int count = 0;
    static {
        try {

        } catch (Exception e) {
        }
    }

    public static List<String> getweather()   //��ȡ��������
    {

        List<String> weathers = new ArrayList<String>();
        try {
            DocumentBuilderFactory domfac = DocumentBuilderFactory.newInstance();  //���������ʹ�ã�����ȥ������������ͬ��������ϸ����
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
                        if (node.getNodeName().equals("status1")) weather = node.getTextContent();  //��ȡ���������
                        else if (node.getNodeName().equals("temperature1")) high = node.getTextContent();  //��ȡ������¶�
                        else if (node.getNodeName().equals("temperature2")) low = node.getTextContent();   //��ȡ������¶�
                    }
                }
                result = cityName + " " + weather + " " + low + "��~" + high + "��";
                weathers.add(result);
            }
            return weathers;

        } catch (Exception e) {
            System.out.println("��ȡ����ʧ��:" + e);
        }
        return null;
    }

    public static void main(String[] arg) {
        new Weather().getweather();  //���ӿں�������ִ�з���
    }

} 