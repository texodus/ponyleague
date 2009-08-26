package com.steinlink.ponyleague.applet;

import com.steinlink.ponyleague.applet.panel.InstrumentPanel;

import javax.sound.midi.Sequence;
import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Shell class used to generate a panel containing an InstrumentPanel for each sequence track
 * User: steinlink
 * Date: Apr 8, 2007
 * Time: 1:14:32 AM
 * To change this template use File | Settings | File Templates.
 */
public class MidiAppletWorkspace extends JPanel {

    List<InstrumentPanel> panels = new LinkedList<InstrumentPanel>();
    
    /**
     * Constructor needs a sequence
     * @param sequence
     */
    public MidiAppletWorkspace(Sequence sequence) {

        super();
        this.setPreferredSize(new Dimension(450, 200));
        this.setLayout(new GridLayout(1,4,0,25));

        try{

            for (int i = 0; i < sequence.getTracks().length; i++) {

                InstrumentPanel panel = new InstrumentPanel("");
                panels.add(panel);
                this.add(panel);
            }

        } catch (Exception e) {
            // TODO error the applet
        }
    }

    /**
     * Returns a list of the panels rendered
     * @return
     */
    public List<InstrumentPanel> getPanels() {
        return panels;
    }
}
