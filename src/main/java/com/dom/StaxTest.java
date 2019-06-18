package com.dom;

import com.sun.xml.internal.fastinfoset.stax.events.EndDocumentEvent;
import com.sun.xml.internal.fastinfoset.stax.events.EndElementEvent;
import com.sun.xml.internal.fastinfoset.stax.events.StartDocumentEvent;
import com.sun.xml.internal.fastinfoset.stax.events.StartElementEvent;
import com.sun.xml.internal.stream.events.CharacterEvent;
import com.util.CommonUtil;

import javax.xml.namespace.QName;
import javax.xml.stream.*;
import javax.xml.stream.events.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;

/**
 * @author jiahangchun
 */
public class StaxTest {

    private static String PATH_NAME = "dom/people.xml";

    private InputStream is;

    public static void main(String[] args) throws Exception {
        StaxTest staxTest = new StaxTest();
        staxTest.setUp();
        staxTest.testRetrieveByCursor();
        /**
         * 流被使用了
         */
//        staxTest.testCreateByCursor();
//        staxTest.testRetrieveByIterator();
//        staxTest.testCreateByCursor();
//        staxTest.testCreateByIterator();
    }

    public void setUp() throws Exception {
        String path = CommonUtil.getResourcePath(PATH_NAME);
        is = new FileInputStream(new File(path));
    }

    /**
     * 基于指针的方式读取xml文档——XMLStreamReader
     *
     * @throws Exception
     */
    public void testRetrieveByCursor() throws Exception {
        //创建读取流工厂对象
        XMLInputFactory factory = XMLInputFactory.newInstance();
        //创建基于指针的读取流对象
        XMLStreamReader streamReader = factory.createXMLStreamReader(is);
        //用指针迭代
        while (streamReader.hasNext()) {
            //事件的ID
            int eventId = streamReader.next();

            switch (eventId) {
                case XMLStreamConstants.START_DOCUMENT:
                    System.out.println("start docmuent");
                    break;

                case XMLStreamConstants.START_ELEMENT:
                    System.out.println("<" + streamReader.getLocalName() + ">");
                    for (int i = 0; i < streamReader.getAttributeCount(); i++) {
                        System.out.println(streamReader.getAttributeLocalName(i) +
                                "=" + streamReader.getAttributeValue(i));
                    }
                    break;

                case XMLStreamConstants.CHARACTERS:
                    if (streamReader.isWhiteSpace()) {
                        break;
                    }
                    System.out.println(streamReader.getText());
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    System.out.println("</" + streamReader.getLocalName() + ">");

                    break;
                case XMLStreamConstants.END_DOCUMENT:
                    System.out.println("end docmuent");
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 基于迭代器的方式读取xml文档——XMLEventReader
     *
     * @throws Exception
     */
    public void testRetrieveByIterator() throws Exception {
        //创建读取流工厂对象
        XMLInputFactory factory = XMLInputFactory.newInstance();
        //创建基于迭代器（事件流对象）的流对象
        XMLEventReader eventReader = factory.createXMLEventReader(is);
        //迭代xml文档
        while (eventReader.hasNext()) {
            //得到具体的 事件对象，就是引发事件的对象（可以是元素节点、文本节点、属性节点）
            XMLEvent event = eventReader.nextEvent();

            switch (event.getEventType()) {
                case XMLStreamConstants.START_DOCUMENT:
                    System.out.println("start docmuent");
                    break;

                case XMLStreamConstants.START_ELEMENT:
                    //将事件对象可以转换为元素节点对象
                    StartElement element = (StartElement) event;
                    System.out.println("<" + element.getName().getLocalPart() + ">");
                    for (Iterator it = element.getAttributes(); it.hasNext(); ) {
                        Attribute attr = (Attribute) it.next();
                        System.out.println(attr.getName().getLocalPart() + "=" + attr.getValue());
                    }
                    break;

                case XMLStreamConstants.CHARACTERS:
                    //将事件对象可以转换成文本节点
                    Characters charData = (Characters) event;
                    if (charData.isIgnorableWhiteSpace() && charData.isWhiteSpace()) {
                        break;
                    }
                    System.out.println(charData.getData());
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    //将事件对象可以转换为元素节点对象
                    EndElement endElement = (EndElement) event;
                    System.out.println("</" + endElement.getName().getLocalPart() + ">");

                    break;
                case XMLStreamConstants.END_DOCUMENT:
                    System.out.println("end docmuent");
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 基于指针的API输出流——XMLStreamWriter
     *
     * @throws Exception
     */
    public void testCreateByCursor() throws Exception {
        //创建输出流对象工厂
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        //创建输出流对象
        XMLStreamWriter streamWriter = factory.createXMLStreamWriter(System.out);
        //创建xml文档，根据对象方法创建对象元素
        streamWriter.writeStartDocument();
        //book start
        streamWriter.writeStartElement("book");
        streamWriter.writeAttribute("category", "CODING");

        streamWriter.writeStartElement("title");
        streamWriter.writeCharacters("Java Coding");
        streamWriter.writeEndElement();

        streamWriter.writeStartElement("author");
        streamWriter.writeCharacters("lisa");
        streamWriter.writeEndElement();

        streamWriter.writeStartElement("year");
        streamWriter.writeCharacters("2013");
        streamWriter.writeEndElement();

        streamWriter.writeStartElement("price");
        streamWriter.writeCharacters("79.9");
        streamWriter.writeEndElement();

        //book end
        streamWriter.writeEndElement();

        streamWriter.writeEndDocument();
        streamWriter.flush();
    }

    /**
     * 基于迭代器的API输出流——XMLEventWriter
     *
     * @throws Exception
     */
    public void testCreateByIterator() throws Exception {
        //创建输出流对象工厂
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        //创建输出流对象
        XMLEventWriter eventWriter = factory.createXMLEventWriter(System.out);
        //创建xml文档，根据对象方法创建对象元素
        eventWriter.add(new StartDocumentEvent());
        eventWriter.add(new StartElementEvent(new QName("book")));

        eventWriter.add(new StartElementEvent(new QName("title")));
        eventWriter.add(new CharacterEvent("Java Coding"));
        eventWriter.add(new EndElementEvent(new QName("title")));

        eventWriter.add(new StartElementEvent(new QName("author")));
        eventWriter.add(new CharacterEvent("rilay"));
        eventWriter.add(new EndElementEvent(new QName("author")));

        eventWriter.add(new StartElementEvent(new QName("year")));
        eventWriter.add(new CharacterEvent("2008"));
        eventWriter.add(new EndElementEvent(new QName("year")));

        eventWriter.add(new StartElementEvent(new QName("price")));
        eventWriter.add(new CharacterEvent("29.9"));
        eventWriter.add(new EndElementEvent(new QName("price")));

        eventWriter.add(new EndElementEvent(new QName("book")));
        eventWriter.add(new EndDocumentEvent());
        eventWriter.flush();
    }

}