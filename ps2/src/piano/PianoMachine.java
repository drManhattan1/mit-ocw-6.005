package piano;

import javax.sound.midi.MidiUnavailableException;

import midi.Instrument;
import midi.Midi;
import music.Pitch;

public class PianoMachine {
	
	private Midi midi;
	
	private Instrument instrument = Midi.DEFAULT_INSTRUMENT;
	
	private int semiTonesShift = 0;
    
	/**
	 * constructor for PianoMachine.
	 * 
	 * initialize midi device and any other state that we're storing.
	 */
    public PianoMachine() {
    	try {
            midi = Midi.getInstance();
        } catch (MidiUnavailableException e1) {
            System.err.println("Could not initialize midi device");
            e1.printStackTrace();
            return;
        }
    }
    
    /**
     * @param rawPitch that will begin to sound using current instrument
     * Shifts frequency of rawPitch up or down a number of semi-tones set by semiTonesShift 
     */
    public void beginNote(Pitch rawPitch) {
    	midi.beginNote(rawPitch.transpose(semiTonesShift).toMidiFrequency(),instrument);
    }
    
    /**
     * @param rawPitch that will stop sounding the current instrument
     * Shifts frequency of rawPitch up or down a number of semi-tones set by semiTonesShift 
     */
    public void endNote(Pitch rawPitch) {
    	midi.endNote(rawPitch.transpose(semiTonesShift).toMidiFrequency(),instrument);
    }
    
    /**
     * sets instrument to next instrument in Instrument enum class
     */
    public void changeInstrument() {
       	instrument = instrument.next();
    }
    
    /**
     * shifts octave, up to two octaves up.
     */
    public void shiftUp() {
    	if(semiTonesShift != 24)
    		semiTonesShift += 12;
    }
    
    /**
     * shifts octave, up to two octaves down.
     */
    public void shiftDown() {
    	if(semiTonesShift != -24)
    		semiTonesShift -= 12;
    }
    
    //TODO write method spec
    public boolean toggleRecording() {
    	return false;
    	//TODO: implement for question 4
    }
    
    //TODO write method spec
    protected void playback() {    	
        //TODO: implement for question 4
    }

}
