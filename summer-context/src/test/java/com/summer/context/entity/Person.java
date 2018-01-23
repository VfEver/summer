package com.summer.context.entity;

/**
 * @author zys
 * @date 2018/01/20
 */
public class Person {

    private String name;
    private int age;
    private Dad dad;

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

    public Dad getDad() {
        return dad;
    }

    public void setDad(Dad dad) {
        this.dad = dad;
    }

    @Override
    public String toString() {

        return "name - " + this.name + "\n"
                + "age - " + this.age + "\n"
                + "dad - " + this.dad.toString();
    }
}
