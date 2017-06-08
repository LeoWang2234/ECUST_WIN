package com.cheng.login;

/**
 * Created by login on 2017/6/6.
 *
 * ��¼״̬ö����
 */
public enum LoginStatus {

    SUCCESS("��¼�ɹ�", 1), FAILED("��¼ʧ��", 2), ALREADY_LOGINED("�û�������", 3);
    // ��Ա����
    private String name;
    private int index;

    // ���췽��
    private LoginStatus(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // ��ͨ����
    public static String getName(int index) {
        for (LoginStatus c : LoginStatus.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }

    // get set ����
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
