/*
 * 文件名: XMLParserEngine.java
 * 版    权：   
 * 描    述: [该类的简要描述]
 * 创建人: w00138133
 * 创建时间:2011-7-19
 * 
 * 修改人：
 * 修改时间:
 * 修改内容：[修改内容]
 */
package com.sleightdemos.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;

import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

/**
 * XML至对象解析
 * 
 * @author w00138133
 * @version [v1.0, 2011-7-19]
 */
public class XMLParserEngine<T> extends DefaultHandler
{
    /**
     * 目标对象
     */
    private T targetObj;

    /**
     * 目标对象class
     */
    private Class<?> targetClassType;

    /**
     * 目标对象Fields
     */
    private Field[] targetFields;

    private Object tmpObj;

    private StringBuffer sb;

    public XMLParserEngine(T target)
    {
        targetObj = target;
        targetClassType = target.getClass();
        targetFields = targetClassType.getFields();
        sb = new StringBuffer();
    }

    public T parse(String xmlStr)
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
        catch (FactoryConfigurationError e)
        {
            e.printStackTrace();
        }
        catch (ParserConfigurationException e)
        {
            e.printStackTrace();
        }
        catch (SAXException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (null != inputStream)
                {
                    inputStream.close();
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return targetObj;
    }

    @Override
    public void characters(char[] ch, int start, int length)
        throws SAXException
    {
        sb.append(ch, start, length);
    }

    @Override
    public void endDocument() throws SAXException
    {
        // TODO Auto-generated method stub
        super.endDocument();
    }

    @Override
    public void endElement(String uri, String localName, String qName)
        throws SAXException
    {
        // TODO Auto-generated method stub
        super.endElement(uri, localName, qName);
    }

    @Override
    public void endPrefixMapping(String prefix) throws SAXException
    {
        // TODO Auto-generated method stub
        super.endPrefixMapping(prefix);
    }

    @Override
    public void error(SAXParseException e) throws SAXException
    {
        // TODO Auto-generated method stub
        super.error(e);
    }

    @Override
    public void fatalError(SAXParseException e) throws SAXException
    {
        // TODO Auto-generated method stub
        super.fatalError(e);
    }

    @Override
    public void ignorableWhitespace(char[] ch, int start, int length)
        throws SAXException
    {
        // TODO Auto-generated method stub
        super.ignorableWhitespace(ch, start, length);
    }

    @Override
    public void notationDecl(String name, String publicId, String systemId)
        throws SAXException
    {
        // TODO Auto-generated method stub
        super.notationDecl(name, publicId, systemId);
    }

    @Override
    public void processingInstruction(String target, String data)
        throws SAXException
    {
        // TODO Auto-generated method stub
        super.processingInstruction(target, data);
    }

    @Override
    public InputSource resolveEntity(String publicId, String systemId)
        throws IOException, SAXException
    {
        // TODO Auto-generated method stub
        return super.resolveEntity(publicId, systemId);
    }

    @Override
    public void setDocumentLocator(Locator locator)
    {
        // TODO Auto-generated method stub
        super.setDocumentLocator(locator);
    }

    @Override
    public void skippedEntity(String name) throws SAXException
    {
        // TODO Auto-generated method stub
        super.skippedEntity(name);
    }

    @Override
    public void startDocument() throws SAXException
    {

    }

    @Override
    public void startElement(String uri, String localName, String qName,
        Attributes attributes) throws SAXException
    {
        for (int i = 0; i < targetFields.length; i++)
        {
            if (qName.equalsIgnoreCase(targetFields[i].getClass()
                .getSimpleName()))
            {

            }
            else
            {
                //对象，继续解析对象类型，需要new出
            }
        }
    }

    @Override
    public void startPrefixMapping(String prefix, String uri)
        throws SAXException
    {
        // TODO Auto-generated method stub
        super.startPrefixMapping(prefix, uri);
    }

    @Override
    public void unparsedEntityDecl(String name, String publicId,
        String systemId, String notationName) throws SAXException
    {
        // TODO Auto-generated method stub
        super.unparsedEntityDecl(name, publicId, systemId, notationName);
    }

    @Override
    public void warning(SAXParseException e) throws SAXException
    {
        // TODO Auto-generated method stub
        super.warning(e);
    }

}
