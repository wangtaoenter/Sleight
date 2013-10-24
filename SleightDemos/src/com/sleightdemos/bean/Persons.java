package com.sleightdemos.bean;

import java.util.Iterator;
import java.util.List;

/**
 * [一句话功能简述]<BR>
 * [功能详细描述]
 * 
 * @author wangtaoenter
 * @version [v1.0, 2011-7-19]
 */
public class Persons
{
    private String type;
    private List<Person> listPerson;

    public Persons()
    {

    }

    public Persons(List<Person> listPerson, String type)
    {
        this.listPerson = listPerson;
        this.type = type;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public List<Person> getListPerson()
    {
        return listPerson;
    }

    public void setListPerson(List<Person> listPerson)
    {
        this.listPerson = listPerson;
    }

    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        for (Iterator it = listPerson.iterator(); it.hasNext();)
        {
            Person p = (Person) it.next();
            sb.append(p.toString());
        }
        return "Persons{" + "type='" + type + '\'' + ", listPerson=" + sb.toString() + "}\n";
    }
}
