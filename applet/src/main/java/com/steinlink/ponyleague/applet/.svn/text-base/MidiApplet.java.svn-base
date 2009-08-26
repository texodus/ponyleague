package com.steinlink.ponyleague.applet;

import com.steinlink.ponyleague.common.util.MidiUtil;
import com.steinlink.ponyleague.common.util.ServerUtil;
import com.steinlink.ponyleague.applet.panel.PlayerPanel;
import com.steinlink.ponyleague.applet.panel.InstrumentPanel;

import javax.swing.*;
import javax.sound.midi.Sequence;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiDevice;
import javax.sound.midi.Sequencer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;
import java.net.URL;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: steinlink
 * Date: Apr 8, 2007
 * Time: 12:08:09 AM
 * To change this template use File | Settings | File Templates.
 */
public class MidiApplet extends JApplet implements ActionListener {

    private JPanel panel;
    private Sequence sequence;
    private Sequencer sequencer;
    private PlayerPanel playerPanel;
    private LiveControllerThread controllerThread;
    private List<InstrumentPanel> instrumentPanels;
    private boolean debug = true;

    private float tempo = 50;
    private MidiDevice.Info info;

    /**
     * initialize the applet
     */
    public void init() {

        // initialize the layout, color, etc
        panel = new JPanel();

        panel.setDoubleBuffered(true);
        BoxLayout box = new BoxLayout(panel, BoxLayout.Y_AXIS);
        this.setBackground(Color.BLACK);//new Color(44, 169, 173));                 
        panel.setBackground(Color.BLACK); //new Color(44, 169, 173));
        panel.setLayout(box);

        // check the input parameters and render the page
        try {

            String host = getCodeBase().getHost() + ":" + getCodeBase().getPort() + getCodeBase().getPath();
            String songID = getParameter("songID");

            if (host == "") {
                host = "http://localhost:8080";
            }

            host = "http://" + host + "t.do?songID=" + songID;


            // get the song from the server
            URL url = new URL(host);
            sequence = ServerUtil.getServerSequence(url);
            if (debug) {
                for (int i = 0; i < sequence.getTracks().length; i ++) {
                    for (int j = 0; j < sequence.getTracks()[i].size(); j ++) {
                        System.out.println(sequence.getTracks()[i].get(j).getTick() + " : " + sequence.getTracks()[i].get(j).getMessage());
                    }
                }
            }

            JFrame frame = new JFrame();
            frame.setEnabled(true);

            // debug info
            /*
            JLabel label = new JLabel();
            label.setText(host);
            label.setForeground(Color.WHITE);
                    
            panel.add(label);
          /*
            label = new JLabel();
            label.setText(songID);
            label.setForeground(Color.WHITE);

            panel.add(label);          */

            // render the instruments
            MidiAppletWorkspace space = new MidiAppletWorkspace(sequence);
            panel.add(space);
            instrumentPanels = space.getPanels();

            playerPanel = new PlayerPanel(this);
            panel.add(playerPanel);

            // add the panel to the applet
            this.add(panel);

            // Start playing the song, and we're done;  there is nothing more to do until we receive
            // a command from the user
            info = MidiSystem.getMidiDeviceInfo()[1];

            sequencer = MidiUtil.play(sequence, info, tempo);
            sequencer.start();

            controllerThread = new LiveControllerThread(instrumentPanels, playerPanel, sequencer, tempo);
            controllerThread.start();
            
        } catch (Exception e) {
            error(e.getMessage());
        }
    }

    public void destroy() {
        sequencer.stop();
        sequencer.close();
        controllerThread.setRunning(false);
    }

    /**
     * Stops the applet;  when the applet stops, stop playing music so the thread dies and the user
     * maintains their sanity.
     */
    public void stop() {
        if (sequencer != null) {
             sequencer.stop();
        }
        controllerThread.setRunning(false);
    }

    public void start() {
    	if (sequencer != null) {
             sequencer.start();
        }
        controllerThread.setRunning(true);
    }

    /**
     * Prints an error message to the applet for user digestion
     * @param ex
     */
    private void error(String ex) {
        // TODO render error page and exit
        panel.add(new JLabel("ERROR LOADING SONG; " + ex));
        this.add(panel);

    }

    /**
     * Handles any user action
     * @param event
     */
    public void actionPerformed(ActionEvent event) {
       if (event.getActionCommand() == playerPanel.getPlayButton().getActionCommand()) {
           if (!sequencer.isRunning()) {

               // if we are at the end of the sequencer, start over
               long tickPosition = sequencer.getTickPosition();

               if (tickPosition == sequencer.getTickLength()) {
                   sequencer.setTickPosition(0);
               }

               float tempTempo = sequencer.getTempoInBPM();
               float tempoFactor = tempo / tempTempo;
               sequencer.setTempoFactor(tempoFactor);
               
               sequencer.start();
           } else {
               sequencer.stop();
           }
       }
    }
}
