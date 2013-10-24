/*
 * 文件名: Person.java
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
public class Person
{
    private String name;
    private String sex;
    private String tel;
    private Addresses addes;

    public Person()
    {

    }

    public Person(Addresses addes, String name, String sex, String tel)
    {
        this.addes = addes;
        this.name = name;
        this.sex = sex;
        this.tel = tel;
    }

    public Addresses getAddes()
    {
        return addes;
    }

    public void setAddes(Addresses addes)
    {
        this.addes = addes;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getSex()
    {
        return sex;
    }

    public void setSex(String sex)
    {
        this.sex = sex;
    }

    public String getTel()
    {
        return tel;
    }

    public void setTel(String tel)
    {
        this.tel = tel;
    }

    public String toString()
    {
        return "Person{" + "addes=" + addes.toString() + ", name='" + name
            + '\'' + ", sex='" + sex + '\'' + ", tel='" + tel + '\'' + "}\n";
    }
}
