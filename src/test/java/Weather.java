import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.net.URL;

public class Weather {
    static String[] city={"����","���","�Ϻ�","����","ʯ��ׯ","̫ԭ","����","����","������","�Ͼ�","����","�Ϸ�","����","�ϲ�","����","֣��","�人","��ɳ","����","����","�ɶ�","����","����","����","����","����","����","����","���ͺ���","����","��³ľ��","���","̨��","����"};  //��������
    static int[] day={0,1,2,3,4};   //��һ�������
    static String weather;  //�����������
    static String high;  //���浱������¶�
    static String low;  //���浱������¶�
    public void getweather()   //��ȡ��������
    {
        URL ur;

        try {

            DocumentBuilderFactory domfac=DocumentBuilderFactory.newInstance();  //���������ʹ�ã�����ȥ������������ͬ��������ϸ����
            DocumentBuilder dombuilder=domfac.newDocumentBuilder();
            Document doc;
            Element root;
            NodeList books;
            for (String str : city) {     //ѭ�����ʻ�ȡ����������ͬ�������
                ur = new URL("http://php.weather.sina.com.cn/xml.php?city="+str+"&password=DJOYnieT8234jlsK&day="+day);
                doc=dombuilder.parse(ur.openStream());
                root=doc.getDocumentElement();
                books=root.getChildNodes();
                for(Node node = books.item(1).getFirstChild(); node!=null; node=node.getNextSibling()){
                    if(node.getNodeType()==Node.ELEMENT_NODE){
                        if(node.getNodeName().equals("status1"))weather=node.getTextContent();  //��ȡ���������
                        else if(node.getNodeName().equals("temperature1"))high=node.getTextContent();  //��ȡ������¶�
                        else if(node.getNodeName().equals("temperature2"))low=node.getTextContent();   //��ȡ������¶�
                    }
                }
                System.out.println(str+" "+weather+" "+low+"��~"+high+"��");  //ǰ̨���
            }

        }catch(Exception e){System.out.println("��ȡ����ʧ��:"+e);}
    }
    public static void main(String [] arg)
    {
        new Weather().getweather();  //���ӿں�������ִ�з���
    }

} 
