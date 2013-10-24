package com.sleightdemos.bean;

/**
 * [一句话功能简述]<BR>
 * [功能详细描述]
 * 
 * @author wangtaoenter
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
        return "Address{" + "addType='" + addType + '\'' + ", place='" + place + '\'' + "}\n";
    }
}
