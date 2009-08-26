package com.steinlink.ponyleague.applet;

import com.steinlink.ponyleague.applet.panel.InstrumentPanel;
import com.steinlink.ponyleague.applet.panel.PlayerPanel;

import javax.sound.midi.Sequencer;
import java.util.List;

/**
 * The LiveControllerThread class is a thread designed to poll the sequencer for play information
 * and the various panels for user input, and to update each accordingly.
 * User: steinlink
 * Date: Sep 1, 2007
 * Time: 2:49:33 AM
 * To change this template use File | Settings | File Templates.
 */
public class LiveControllerThread extends Thread {

    private boolean running = true;
    private PlayerPanel playerPanel;
    private List<InstrumentPanel> instrumentPanels;
    private Sequencer sequencer;
    private float tempo;

    /**
     * Constructor requires a sequencer and various panels
     * @param panel
     * @param seq
     */
    public LiveControllerThread(List<InstrumentPanel> panels, PlayerPanel panel, Sequencer seq, float temp) {
        playerPanel = panel;
        instrumentPanels = panels;
        sequencer = seq;
        tempo = temp;
    }

    /**
     * The run method overrides the super class run emthod, and is called asynchronously from the
     * start method of the superclass.  It is designed to loop until the applet shuts down, which
     * is controlled from the running boolean;  until then, it uses Thread.sleep() to wait between polls
     * and minimize resource consumption.
     */
    public void run() {

        long length = sequencer.getTickLength();

        while (running) {

            // This will always wok if the sequence is valid, TickPosition < TickLength
            long currentPosition = sequencer.getTickPosition();

            // map tick ratio to [0, 100]
            playerPanel.getPositionSlider().setValue(new Long((currentPosition * 100) / length).intValue());

            float tempTempo = sequencer.getTempoInBPM();
            float tempoFactor = tempo / tempTempo;
            sequencer.setTempoFactor(tempoFactor);

            // set the play button state
            if (sequencer.isRunning()) {
                playerPanel.getPlayButton().setText("Stop");
            } else {
                playerPanel.getPlayButton().setText("Play");
            }

            // get the appropriate volumes
            for (int i = 0; i < instrumentPanels.size(); i ++) {
                InstrumentPanel panel = instrumentPanels.get(i);
                sequencer.setTrackMute(i, panel.isMute());
            }

            try {
                Thread.sleep(100);
            } catch (Exception e) {
                running = false;
            }
        }
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
