package vip.wush.cloud.user.model;

import java.io.Serializable;

/**
 * @ClassName: user
 * @Description: TODO
 * @Author: wush
 * @Date: 2020/4/24 18:36
 */

public class User implements Serializable {

    private static final long serialVersionUID = 7845905594048990867L;

    private String name;

    private String phone;

    private int age;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", age=" + age +
                '}';
    }
}
