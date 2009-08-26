package com.steinlink.ponyleague.common.util;

import javax.sound.midi.Sequence;
import javax.sound.midi.MidiSystem;
import java.net.URL;
import java.io.InputStream;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: steinlink
 * Date: Aug 30, 2007
 * Time: 6:56:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class ServerUtil {

    /**
     * Waits and polls until a song is found
     * @param key
     */
    public static Sequence getServerSequence(URL key) {

        // TODO render loading page

        // poll for a song
        boolean ready = false;
        InputStream stream = null;

        Sequence sequence = null;
        while(!ready) {

            try {

                try {
                    stream = key.openStream();
                    int size = stream.available();

                    if (size > 0) {

                     //   stream.read(bytes);
                        sequence = MidiSystem.getSequence(stream);
                    }
                } catch (Exception e) {
                    return null;
                }
            } finally {
                try {
                    stream.close();
                } catch (IOException e) {

                    // swallow this error

                }
            }

            if (sequence != null) {
                ready = true;
            } else {
                try {
                    Thread.sleep(6000);
                } catch (InterruptedException ex) {

                }
            }
        }

        return sequence;
    }
}
