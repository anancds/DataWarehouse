package com.hikvision.mdp.web.bean;

/**
 * Created by geting on 2017/2/8.
 */
public class MdpCategoryObject {

    private String name;

    public MdpCategoryObject(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MdpCategoryObject{" +
                "name='" + name + '\'' +
                '}';
    }
}
