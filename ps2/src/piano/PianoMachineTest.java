package piano;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.sound.midi.MidiUnavailableException;

import midi.Midi;
import music.Pitch;

import org.junit.Test;

public class PianoMachineTest {
	
    @Test
    public void singleNoteTest() throws MidiUnavailableException {
    	PianoMachine pm = new PianoMachine();
        String expected0 = "on(61,PIANO) wait(100) off(61,PIANO)";
        
    	Midi midi = Midi.getInstance();

    	midi.clearHistory();
    	
        pm.beginNote(new Pitch(1));
		Midi.wait(100);
		pm.endNote(new Pitch(1));

        System.out.println(midi.history());
        assertEquals(expected0,midi.history());
    }
    
    @Test
    public void changeInstrumentTest() throws MidiUnavailableException{
    	PianoMachine pm = new PianoMachine();
    	String expected0 = "on(61,BRIGHT_PIANO) wait(100) off(61,BRIGHT_PIANO)";
        
    	Midi midi = Midi.getInstance();

    	midi.clearHistory();
    	
    	pm.changeInstrument();
        pm.beginNote(new Pitch(1));
		Midi.wait(100);
		pm.endNote(new Pitch(1));

        System.out.println(midi.history());
        assertEquals(expected0,midi.history());
    }
    
    @Test
    public void shiftUpTest() throws MidiUnavailableException{
    	PianoMachine pm = new PianoMachine();
    	String expected0 = "on(73,PIANO) wait(100) off(73,PIANO)";
        
    	Midi midi = Midi.getInstance();

    	midi.clearHistory();
    	
    	pm.shiftUp();
        pm.beginNote(new Pitch(1));
		Midi.wait(100);
		pm.endNote(new Pitch(1));

        System.out.println(midi.history());
        assertEquals(expected0,midi.history());
    }
    
    @Test
    public void shiftUpEdgeTest() throws MidiUnavailableException{
    	PianoMachine pm = new PianoMachine();
    	String expected0 = "on(85,PIANO) wait(100) off(85,PIANO)";
        
    	Midi midi = Midi.getInstance();

    	midi.clearHistory();
    	
    	for(int i=0; i<3; i++) //shift up three octaves
    		pm.shiftUp();
    	
        pm.beginNote(new Pitch(1));
		Midi.wait(100);
		pm.endNote(new Pitch(1));

        System.out.println(midi.history());
        assertEquals(expected0,midi.history());
    }
    
    @Test
    public void shiftDownTest() throws MidiUnavailableException{
    	PianoMachine pm = new PianoMachine();
    	String expected0 = "on(49,PIANO) wait(100) off(49,PIANO)";
        
    	Midi midi = Midi.getInstance();

    	midi.clearHistory();
    	
    	pm.shiftDown();
        pm.beginNote(new Pitch(1));
		Midi.wait(100);
		pm.endNote(new Pitch(1));

        System.out.println(midi.history());
        assertEquals(expected0,midi.history());
    }
    
    @Test
    public void shiftDownEdgeTest() throws MidiUnavailableException{
    	PianoMachine pm = new PianoMachine();
    	String expected0 = "on(37,PIANO) wait(100) off(37,PIANO)";
        
    	Midi midi = Midi.getInstance();

    	midi.clearHistory();
    	
    	for(int i=0; i<3; i++) //shift down three times
    		pm.shiftDown();
    			
        pm.beginNote(new Pitch(1));
		Midi.wait(100);
		pm.endNote(new Pitch(1));

        System.out.println(midi.history());
        assertEquals(expected0,midi.history());
    }

}
