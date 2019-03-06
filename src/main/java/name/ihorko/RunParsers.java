package name.ihorko;

import name.ihorko.parsers.ParserDOM;
import name.ihorko.parsers.ParserSAX;
import name.ihorko.parsers.ValidatorDOM;
import name.ihorko.parsers.ValidatorSAX;

import java.io.File;

public class RunParsers {
    public static void main(String[] args) {
        File flowersXML = new File("src\\main\\resources\\xml\\flowers.xml");
        File flowersXSD = new File("src\\main\\resources\\xml\\flowers.xsd");
        File flowersXSL = new File("src\\main\\resources\\xml\\flowers.xsl");


        // show collection of Flower's by DOM parser
//        System.out.println(ParserDOM.readDocumentAndGiveMeFlowers(flowersXML));
        // validate xml to schema by DOM
//        ValidatorDOM.validate(flowersXML, flowersXSD);

        // show collection of Flower's by SAX parser
//        System.out.println(ParserSAX.readDocumentAndGiveMeFlowers(flowersXML));
        // validate xml to schema by SAX
        ValidatorSAX.validate(flowersXML, flowersXSD);


    }
}
