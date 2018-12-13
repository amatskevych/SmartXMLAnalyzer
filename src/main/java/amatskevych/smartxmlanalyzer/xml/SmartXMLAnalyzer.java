package amatskevych.smartxmlanalyzer.xml;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class SmartXMLAnalyzer {
    public static List<SmartXMLElement> findElements(String findId, String firstFileName, String secondFileName) throws FileNotFoundException {
        URL urlInputFile = ClassLoader.getSystemResource(firstFileName);
        if (urlInputFile == null) {
            throw new FileNotFoundException("The file does not exist: " + firstFileName);
        }
        File inputFile = new File(urlInputFile.getFile());

        URL urlSecondFile = ClassLoader.getSystemResource(secondFileName);
        if (urlSecondFile == null) {
            throw new FileNotFoundException("The file does not exist: " + secondFileName);
        }
        File secondFile = new File(urlSecondFile.getFile());


        SAXParserFactory factory = SAXParserFactory.newInstance();
        SmartXmlHandler handlerForInputFile = new SmartXmlHandler(findId, null, null);

        try {
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(inputFile, handlerForInputFile);
            List<SmartXMLElement> firstResult = handlerForInputFile.getFindElementPathList();
            if (firstResult.isEmpty()) {
                return null;
            }

            SmartXMLElement foundElement = firstResult.get(0);
            SmartXmlHandler handlerForSecondFile = new SmartXmlHandler(null, foundElement.getClassAttr(), foundElement.getValue());
            saxParser.parse(secondFile, handlerForSecondFile);
            return handlerForSecondFile.getFindElementPathList();

        } catch (ParserConfigurationException| SAXException | IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
