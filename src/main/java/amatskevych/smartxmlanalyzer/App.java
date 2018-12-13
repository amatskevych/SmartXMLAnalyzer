package amatskevych.smartxmlanalyzer;

import amatskevych.smartxmlanalyzer.xml.SmartXMLAnalyzer;
import amatskevych.smartxmlanalyzer.xml.SmartXMLElement;

import java.io.FileNotFoundException;
import java.util.List;

public class App {
    public static void main(String[] args) {
        if (args.length != 2 && args.length != 3) {
            usage();
            System.exit(1);
        }

        String findId = args.length == 3 ? args[2] : "make-everything-ok-button";
        String inputFilePath = args[0];
        String secondFilePath = args[1];

        try {
            List<SmartXMLElement> result = SmartXMLAnalyzer.findElements(findId, inputFilePath, secondFilePath);
            if (result != null) {
                for (SmartXMLElement element : result) {
                    System.out.println(element.getUri());
                }
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }

    private static void usage() {
        System.err.println("Usage: java -jar smartxmlanalyzer-jar-with-dependencies.jar  <input_origin_file_path>"
                + " <input_other_sample_file_path> [<attributeId>]");
        System.err.println(" <input_origin_file_path> - origin sample path to find the element with attribute "
                + "id=\"make-everything-ok-button\" and collect all the required information;");
        System.err.println(" <input_other_sample_file_path> - path to diff-case HTML file to search a similar element;");
        System.err.println(" <attributeId> - optional, the target element id for collecting the initial information,"
                + " default value = \"make-everything-ok-button\"");
    }
}
