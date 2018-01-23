package com.summer.context.entity;

/**
 * entity to test
 * @author zys
 * @date 2018年1月23日22:42:19
 */
public class Dad {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void say () {
        System.out.println("hello " + name);
    }

    @Override
    public String toString() {
        return "dad name - " + this.name;
    }
}
