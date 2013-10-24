/*
 * 文件名: Address.java
 * 版    权：   
 * 描    述: [该类的简要描述]
 * 创建人: w00138133
 * 创建时间:2011-7-19
 * 
 * 修改人：
 * 修改时间:
 * 修改内容：[修改内容]
 */
package com.sleightdemos.bean;

/**
 * [一句话功能简述]<BR>
 * [功能详细描述]
 * 
 * @author w00138133
 * @version [v1.0, 2011-7-19]
 */
public class Address
{
    private String addType;
    private String place;

    public Address()
    {

    }

    public Address(String addType, String place)
    {
        this.addType = addType;
        this.place = place;
    }

    public String getAddType()
    {
        return addType;
    }

    public void setAddType(String addType)
    {
        this.addType = addType;
    }

    public String getPlace()
    {
        return place;
    }

    public void setPlace(String place)
    {
        this.place = place;
    }

    public String toString()
    {
        return "Address{" + "addType='" + addType + '\'' + ", place='" + place
            + '\'' + "}\n";
    }
}
