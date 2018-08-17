package com.zjh.db;

/**
 * Created by ZhangJinghao on 2018/8/16.
 */
public class PlayerBean {
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String AGE = "age";
    public static final String ISMALE = "isMale";


    int id;
    String name;
    int age;
    boolean isMale;

    public PlayerBean(int id, String name, int age, boolean isMale) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.isMale = isMale;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setMale(boolean male) {
        isMale = male;
    }
}
