package amatskevych.smartxmlanalyzer;

import amatskevych.smartxmlanalyzer.xml.SmartXMLAnalyzer;
import amatskevych.smartxmlanalyzer.xml.SmartXMLElement;

import java.io.FileNotFoundException;
import java.util.List;

public class App {
    public static void main(String[] args) {
        String findId = "make-everything-ok-button";
        String inputFilePath = "sample-0-origin.html";
        String secondFilePath = "sample-1-evil-gemini.html";
//        String secondFilePath = "sample-2-container-and-clone.html";
//        String secondFilePath = "sample-3-the-escape.html";
//        String secondFilePath = "sample-4-the-mash.html";

        try {
            List<SmartXMLElement> result = SmartXMLAnalyzer.findElements(findId, inputFilePath, secondFilePath);
            if (result != null) {
                for (SmartXMLElement element : result) {
                    System.out.println(element.getUri());
                }
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
