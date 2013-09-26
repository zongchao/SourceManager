package com.neko.xml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;


/**
 * @author zc
 * @date 20120313
 */
public class XMLUtil
{
    /**
     * 从文件读取XML，输入文件名，返回XML文档 
     * @param fileName xml文件路径
     * @return xml document
     * @throws MalformedURLException
     * @throws DocumentException
     */
    public static Document read(String fileName) throws MalformedURLException, DocumentException 
    {
        SAXReader reader = new  SAXReader();
        Document document = reader.read(new File(fileName));
        return document;
    }
    
    /**
     * 从流读取XML，返回XML文档 
     * @param inputStream 输入流
     * @return xml document
     * @throws MalformedURLException
     * @throws DocumentException
     */
    public static Document read(InputStream inputStream) throws DocumentException 
    {
        SAXReader reader = new  SAXReader();
        Document document = reader.read(inputStream);
        return document;
    }
    
    /**
     * 取得Root节点
     * @param doc xml document
     * @return xml 根节点
     */
    public static Element getRootElement(Document doc)
    {
        return doc.getRootElement();
    }
    
    
    public static String getElementText(Element element, String elementName)
    {
        String elementText = "";
        try
        {
            elementText = element.element(elementName).getTextTrim();
        }
        catch (Exception e)
        {
            elementText="";
        }
        return elementText;
    }
 
    public static void setElementText(Element element ,String elementText, String elementName)
    {
        try
        {
           element.element(elementName).setText(elementText);
        }
        catch (Exception e)
        {
        }
    }
    
    /*
     * 输出xml
     * */
    public static void writerXML(Document doc,String url) 
    {
        XMLWriter writer = null;
        //设置格式
        OutputFormat format = OutputFormat.createPrettyPrint();
        //设置字符集编码
        format.setEncoding("utf-8");
        try 
        {
            //输出xml操作
            writer= new XMLWriter(new FileWriter(new File(url)),format);
            writer.write(doc);
            writer.close(); 
        } 
        catch (Exception e) 
        {
          e.printStackTrace();
        }
    }
    
    /*
     * 输出xml
     * */
    public static void writerXML(Document doc,OutputStream out) 
    {
        XMLWriter writer = null;
        //设置格式
        OutputFormat format = OutputFormat.createPrettyPrint();
        //设置字符集编码
        format.setEncoding("utf-8");
        try 
        {
            //输出xml操作
            writer= new XMLWriter(out,format);
            writer.write(doc);
            writer.close(); 
        } 
        catch (Exception e) 
        {
          e.printStackTrace();
        }
    }
}
