package by.vitebsk.energo.koldunova.reader;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class MyXmlReader {
    private static final String XML_CONFIG = "./src/by/vitebsk/energo/resource/config.xml";

    public static String readXmlConfig() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;

        try {
            builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(XML_CONFIG));

            Node root = document.getDocumentElement();
            NodeList configDate = root.getChildNodes();

            for (int j = 0; j < configDate.getLength(); j++) {
                Node conf = configDate.item(j);
                if (conf.getNodeType() != Node.TEXT_NODE) {
                    if (conf.getNodeName().equals("ConnectionSender")) {
                        return conf.getChildNodes().item(0).getTextContent();
                    }
                }
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
