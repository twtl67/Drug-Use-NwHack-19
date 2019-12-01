package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Pair;
import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, ParseException {
//        Set<String> test = new HashSet<>();
//        test.add("Aspirin");
//        test.add("sertraline");
//        test.add("bosentan");
//
//        Data d = new Data(test);
//        d.readFile("C:\\Users\\shsu8\\IdeaProjects\\Graph\\src\\sample\\input");
//        System.out.println(d.readAPI());
//        Set<String> drugs = d.getDrugs();
//        for (String s : drugs)
//            System.out.println(s + "\n");
//
//        System.out.println("interactions");
//        for(Pair<String, String> r : d.getInteractions()) {
//            System.out.println(r.getKey() + ", " + r.getValue());
//        }
        launch(args);

    }



}
