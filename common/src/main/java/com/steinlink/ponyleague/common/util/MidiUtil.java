package com.steinlink.ponyleague.common.util;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.Transmitter;

public class MidiUtil {

	public static Sequencer play(Sequence sequence, MidiDevice.Info info, float tempo){

        Sequencer sequencer = null;
        Synthesizer synthesizer;

        // initialize the sequencer and such for playing
  		try{
			sequencer = MidiSystem.getSequencer(false);
			MidiDevice device = MidiSystem.getMidiDevice(info);
			sequencer.getTransmitter().setReceiver(device.getReceiver());
		} catch (MidiUnavailableException e){
			e.printStackTrace();
		}

		if (sequencer == null){
			return null;
		}

		try {
			sequencer.open();
			sequencer.setSequence(sequence);
		} catch (InvalidMidiDataException e){
			e.printStackTrace();
		} catch (MidiUnavailableException e){
			e.printStackTrace();
		}

		if (!(sequencer instanceof Synthesizer)){
			try {
				synthesizer = MidiSystem.getSynthesizer();
				synthesizer.open();

                float tempTempo = sequencer.getTempoInBPM();
                float tempoFactor = tempo / tempTempo;
                sequencer.setTempoFactor(tempoFactor);

                Receiver	synthReceiver = synthesizer.getReceiver();
				Transmitter	seqTransmitter = sequencer.getTransmitter();
				seqTransmitter.setReceiver(synthReceiver);
			} catch (MidiUnavailableException e) {
				e.printStackTrace();
			}
		}
        
        return sequencer;
    }
}