package piwords;

import static org.junit.Assert.*;

import org.junit.Test;

public class PiGeneratorTest {
    @Test
    public void basicPowerModTest() {
        // 5^7 mod 23 = 17
        assertEquals(17, PiGenerator.powerMod(5, 7, 23));
    }

    @Test
    public void basicComputePiInHexTest(){
    	assertNull(PiGenerator.computePiInHex(-1));
    	assertArrayEquals(new int[]{2,4,3,15,6},PiGenerator.computePiInHex(5));
    }
}
