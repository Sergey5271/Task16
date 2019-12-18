package parser;

import exception.XMLException;
import org.xml.sax.SAXException;
import security.SecurityConstraint;
import security.SecurityProperties;
import security.SecurityRoot;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class XMLParser {

    public static final Logger LOGGER = Logger.getLogger(XMLParser.class.getName());

    private SecurityRoot securityRoot = new SecurityRoot();

    public SecurityProperties parse(File file) throws XMLException, ParserConfigurationException, SAXException, IOException, JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(SecurityRoot.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        securityRoot = (SecurityRoot) unmarshaller.unmarshal(file);
        System.out.println(securityRoot);
        for (SecurityConstraint constraint : securityRoot.getConstraints()) {
            String regexpPattern = constraint.getUrlPattern().replaceAll("\\*", "\\\\w+");
            constraint.setUrlPattern(regexpPattern);
        }
        return new SecurityProperties(securityRoot.getConstraints());

    }
}
