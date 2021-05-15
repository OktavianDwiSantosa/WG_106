package file;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileReader;
import java.io.FileWriter;

public class Main {

    public static void main(String[] args) {

        final String inputFilePath = "C:\\Users\\ods09\\Documents\\WhiteGate\\Test_File.txt";

        JFrame jFrame = new JFrame("Load ,Edit and Save file");

        Container content = jFrame.getContentPane();

        final JEditorPane edPane = new JEditorPane();

        JScrollPane sPne = new JScrollPane(edPane);

        content.add(sPne, BorderLayout.CENTER);

        edPane.setEditorKit(new HTMLEditorKit());

        JPanel jPanel = new JPanel();

        Action Load = new AbstractAction() {

            @Override

            public void actionPerformed(ActionEvent event) {

                try {

                    load(edPane, inputFilePath);

                } catch (Exception e1) {

                    e1.printStackTrace();

                }

            }

        };

        Load.putValue(Action.NAME, "Load");

        JButton loadButton = new JButton(Load);

        jPanel.add(loadButton);

        Action absActionSave = new AbstractAction() {

            @Override

            public void actionPerformed(ActionEvent event) {

                try {

                    save(edPane, inputFilePath);

                } catch (Exception e1) {

                    e1.printStackTrace();

                }

            }

        };

        absActionSave.putValue(Action.NAME, "Save");

        JButton jButton = new JButton(absActionSave);

        jPanel.add(jButton);

        Action absActionClear = new AbstractAction() {

            @Override

            public void actionPerformed(ActionEvent event) {

                edPane.setText("");

            }

        };

        absActionClear.putValue(Action.NAME, "Clear");

        JButton clearButton = new JButton(absActionClear);

        jPanel.add(clearButton);

        content.add(jPanel, BorderLayout.SOUTH);

        jFrame.setSize(800, 600);

        jFrame.setVisible(true);
    }

    public static void save(JTextComponent text, String inputFile) throws Exception {

        FileWriter writer;

        writer = new FileWriter(inputFile);

        text.write(writer);

        writer.close();
    }

    public static void load(JTextComponent text, String inputFile) throws Exception {

        FileReader inputReader;

        inputReader = new FileReader(inputFile);

        text.read(inputReader, inputFile);

        inputReader.close();

    }
}

