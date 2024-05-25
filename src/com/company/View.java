package com.company;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class View {

    private JLabel imageLabel;
    private Controller controller;

    public void create(int width, int height) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setUndecorated(true);
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                controller.handleKeyPress(e.getKeyCode());

            }
        });

        imageLabel = new JLabel();
        imageLabel.setBounds(0, 0, width, height);
        frame.add(imageLabel);

        frame.setVisible(true);
    }

    public void setImage(BufferedImage image) {
        imageLabel.setIcon(new ImageIcon(image));
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
}
