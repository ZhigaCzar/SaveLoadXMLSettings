package com.company.forms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.io.StringWriter;

public class ErrorForm {

    final static JFrame FRAME = new JFrame();

    public static void createErrorForm(String informName, String information) {

        FRAME.setName(informName);
        FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FRAME.setResizable(false);
        FRAME.setSize(300,300);

        createUI(FRAME.getContentPane(), information);

        FRAME.setVisible(true);

    }

    public static void createErrorForm(String informName, Exception information) {

        FRAME.setName(informName);
        FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //FRAME.setResizable(false);
        FRAME.setSize(300,300);

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        information.printStackTrace(pw);
        String sStackTrace = sw.toString();

        createUI(FRAME.getContentPane(), sStackTrace);

        FRAME.setVisible(true);

    }

    private static void createUI(Container container, String information) {
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));//

        JTextArea errorArea = new JTextArea();
        errorArea.setText(information);
        errorArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        errorArea.setEditable(false);
        errorArea.setSize(300, 270);
        errorArea.setLineWrap(false);

        JScrollPane jsp = new JScrollPane(errorArea,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        container.add(jsp);

        JButton button = new JButton("OK");
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        button.setSize(50, 25);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FRAME.dispose();
            }
        });
        container.add(button);
    }
}
