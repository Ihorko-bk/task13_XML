package name.ihorko.parsers;

import name.ihorko.model.Flower;
import name.ihorko.model.GrowingTips;
import name.ihorko.model.Soil;
import name.ihorko.model.VisualParameters;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParserSAX {
    public static List<Flower> readDocumentAndGiveMeFlowers(File xml) {
        try {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            SAXParser saxParser = saxParserFactory.newSAXParser();
            SAXHandler saxHandler = new SAXHandler();
            saxParser.parse(xml, saxHandler);

            return saxHandler.getFlowers();
        } catch (SAXException | ParserConfigurationException | IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}

class SAXHandler extends DefaultHandler {

    private List<Flower> flowers = new ArrayList<>();
    private Flower flower;
    private VisualParameters visualParameters;
    private GrowingTips growingTips = new GrowingTips();

    private boolean bName, bSoil, bOrigin,
            bVisualParameters, bStemColor, bLeafColor, bPlantAverageSize,
            bGrowingTips, bTemperature, bLight, bWatering,
            bMultiplying;

    public List<Flower> getFlowers() {
        return flowers;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("flower")) {
            flower = new Flower();
        } else if (qName.equalsIgnoreCase("name")) {
            bName = true;
        } else if (qName.equalsIgnoreCase("soil")) {
            bSoil = true;
        } else if (qName.equalsIgnoreCase("origin")) {
            bOrigin = true;
        }
        // контейнери пропускати бо в них одразу знаходиться наступне значення, якогось хера
//        else if (qName.equalsIgnoreCase("visual_parameters")) {
//            bVisualParameters = true;
//        }
        else if (qName.equalsIgnoreCase("stem_color")) {
            bStemColor = true;
        } else if (qName.equalsIgnoreCase("leaf_color")) {
            bLeafColor = true;
        } else if (qName.equalsIgnoreCase("plant_average_size")) {
            bPlantAverageSize = true;
        }
        // контейнери пропускати бо в них одразу знаходиться наступне значення, якогось хера
//        else if (qName.equalsIgnoreCase("growing_tips")) {
//            bGrowingTips = true;
//        }
        else if (qName.equalsIgnoreCase("temperature")) {
            bTemperature = true;
        } else if (qName.equalsIgnoreCase("light")) {
            bLight = true;
        } else if (qName.equalsIgnoreCase("watering")) {
            bWatering = true;
        } else if (qName.equalsIgnoreCase("multiplying")) {
            bMultiplying = true;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (bName) {
            flower.setName(new String(ch, start, length));
            bName = false;
        } else if (bSoil) {
            flower.setSoil(Soil.getSoil(new String(ch, start, length)));
            bSoil = false;
        } else if (bOrigin) {
            flower.setOrigin(new String(ch, start, length));
            bOrigin = false;
        }
        // контейнери пропускати бо в них одразу знаходиться наступне значення, якогось хера
//        else if (bVisualParameters) {
//            visualParameters = new VisualParameters();
//            bVisualParameters = false;
//            System.out.print("bVisualParameters=");
//            System.out.println(new String(ch, start, length));
//        }
        else if (bStemColor) {
            visualParameters = new VisualParameters();

            visualParameters.setStemColor(new String(ch, start, length));
            bStemColor = false;
        } else if (bLeafColor) {
            visualParameters.setLeafColor(new String(ch, start, length));
            bLeafColor = false;
        } else if (bPlantAverageSize) {
            visualParameters.setPlantAverageSize(Double.parseDouble(new String(ch, start, length)));
            flower.setVisualParameters(visualParameters);
            bPlantAverageSize = false;
        }
        // контейнери пропускати бо в них одразу знаходиться наступне значення, якогось хера
//        else if (bGrowingTips) {
//            growingTips = new GrowingTips();
//            bGrowingTips = false;
//        }
        else if (bTemperature) {
            growingTips = new GrowingTips();

            growingTips.setTemperature(Double.parseDouble(new String(ch, start, length)));
            bTemperature = false;
        } else if (bLight) {
            growingTips.setLight(Boolean.parseBoolean(new String(ch, start, length)));
            bLight = false;
        } else if (bWatering) {
            growingTips.setWatering(Integer.parseInt(new String(ch, start, length)));
            flower.setGrowingTips(growingTips);
            bWatering = false;
        } else if (bMultiplying) {
            flower.setMultiplying(new String(ch, start, length));
            bMultiplying = false;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("flower")) {
            flowers.add(flower);
        }
    }
}
