//package com.dom;
//import com.domain.People;
//import com.util.CommonUtil;
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.w3c.dom.Node;
//import org.w3c.dom.NodeList;
//
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import java.io.File;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author jiahangchun
// */
//public class DomTest {
//    private static String PATH_NAME="dom/people.xml";
//
//    public static void main(String[] args) throws Exception {
//        String path= CommonUtil.getResourcePath(PATH_NAME);
//        try {
//            File file = new File(path);
//            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder builder = factory.newDocumentBuilder();
//            Document document = builder.parse(file);
//            Element element = document.getDocumentElement();
//
//            List<People> peopleList = new ArrayList<People>();
//            NodeList peopleNodes = element.getElementsByTagName("People");
//            for(int i=0;i<peopleNodes.getLength();i++){
//                People people = new People();
//                Element peopleElement = (Element) peopleNodes.item(i);
//                people.setId(peopleElement.getAttribute("id"));
//                NodeList childPeopleNodes = peopleElement.getChildNodes();
//                for(int j=0;j<childPeopleNodes.getLength();j++){
//                    //DOM解析时候注意子节点前面的空格也会被解析
//                    if(childPeopleNodes.item(j) instanceof Element){
//                        Element childPeopleElement = (Element) childPeopleNodes.item(j);
//                        if(childPeopleElement.getNodeType()== Node.ELEMENT_NODE){
//                            if(childPeopleElement.getNodeName().equals("Name")){
//                                people.setEnglishName(childPeopleElement.getAttribute("en"));
//                                people.setName(childPeopleElement.getTextContent());
//                            }
//                            else if(childPeopleElement.getNodeName().equals("Age")){
//                                people.setAge(childPeopleElement.getTextContent());
//                            }
//                        }
//                    }
//                }
//                peopleList.add(people);
//            }
//
//            for(People people : peopleList){
//                System.out.println(people.getId()+"\t"+people.getName()+"\t"+people.getEnglishName()+"\t"+people.getAge());
//            }
//
//        } catch (Exception e) {
//            // TODO 自动生成的 catch 块
//            e.printStackTrace();
//        }
//    }
//
//
//
//    public static ClassLoader getClassLoader() {
//        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//        if (classLoader == null) {
//            classLoader = DomTest.class.getClassLoader();
//        }
//        return classLoader;
//    }
//}
