package com.steinlink.ponyleague.applet.panel;

import javax.swing.*;
import javax.sound.midi.MidiUnavailableException;
import java.awt.*;

/**
 * an InstrumentPanel is a JPanel representing a single midi track, and handles volume & effects
 * settings for the track
 * User: steinlink
 * Date: Sep 1, 2007
 * Time: 2:04:46 AM
 * To change this template use File | Settings | File Templates.
 */
public class InstrumentPanel extends JPanel {

    private JSlider volumeSlider;
    private JCheckBox box;

    /**
     * Constructor initializes the panel
     * @param name
     * @throws MidiUnavailableException
     */
    public InstrumentPanel(String name) throws MidiUnavailableException {

        Color backgroundColor = Color.BLACK; //new Color(44, 169, 173);

        GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
		gridBagConstraints3.fill = GridBagConstraints.BOTH;
		gridBagConstraints3.weighty = 1.0D;
		gridBagConstraints3.gridx = 0;
		gridBagConstraints3.gridy = 1;
		gridBagConstraints3.gridwidth = 2;
		gridBagConstraints3.weightx = 1.0;
		JLabel label = new JLabel();
        label.setText("  " + name);
		label.setFont(new Font("Dialog", Font.BOLD, 14));
        label.setForeground(Color.WHITE);
        
		// the Slider
		volumeSlider = new JSlider();
		volumeSlider.setOrientation(JSlider.VERTICAL);
        volumeSlider.setPaintLabels(false);
		volumeSlider.setMajorTickSpacing(20);
		volumeSlider.setMinorTickSpacing(10);
		volumeSlider.setPaintTicks(true);
		volumeSlider.setMaximum(100);
		volumeSlider.setBackground(Color.BLACK);
        volumeSlider.setForeground(Color.WHITE);
        volumeSlider.setValue(20);
        
        // the mute button
        box = new JCheckBox("Mute");
        box.setBackground(backgroundColor);
        box.setForeground(Color.WHITE);
        box.setSelected(false);

        this.setLayout(new GridBagLayout());
		this.setSize(new Dimension(60, 200));
		this.setBackground(backgroundColor);
		this.setBounds(new Rectangle(0, 0, 60, 200));
//		track.add(label, gridBagConstraints2);
		this.add(volumeSlider, gridBagConstraints3);
        this.add(box);
    }

    /**
     * Is the track muted?
     * @return
     */
    public boolean isMute() {
        return box.isSelected();
    }
}
