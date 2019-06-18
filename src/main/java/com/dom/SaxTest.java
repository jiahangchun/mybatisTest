package com.dom;

import com.util.CommonUtil;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

/**
 * @author jiahangchun
 */
public class SaxTest extends DefaultHandler {

    private static String PATH_NAME="dom/people.xml";


    public static void main(String[] args) {
        try {
            String path= CommonUtil.getResourcePath(PATH_NAME);
            File inputFile = new File(path);

            // 创建sax解析工厂
            SAXParserFactory factory = SAXParserFactory.newInstance();
            // 创建sax转换工具
            SAXParser saxParser = factory.newSAXParser();

            // 解析xml
            saxParser.parse(inputFile, new SaxTest());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void startElement(String uri,
                             String localName, String qName, Attributes attributes) throws SAXException {
        System.out.println("<" + qName + ">");
//        System.out.println(uri+" "+localName+" "+qName);
    }

    @Override
    public void endElement(String uri,
                           String localName, String qName) throws SAXException {
        System.out.println("2</" + qName + ">");
//        System.out.println(uri+" "+localName+" "+qName);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String str = new String(ch, start, length);
        System.out.println("3"+str);
    }
}