# SmartXMLAnalyzer
Smart XML Analyzer for AgileEngine

#### Requirements
Write a program that analyzes HTML and finds a specific element, even after changes, using a set of extracted attributes. 
The target element that needs to be found by your program is the green “Everything OK” button.
Any user can easily find this button visually, even when the site changes. Original contains a button with attribute
id="make-everything-ok-button". This id is the only exact criteria, to find the target element in the input file.
See example below.
The program must consume the original page to collect all the required information about the target element.
Then the program should be able to find this element in diff-case HTML document that differs a bit from the original
page. Original and diff-case HTML documents should be provided to the program in each run - no persistence is required.

Consider HTML samples, as regular XML files. No image/in-browser app analysis is needed. No CSS/JS analysis is needed
(CSS/JS files are provided just for demo).

#### Implementation

Technologies: jdk 8, maven, git

Main class: amatskevych.smartxmlanalyzer.App


##### Should be installed ###

* [jdk 8.101 or higher](www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* [maven 3.*](http://maven.apache.org/install.html)

##### For building and running ###

To build the application, open a root directory and execute the command
```
 mvn clean install
```
To start the application, run the command 

```
java -jar target/smartxmlanalyzer-jar-with-dependencies.jar <input_origin_file_path> <input_other_sample_file_path> [<attributeId>]
```
```
Usage: java -jar smartxmlanalyzer-jar-with-dependencies.jar <input_origin_file_path> <input_other_sample_file_path> [<attributeId>]
 <input_origin_file_path> - origin sample path to find the element with attribute id="make-everything-ok-button" and collect all the required information;
 <input_other_sample_file_path> - path to diff-case HTML file to search a similar element;
 <attributeId> - optional, the target element id for collecting the initial information, default value = "make-everything-ok-button"
```

