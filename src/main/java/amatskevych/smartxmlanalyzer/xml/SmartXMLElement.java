package amatskevych.smartxmlanalyzer.xml;

public class SmartXMLElement {
    private String idAttr;
    private String classAttr;
    private String value;
    private String uri;

    SmartXMLElement(String idAttr, String classAttr, String value, String uri) {
        this.idAttr = idAttr;
        this.classAttr = classAttr;
        this.value = value;
        this.uri = uri;
    }

    @SuppressWarnings("unused")
    public String getIdAttr() {
        return idAttr;
    }

    String getClassAttr() {
        return classAttr;
    }

    String getValue() {
        return value;
    }

    public String getUri() {
        return uri;
    }
}
