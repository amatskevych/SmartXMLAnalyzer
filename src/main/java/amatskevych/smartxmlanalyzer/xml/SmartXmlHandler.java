package amatskevych.smartxmlanalyzer.xml;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class SmartXmlHandler extends DefaultHandler {
    private boolean isFindIdAndClass;
    private boolean isFindElement;

    private String foundElementQNameName;
    private String currentId;
    private String currentClass;
    private String currentValue;
    private LinkedList<String> currentPath = new LinkedList<>();

    private String findId;
    private String findClass;
    private String findValue;

    private List<SmartXMLElement> findElementPathList = new ArrayList<>();

    SmartXmlHandler(String findId, String findClass, String findValue) {
        this.findId = findId;
        this.findClass = findClass;
        this.findValue = findValue;
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        currentPath.addLast(qName);

        isFindIdAndClass = false;
        isFindElement = false;
        foundElementQNameName = null;

        if (attributes == null) {
            return;
        }

        currentId = attributes.getValue("id");
        if (findId == null || findId.equalsIgnoreCase(currentId)) {
            currentClass = attributes.getValue("class");
            if (findClass == null || findClass.equalsIgnoreCase(currentClass)) {
                isFindIdAndClass = true;
                foundElementQNameName = qName;
                currentValue = "";
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        currentPath.removeLast();

        if (foundElementQNameName == null || !foundElementQNameName.equalsIgnoreCase(qName)) {
            return;
        }
        if (!isFindIdAndClass) {
            return;
        }
        isFindElement = findValue == null || findValue.equalsIgnoreCase(currentValue);
        if (isFindElement) {
            SmartXMLElement element = new SmartXMLElement(currentId, currentClass, currentValue, getXmlPath());
            findElementPathList.add(element);
            isFindIdAndClass = false;
        }
    }

    public void characters(char[] ch, int start, int length) {
        if (isFindIdAndClass) {
            currentValue += new String(ch, start, length).replaceAll("\\n", "").trim();
        }
    }

    List<SmartXMLElement> getFindElementPathList() {
        return findElementPathList;
    }

    private String getXmlPath() {
        Iterator<String> it = currentPath.iterator();
        StringBuilder sb = new StringBuilder();
        while (it.hasNext()) {
            sb.append(it.next()).append(" > ");
        }
        sb.append(foundElementQNameName);
        return sb.toString();
    }
}
