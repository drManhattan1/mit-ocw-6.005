package piwords;

import static org.junit.Assert.*;

import org.junit.Test;

public class BaseTranslatorTest {
    @Test
    public void basicBaseTranslatorTest() {
        // Expect that .01 in base-2 is .25 in base-10
        // (0 * 1/2^1 + 1 * 1/2^2 = .25)
        int[] input = {0, 1};
        int[] expectedOutput = {2, 5};
        assertArrayEquals(expectedOutput,
                          BaseTranslator.convertBase(input, 2, 10, 2));
    }

    @Test
    //tests cases that return null
    public void convertBaseFailureTest(){
    	assertNull( BaseTranslator.convertBase(new int[]{-1},2,10,5));
    	assertNull( BaseTranslator.convertBase(new int[]{3},2,10,5));
    	assertNull( BaseTranslator.convertBase(new int[]{3},1,10,5));
    	assertNull( BaseTranslator.convertBase(new int[]{3},2,1,5));
    	assertNull( BaseTranslator.convertBase(new int[]{3},2,10,0));
    }
}
