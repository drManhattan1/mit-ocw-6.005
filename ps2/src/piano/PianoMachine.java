package piano;

import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.MidiUnavailableException;

import midi.Instrument;
import midi.Midi;
import music.NoteEvent;
import music.Pitch;

public class PianoMachine {
	
	private Midi midi;
	
	private Instrument instrument = Midi.DEFAULT_INSTRUMENT;
	
	private int semiTonesShift = 0;
	
	private List<NoteEvent> eventHistory = new ArrayList<NoteEvent>();
	
	private boolean isRecording = false;
    
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
     * @param instr Instrument to use
     * @param rawPitch that will begin to sound using instr
     * Shifts frequency of rawPitch up or down a number of semi-tones set by semiTonesShift 
     */
    public void beginNote(Pitch rawPitch, Instrument instr) {
    	midi.beginNote(rawPitch.transpose(semiTonesShift).toMidiFrequency(),instr);
    }
    
    /**
     * @param rawPitch that will stop sounding the current instrument
     * Shifts frequency of rawPitch up or down a number of semi-tones set by semiTonesShift 
     */
    public void endNote(Pitch rawPitch) {
    	midi.endNote(rawPitch.transpose(semiTonesShift).toMidiFrequency(),instrument);
    }
    
    /**
     * @param instr that will stop playing note
     * @param rawPitch that will stop sounding instr
     * Shifts frequency of rawPitch up or down a number of semi-tones set by semiTonesShift 
     */
    public void endNote(Pitch rawPitch, Instrument instr) {
    	midi.endNote(rawPitch.transpose(semiTonesShift).toMidiFrequency(),instr);
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
    
    /**
     * Toggles the recording of the midi device
     * @return true if recording is turned on when method returns, otherwise returns false
     */
    public boolean toggleRecording() {
    	if(!isRecording){ //starting to record, clear evenHistory
    		eventHistory = new ArrayList<NoteEvent>();
    	}else{
    		eventHistory.add(new NoteEvent(new Pitch('C'), System.currentTimeMillis(),instrument,NoteEvent.Kind.stop));  //add 'dummy' final noteEvent to represent end of recording
    	}
    	
    	isRecording = !isRecording;
    	return isRecording;
    }
    
    /**
     * Plays back each note that has been played since recording was switched on via toggleRecording()
     */
    protected void playback() {    	
        for(int i=0; i<eventHistory.size()-1; i++){
        	NoteEvent event = eventHistory.get(i);
        	NoteEvent nextEvent = eventHistory.get(i+1);
        	long waitInterval = nextEvent.getTime() - event.getTime();
        	playNoteEvent(event, waitInterval);
        }
    }
    
    
    private void playNoteEvent(NoteEvent ne, long waitMillis){
    	if(ne.getKind() == NoteEvent.Kind.start){
    		beginNote(ne.getPitch(), ne.getInstr());
    	}else{
    		endNote(ne.getPitch(), ne.getInstr());
    	}
    	
    	try{
    		midi.wait(waitMillis);
    	}catch(InterruptedException ie){
    		//do nothing
    	}
    }

}
