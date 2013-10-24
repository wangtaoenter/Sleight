/*
 * 文件名: Addresses.java
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

import java.util.Iterator;
import java.util.List;

/**
 * [一句话功能简述]<BR>
 * [功能详细描述]
 * 
 * @author w00138133
 * @version [v1.0, 2011-7-19]
 */
public class Addresses
{
    private List<Address> listAdd;

    public Addresses()
    {

    }

    public Addresses(List<Address> listAdd)
    {
        this.listAdd = listAdd;
    }

    public List<Address> getListAdd()
    {
        return listAdd;
    }

    public void setListAdd(List<Address> listAdd)
    {
        this.listAdd = listAdd;
    }

    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        for (Iterator it = listAdd.iterator(); it.hasNext();)
        {
            Address add = (Address) it.next();
            sb.append(add.toString());
        }
        return "Addresses{" + "listAdd=" + sb.toString() + "}\n";
    }
}
