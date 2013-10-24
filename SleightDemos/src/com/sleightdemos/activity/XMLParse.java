package com.sleightdemos.activity;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;

import com.sleightdemos.util.XmlParse;

public class XMLParse extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        String xmlStr = "<?xml version='1.0' encoding='UTF-8'?><Root><customer><id>1</id><name>ddd</name></customer>"
            + "<order><orderId>2</orderId><orderName>nnmnmnmn</orderName></order></Root>";

        //        XMLParserEngine xmlParserEngine = new XMLParserEngine();
        //        xmlParserEngine.parse(xmlStr, new Object());

        XmlParse<Root> xmlParse = new XmlParse<Root>(new Root());
        xmlParse.parse(xmlStr);
        List<Root> list = xmlParse.getList();
    }

    public class Root
    {
        private Customer customer;

        private Order order;

        public Customer getCustomer()
        {
            return customer;
        }

        public void setCustomer(Customer customer)
        {
            this.customer = customer;
        }

        public Order getOrder()
        {
            return order;
        }

        public void setOrder(Order order)
        {
            this.order = order;
        }
    }

    public class Customer
    {
        private String id;

        private String name;

        public String getId()
        {
            return id;
        }

        public void setId(String id)
        {
            this.id = id;
        }

        public String getName()
        {
            return name;
        }

        public void setName(String name)
        {
            this.name = name;
        }

    }

    public class Order
    {
        private String orderId;

        private String orderName;

        public String getOrderId()
        {
            return orderId;
        }

        public void setOrderId(String orderId)
        {
            this.orderId = orderId;
        }

        public String getOrderName()
        {
            return orderName;
        }

        public void setOrderName(String orderName)
        {
            this.orderName = orderName;
        }
    }

}
