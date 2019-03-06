package name.ihorko.parsers;

import name.ihorko.model.Flower;
import name.ihorko.model.GrowingTips;
import name.ihorko.model.Soil;
import name.ihorko.model.VisualParameters;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParserDOM {

    public static List<Flower> readDocumentAndGiveMeFlowers(File xml) {
        Document document = createDocument(xml);
        document.getDocumentElement().normalize();
        List<Flower> flowers = new ArrayList<>();

        NodeList nodeList = document.getElementsByTagName("flower");

        for (int i = 0; i < nodeList.getLength(); i++) {

            Flower flower = new Flower();

            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                flower.setName(getElementByTag(element, "name"));
                flower.setSoil(Soil.getSoil(getElementByTag(element, "soil")));
                flower.setOrigin(getElementByTag(element,"origin"));
                flower.setVisualParameters(getVisualParameters(element.getElementsByTagName("visual_parameters")));
                flower.setGrowingTips(getGrowingTips(element.getElementsByTagName("growing_tips")));
                flower.setMultiplying(getElementByTag(element, "multiplying"));

                flowers.add(flower);
            }
        }
        return flowers;
    }

    private static GrowingTips getGrowingTips(NodeList nodes) {
        GrowingTips growingTips = new GrowingTips();
        if (nodes.item(0).getNodeType() == Node.ELEMENT_NODE){
            Element element = (Element) nodes.item(0);
            growingTips.setTemperature(Double.parseDouble(getElementByTag(element, "temperature")));
            growingTips.setLight(Boolean.parseBoolean(getElementByTag(element, "light")));
            growingTips.setWatering(Integer.parseInt(getElementByTag(element, "watering")));
        }
            return growingTips;
    }
    private static VisualParameters getVisualParameters(NodeList nodes) {
        VisualParameters visualParameters = new VisualParameters();
        if (nodes.item(0).getNodeType() == Node.ELEMENT_NODE){
            Element element = (Element) nodes.item(0);
            visualParameters.setStemColor(getElementByTag(element, "stem_color"));
            visualParameters.setLeafColor(getElementByTag(element, "leaf_color"));
            visualParameters.setPlantAverageSize(Double.parseDouble(getElementByTag(element, "plant_average_size")));
        }
        return visualParameters;
    }

    private static String getElementByTag(Element element, String tag) {
        return element.getElementsByTagName(tag).item(0).getTextContent();
    }

    public static Document createDocument(File xml) {
        try {
            return createDocumentBuilder().parse(xml);
        }catch (SAXException | IOException ex){
            ex.printStackTrace();
        }
        return null;
    }
    private static DocumentBuilder createDocumentBuilder() {
        try {
            return DocumentBuilderFactory.newInstance().newDocumentBuilder();
        }catch (ParserConfigurationException ex){
            ex.printStackTrace();
        }
        return null;
    }
}
