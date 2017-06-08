package com.cheng.login;

/**
 * Created by login on 2017/6/6.
 *
 * 登录状态枚举类
 */
public enum LoginStatus {

    SUCCESS("登录成功", 1), FAILED("登录失败", 2), ALREADY_LOGINED("用户已在线", 3);
    // 成员变量
    private String name;
    private int index;

    // 构造方法
    private LoginStatus(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (LoginStatus c : LoginStatus.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }

    // get set 方法
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
