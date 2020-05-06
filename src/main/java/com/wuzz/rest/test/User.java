package com.wuzz.rest.test;

import com.wuzz.rest.annotation.ApiDoc;

import java.util.Map;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/10/22
 * Time: 15:49
 * Description 描述:
 */
public class User {

    @ApiDoc(value = "姓名",mustInput = false)
    private String name;

    @ApiDoc("年龄")
    private int age;

    @ApiDoc("性别")
    private int sex;

    @ApiDoc("账户信息")
    private Account account;

    @ApiDoc("map信息")
    private Map<String,String> mapParam;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", account=" + account +
                '}';
    }
}
