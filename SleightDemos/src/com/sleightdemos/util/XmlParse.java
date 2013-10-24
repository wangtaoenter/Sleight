package com.sleightdemos.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

public class XmlParse<T> extends DefaultHandler
{
    private StringBuffer buf;

    private Field[] fields;
    private ArrayList<T> ls;
    private Class<?> classType;
    private Object tmpObj;

    public XmlParse(T obj)
    {
        Class<?> type = obj.getClass();
        fields = type.getDeclaredFields();
        this.classType = type;
        buf = new StringBuffer();
    }

    public void parse(String xmlStr)
    {
        SAXParserFactory saxParserfactory = SAXParserFactory.newInstance();
        SAXParser saxParser = null;
        XMLReader xmlReader = null;
        InputStream inputStream = null;
        try
        {
            saxParser = saxParserfactory.newSAXParser();
            xmlReader = saxParser.getXMLReader();
            xmlReader.setContentHandler(this);
            inputStream = new ByteArrayInputStream(xmlStr.getBytes());
            xmlReader.parse(new InputSource(inputStream));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /*
     * 解析XML文档开始是将自动调用这个方法
     * @see org.xml.sax.helpers.DefaultHandler#startDocument()
     */
    @Override
    public void startDocument() throws SAXException
    {
        // TODO Auto-generated method stub
        //super.startDocument();
        ls = new ArrayList<T>();
    }

    /*
     * 文档解析结束的时候将自动调用这个方法
     * @see org.xml.sax.helpers.DefaultHandler#endDocument()
     */
    @Override
    public void endDocument() throws SAXException
    {
        // TODO Auto-generated method stub
        super.endDocument();
    }

    /*
     * 当解析到某个元素是将调用这个方法 首先判断是不是一个类元素的开始，如果是一个类元素开始，比如你解析的XML内容如下：
     * <Person><name>张三</name><age>78</age><sex>男</sex></Person>
     * 那么在这个方法中我们将判断解析到的元素是不是Person，如果是，那么就新建一个Person对象
     * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String,
     * java.lang.String, java.lang.String, org.xml.sax.Attributes)
     */
    @Override
    public void startElement(String uri, String localName, String qName,
        Attributes attributes) throws SAXException
    {
        // TODO Auto-generated method stub
        //System.out.println(qName);
        if (qName.equals(classType.getSimpleName()))
        {
            try
            {
                tmpObj = classType.newInstance();
            }
            catch (InstantiationException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            catch (IllegalAccessException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }

    /*
     * 当解析到某个元素结束是，首先判断是不是Person， 如果不是，那说明要么就还没有解析到Person对象内容还没有结束，就
     * 将把解析到的元素名和Person类中的成员变量名进行比较
     * ，如果比较成功，那么就调用相应的set方法给前面生成的那个Person对象中相应的成员变量赋值。
     * 如果是，那就说明一个Person对象里面东西都解析完成了，那么就将生成的这个Person对象添加到List中。
     * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String,
     * java.lang.String, java.lang.String)
     */
    @Override
    public void endElement(String uri, String localName, String qName)
        throws SAXException
    {
        // TODO Auto-generated method stub
        if (qName.equals(classType.getSimpleName()))
        {
            ls.add((T) tmpObj);
        }
        else
        {
            for (Field field : fields)
            {
                String name = field.getName();
                if (qName.equals(name))
                {
                    String firstLetter = name.substring(0, 1).toUpperCase();

                    String setMethodName = "set" + firstLetter
                        + name.substring(1);

                    try
                    {
                        Method method = classType.getDeclaredMethod(
                            setMethodName, new Class[] {field.getType() });
                        method.invoke(tmpObj, new Object[] {buf.toString() });
                        buf.setLength(0);
                    }
                    catch (SecurityException e)
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    catch (NoSuchMethodException e)
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    catch (IllegalArgumentException e)
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    catch (IllegalAccessException e)
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    catch (InvocationTargetException e)
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }

        }
    }

    /*
     * 截取元素中间的内容
     * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
     */
    @Override
    public void characters(char[] ch, int start, int length)
        throws SAXException
    {
        // TODO Auto-generated method stub
        buf.append(ch, start, length);
    }

    /**
     * 返回解析生成的List<T>
     * 
     * @return
     */
    public List<T> getList()
    {
        // TODO Auto-generated method stub
        return ls;
    }

}
