package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.util.Pair;
import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.util.*;
import java.io.*;
/**
 *
 * @author terry
 */
public class MedTreeGraph extends JPanel implements ActionListener {
    Vector<String> names = new Vector<String>();
    Vector<Pair> pairs = new Vector<Pair>();
    int numMeds;

    @FXML
    TextField label;


    @Override
    public void paintComponent(Graphics g) {

//        for(String n :names)
//            System.out.println(n);

        int originX = 250;
        int originY = 250;
//        names.add("Asperin");
//        names.add("Sertraline");
//        names.add("Benadryl");
//        names.add("Water");
//        pairs.add(new Pair("Aspirin", "Sertraline"));
//        pairs.add(new Pair("Sertraline", "Benadryl"));
        Vector<Dataset> data = new Vector<Dataset>();
        double theta = 0;
        int r = 100;
        int x;
        int y;
        for (int i = 0; i < names.size(); i++) {
            x = originX + (int) (r * Math.cos(theta));
            y = originY - (int) (r * Math.sin(theta));
            theta += (2 * Math.PI) / (names.size());
            g.drawString(names.get(i), x, y);
            data.add(new Dataset(names.get(i), x, y));
        }
        String name;
        int index;
        for (int i = 0; i < pairs.size(); i++) {
            name = pairs.get(i).getKey().toString().toUpperCase();
            index = 0;
            for (int j = 0; j < names.size(); j++) {
                if (data.get(j).getName().equals(name)) {
                    index = j;
                    break;
                }
            }
            x = data.get(index).getX();
            y = data.get(index).getY();
            name = pairs.get(i).getValue().toString().toUpperCase();
            index = 0;
            for (int j = 0; j < names.size(); j++) {
                if (data.get(j).getName().equals(name)) {
                    index = j;
                    break;
                }
            }
            g.drawLine(x, y, data.get(index).getX(), data.get(index).getY());
        }
    }
    public void readFile(String filePath) throws IOException {
        File file = new File(filePath);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        while ((st = br.readLine()) != null) {
            String[] words = st.split(" ");
            pairs.add(new Pair(words[0], words[1]));
            names.addAll(Arrays.asList(words));
        }
    }
    public static void main(String[] args) {
        openFrame();
        //drawGraph();
    }
    public static void openFrame() {
/*
        final int[] num = {0};
        Vector<String> tempNames = new Vector<String>();
        JFrame frame = new JFrame("Medication Interaction");
        JLabel label = new JLabel("Enter number of medication");
        frame.getContentPane().add(label);
        label.setBounds(50, 60, 200, 30);
        JButton button = new JButton("Enter");
        frame.getContentPane().add(button);
        button.setBounds(250, 100, 80, 30);
        final JTextField medNum;
        medNum = new JTextField();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                num[0] = Integer.parseInt(medNum.getText());
            }
        });
*/
//        num = Integer.parseInt(medNum.getText());

//        int num = Integer.parseInt(numMed.getText());
//
////        medNum.setBounds(50, 100, 200, 30);
////        frame.add(medNum);
//        for (int i = 0; i < num; i++) {
////            label.setText("Enter name of medication " + (i + 1));
//            names.add(label.getText());
//        }
//        frame.setSize(400, 400);
//        frame.setLayout(null);
//        frame.setVisible(true);
//        JLabel l = new JLabel("text");
//        frame.getContentPane().add(l);
//
//        JButton button1 = new JButton("Press");
//        frame.getContentPane().add(button1);
//        frame.setVisible(true);
    }

    public void draw() throws SAXException, ParserConfigurationException, ParseException, IOException {
        Set<String> nameSet = new HashSet<>();
        nameSet.addAll(names);
        Data d = new Data(nameSet);
        pairs.addAll(d.getInteractions());

        for(Pair p : d.getInteractions()) {
            System.out.println(p.getKey() + " " + p.getValue());
        }

//        for(String n :names)
//            System.out.println(n);
        drawGraph();
    }

    public void addMedList() {
        names.add(label.getText().toUpperCase());
//.substring(0,1).toUpperCase() + label.getText().substring()
    }

    public void drawGraph() {
        JFrame jFrame = new JFrame();
        jFrame.add(new MedTreeGraph());
        jFrame.setSize(500, 500);
        jFrame.setVisible(true);
        paintComponent(jFrame.getGraphics());

    }
    //    @Override
//    public void actionPerformed(ActionEvent arg0) {
//        medNum.getText();
//
//    }
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}