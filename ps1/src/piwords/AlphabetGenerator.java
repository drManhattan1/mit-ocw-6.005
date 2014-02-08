package piwords;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class AlphabetGenerator {
	
	 private static final char[] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};

    /**
     * Given a numeric base, return a char[] that maps every digit that is
     * representable in that base to a lower-case char.
     * 
     * This method will try to weight each character of the alphabet
     * proportional to their occurrence in words in a training set.
     * 
     * This method should do the following to generate an alphabet:
     *   1. Count the occurrence of each character a-z in trainingData.
     *   2. Compute the probability of each character a-z by taking
     *      (occurrence / total_num_characters).
     *   3. The output generated in step (2) is a PDF of the characters in the
     *      training set. Convert this PDF into a CDF for each character.
     *   4. Multiply the CDF value of each character by the base we are
     *      converting into.
     *   5. For each index 0 <= i < base,
     *      output[i] = (the first character whose CDF * base is > i)
     * 
     * A concrete example:
     * 	 0. Input = {"aaaaa..." (302 "a"s), "bbbbb..." (500 "b"s),
     *               "ccccc..." (198 "c"s)}, base = 93
     *   1. Count(a) = 302, Count(b) = 500, Count(c) = 193
     *   2. Pr(a) = 302 / 1000 = .302, Pr(b) = 500 / 1000 = .5,
     *      Pr(c) = 198 / 1000 = .198
     *   3. CDF(a) = .302, CDF(b) = .802, CDF(c) = 1
     *   4. CDF(a) * base = 28.086, CDF(b) * base = 74.586, CDF(c) * base = 93
     *   5. Output = {"a", "a", ... (28 As, indexes 0-27),
     *                "b", "b", ... (47 Bs, indexes 28-74),
     *                "c", "c", ... (18 Cs, indexes 75-92)}
     * 
     * The letters should occur in lexicographically ascending order in the
     * returned array.
     *   - {"a", "b", "c", "c", "d"} is a valid output.
     *   - {"b", "c", "c", "d", "a"} is not.
     *   
     * If base >= 0, the returned array should have length equal to the size of
     * the base.
     * 
     * If base < 0, return null.
     * 
     * If a String of trainingData has any characters outside the range a-z,
     * ignore those characters and continue.
     * 
     * 
     * @param base A numeric base to get an alphabet for.
     * @param trainingData The training data from which to generate frequency
     *                     counts. This array is not mutated.
     * @return A char[] that maps every digit of the base to a char that the
     *         digit should be translated into.
     */
	public static char[] generateFrequencyAlphabet(int base,
                                                   String[] trainingData) {
    	//check input parameters
    	if(base<0)
    		return null;
    	
    	if(base==0 || trainingData.length==0)
    		return new char[0];
    	
    	int totalNumChars = 0;
    	Map<Character,Double> charFrequencies = getZeroCharFrequencies();
    	
    	for(String str : trainingData){
    		str = str.toLowerCase();
    		
    		for(int i=0; i<str.length(); i++){
    			if(str.substring(i,i+1).matches("[a-z]")){ //note use of substring to get character, so I can use matches() function
    				charFrequencies.put(str.charAt(i), charFrequencies.get(str.charAt(i))+1);
    				totalNumChars++;
    			}
    		}
    	}
    	
    	if(totalNumChars==0)
    		return new char[0];
    	
    	return convertToCDF(charFrequencies,totalNumChars,base);
    }
    
    //converts map of character frequencies to frequency alphabet
    private static char[] convertToCDF(Map<Character, Double> fMap, int totalNumChars, int base){
    	for(Entry<Character,Double> e : fMap.entrySet()){
    		fMap.put(e.getKey(), e.getValue()/(double)totalNumChars);
    	}
    	
    	double carry = 0;
    	for(char c : alphabet){
    		Double pr = fMap.get(c);
    		pr+=carry;
    		fMap.put(c, pr);
    		carry=pr;
    	}
    	
    	char[] fAlphabet = new char[base];
    	int startCopyIdx = 0;
    	int endCopyIdx = 0;
    	for(char c : alphabet){
    		
    		endCopyIdx = (int)Math.round(fMap.get(c)*base) - 1;
    		
    		//copy c from start index to end index
    		for(int i=startCopyIdx;i<=endCopyIdx;i++){
    			fAlphabet[i] = c;
    		}
    		
    		startCopyIdx = endCopyIdx + 1;
    	}
    	
    	return fAlphabet;
    }
    
    //creates a map with k = character and v = 0 for all characaters in alphabet
    private static Map<Character, Double> getZeroCharFrequencies(){
    	Map<Character,Double> charFrequencies = new HashMap<Character,Double>();
    	for(char c : alphabet){
    		charFrequencies.put(c, 0.0);
    	}
    	return charFrequencies;
    }
}
