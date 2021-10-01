package com.company.engine;

import com.company.objects.Setting;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

import static com.company.forms.ErrorForm.createErrorForm;
import static com.company.forms.InformationForm.createInformationForm;

public class XMLsupport {

    /**********************/
    /**  public methods  **/
    /**********************/

    public static void writeSettingsInXML(String settingFilePath, boolean startInit) {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double screenWidth = screenSize.getWidth();
        double screenHeight = screenSize.getHeight();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;

        try {

            builder = factory.newDocumentBuilder();

            Document doc = builder.newDocument();
            Element rootElement = doc.createElement("Settings");
            doc.appendChild(rootElement);

            rootElement.appendChild(writeSettingInXLM(doc, new Setting("screenWidth", String.valueOf((int)screenWidth))));
            rootElement.appendChild(writeSettingInXLM(doc, new Setting("screenHeight", String.valueOf((int)screenHeight))));
            rootElement.appendChild(writeSettingInXLM(doc, new Setting("fullScreenMode", "1")));

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);

            StreamResult settingFile = new StreamResult(new File(settingFilePath));

            transformer.transform(source, settingFile);

            if (!startInit)
                createInformationForm("Saved!", "Settings saved successfully");

        } catch (Exception e) {
            createErrorForm("Not saved!", e);
        }

    }

    public static ArrayList<Setting> readSettingsInXML(String settingFilePath) {

        ArrayList<Setting> settingsList = new ArrayList<>();

        File settingFile = new File(settingFilePath);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;

        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(settingFile);
            doc.getDocumentElement().normalize();

            Node rootElement = doc.getDocumentElement();
            NodeList nodeList = rootElement.getChildNodes();


            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() != Node.TEXT_NODE) {
                    NodeList settingNodes = node.getChildNodes();
                    Setting setting = new Setting();
                    setting.setName(node.getNodeName());
                    for (int j = 0; j < settingNodes.getLength(); j++) {
                        Node settingNode = settingNodes.item(j);
                        setting.setValue(settingNode.getNodeValue());

                    }
                    settingsList.add(setting);
                }
            }

        } catch (Exception e) {
            createErrorForm("Unread!", e);
        }

        return settingsList;
    }

    /**********************/
    /**  private methods **/
    /**********************/

    private static Node writeSettingInXLM(Document doc, Setting setting) {
        Element settingElem = doc.createElement(setting.getName());
        settingElem.appendChild(doc.createTextNode(setting.getValue()));
        return settingElem;
    }


//    private static Setting readSettingInXML(Node node) {
//        Setting setting = new Setting();
//        if (node.getNodeType() == Node.ELEMENT_NODE) {
//            Element element = (Element) node;
//            setting.setName(element.getNextSibling().getNodeName());
//            setting.setValue(element.getNextSibling().getFirstChild());
//        }
//
//        return setting;
//    }

}
