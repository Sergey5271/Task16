package parser;

import org.junit.Test;
import security.SecurityRoot;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;

public class XMLToRole {


    private SecurityRoot securityRoot;

    @Test
    public void testXmlToObject() throws JAXBException, FileNotFoundException {
        File file = new File("src/main/webapp/WEB-INF/security.xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(SecurityRoot.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
       securityRoot = (SecurityRoot) unmarshaller.unmarshal(file);
        System.out.println(securityRoot);
    }
}
