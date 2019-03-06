package name.ihorko.parsers;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.sax.SAXSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ValidatorSAX {

    public static void validate(File xml, File xsd) {
        try {

            Reader reader = Files.newBufferedReader(xml.toPath());

            String schemaLang = XMLConstants.W3C_XML_SCHEMA_NS_URI;
            SchemaFactory factory = SchemaFactory.newInstance(schemaLang);
            Schema schema = factory.newSchema(xsd);

            Validator validator = schema.newValidator();

            SAXSource source = new SAXSource(new InputSource(reader));
            validator.validate(source);

        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }

    }
}
