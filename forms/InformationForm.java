package com.company.forms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InformationForm{

    final static JFrame FRAME = new JFrame();

    public static void createInformationForm(String informName, String information) {

        FRAME.setName(informName);
        FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FRAME.setResizable(false);
        FRAME.setSize(300,100);

        createUI(FRAME.getContentPane(), information);

        FRAME.setVisible(true);

    }

    private static void createUI(Container container, String information) {
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        JTextPane informationArea = new JTextPane();
        informationArea.setText(information);
        informationArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        informationArea.setEditable(false);
        container.add(informationArea);

        JButton button = new JButton("OK");
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FRAME.dispose();
            }
        });
        container.add(button);
    }
}
