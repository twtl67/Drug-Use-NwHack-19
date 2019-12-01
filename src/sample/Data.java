package sample;

import javafx.util.Pair;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import scala.xml.Elem;

import javax.swing.text.html.ListView;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.*;
import java.util.regex.Pattern;

public class Data {
    private int rxcui = 88014;

    private Set<Pair<String, String>> interactions;
    private Set<String> drugs;
    private String BASE_URL = "https://rxnav.nlm.nih.gov/REST/interaction/list?rxcuis=";


    public Data(Set<String> m) throws IOException, SAXException, ParseException, ParserConfigurationException {
        interactions = new HashSet<>();
        drugs = m;

        readAPI();
    }

    public String readAPI() throws IOException, SAXException, ParseException, ParserConfigurationException {

        String allDrugRxcui = inputListMaker();
//        System.out.println(allDrugRxcui);

        StringBuilder result = new StringBuilder();
        URL url = new URL(BASE_URL + allDrugRxcui /*+ "&sources=ONCHigh"*/);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));


        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
//            System.out.println(line + "\n" + "0000000");
        }

        String[] splitRxcui = result.toString().split("interactionConcept");
        for (String s : splitRxcui) {
//            System.out.println(s);
            extractRxcui(s);
        }



/*
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        try {
            builder = factory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(new StringReader(result.toString())));

            NodeList h = document.getElementsByTagName("interactionTypeGroup");
            Element element = (Element) h;
            ((Element) h).getElementsByTagName();
//            element.getAttribute()
            System.out.println(h);
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }

*/

//        Document doc = (Document) db.parse(String.valueOf(result));


//        JSON
/*
        JSONParser parser = new JSONParser();
        JSONObject jsonArray =  (JSONObject) parser.parse(rd);


        JSONArray fullInteractionTypeGroup = (JSONArray) jsonArray.get("fullInteractionTypeGroup"); //min concept
        JSONObject fullInteractionType = (JSONObject) fullInteractionTypeGroup.get(0);
//        System.out.println(fullInteractionType);

        String[] interactionPair = fullInteractionType.toString().split("\"interactionPair\"");
        for (String ip : interactionPair) {
//            System.out.println(ip + "\n\n");
            String[] minConcept = ip.split("\"minConceptItem\"");
            for (String mc : minConcept) {
                System.out.println(mc + "\n\n");
            }
        }
*/


//        JSONArray interactionPair = (JSONArray) fullInteractionType.get(2);
//        JSONObject interactionConcept = (JSONObject) interactionPair.get(0);
//        JSONObject minConcept1 = (JSONObject) interactionConcept.get("minConceptItem");
//        JSONObject minConcept2 = (JSONObject) interactionConcept.get(0);

//        System.out.println(minConcept1.toString());
//        System.out.println(minConcept2);

//        String line;
//        while ((line = rd.readLine()) != null) {
//            result.append(line);
//            System.out.println(line);
//        }
//        rd.close();
        return "";

    }

    private void extractRxcui(String s) {
        String[] splitname = s.split("<name>");
        List<String> list = new ArrayList<>();
        for(String section : splitname) {
//            System.out.println(section);
            if (section.charAt(0) != '<' && section.charAt(0) != '>') {
                list.add(splitName2(section)); // returns splitted names
            }
        }
        if (list.isEmpty()) return;
        Pair<String, String> p = new Pair<String, String>(list.get(0), list.get(1));
        interactions.add(p);
//        System.out.println("XXX");
    }

    private String splitName2(String section) {
        String[] splitResult = section.split("</name>");
        List<String> pair = new ArrayList<>();

        return splitResult[0];
/*
        for(String s : splitResult) {
            System.out.println(s);
//            System.out.println(s);
            if (!Pattern.matches("<*", s)) {
                pair.add(s);
//                System.out.println(s);
            }
        }
*/

//        System.out.println("---");

//        String s1 = pair.get(0);
//        String s2 =  pair.get(1);
//        Pair<String, String> p = new Pair<>(s1,s2);
//        interactions.add(p);
    }

    private String inputListMaker() throws SAXException, IOException {
        String returnS = "";
        for(String s : drugs) {
            returnS += toRXCUI(s);
            returnS += "+";
        }
        return returnS.substring(0, returnS.length()-1);
    }

    private String toRXCUI(String drug) throws IOException, SAXException {
        StringBuilder result = new StringBuilder();
        URL url = new URL("https://rxnav.nlm.nih.gov/REST/rxcui?name=" + drug);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));

        String line;
        String[] array = null;
        while ((line = rd.readLine()) != null) {
            result.append(line);
            array = line.split("<rxnormId>");
        }
        rd.close();

        String y = array[1].split("</rxnormId>")[0];
        return y;
    }


    //    read in file data and populate the two sets
    public void readFile(String filePath) throws IOException {
        File file = new File(filePath);

        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        while ((st = br.readLine()) != null) {
            String [] words = st.split(" ");
            interactions.add(new Pair<String, String>(words[0], words[1]));
             drugs.addAll(Arrays.asList(words));
        }
    }

    public Set<Pair<String, String>> getInteractions() {
        return interactions;
    }

    public Set<String> getDrugs() {
        return drugs;
    }

}
