package name.ihorko.parsers;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class ValidatorDOM {

    public static void validate(File xml, File xsd) {
        validate(ParserDOM.createDocument(xml), createSchema(xsd));
    }
    public static void validate(Document xml, Schema xsd) {
        try {
            xsd.newValidator().validate(new DOMSource(xml));
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
    }
    public static Schema createSchema(File xsd) {
        try {
            return SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI).newSchema(xsd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
