package com.steinlink.ponyleague.applet.panel;

import com.steinlink.ponyleague.applet.MidiApplet;

import javax.swing.*;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: steinlink
 * Date: Sep 1, 2007
 * Time: 2:01:41 AM
 * To change this template use File | Settings | File Templates.
 */
public class PlayerPanel extends JPanel {

    private JSlider positionSlider;
    private JButton playButton;

    public PlayerPanel(MidiApplet applet) {

        this.setBackground(Color.BLACK); //new Color(44, 169, 173));

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setPreferredSize(new Dimension(450, 25));
        this.setBounds(new Rectangle(0, 0, 450, 25));

        playButton = new JButton("Stop");
        playButton.setActionCommand("STOP");
        playButton.setBackground(Color.BLACK); //new Color(44, 169, 173));
        playButton.addActionListener(applet);
        this.add(playButton, new GridBagConstraints());

        positionSlider = new JSlider();
        positionSlider.setPreferredSize(new Dimension(300, 10));
        positionSlider.setMaximum(100);
        positionSlider.setMinimum(0);
        this.add(positionSlider);
    }

    public JSlider getPositionSlider() {
        return positionSlider;
    }

    public JButton getPlayButton() {
        return playButton;
    }
}
