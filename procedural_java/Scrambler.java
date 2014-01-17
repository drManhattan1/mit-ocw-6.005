class Scrambler {

    static String reverse(String input) {
        // YOUR CODE HERE
        return null;
    }    
    
	static String reverseWords(String input) {
        // YOUR CODE HERE
		return null;
	}

    static boolean isWordPalindrome(String input) {
        // YOUR CODE HERE
        return false;
    }
    
	public static void main(String args[]) {
        String input, output;
        
        // reverse
        input = "Three blind mice";
        output = reverse(input);
        System.out.println("Should be: ecim dnilb eerhT");
        System.out.println("Actual is: " + output);

        input = "level madam level";
        output = reverse(input);
        System.out.println("\nShould be: level madam level");
        System.out.println("Actual is: " + output);
        
        // reverseWords
		input = "To be or not to be, that is the question.";
		output = reverseWords(input);
        System.out.println("\nShould be: question. the is that be, to not or be To");
        System.out.println("Actual is: " + output);

		input = "Stressed spelled backwards is Desserts";
		output = reverseWords(input);
        System.out.println("\nShould be: Desserts is backwards spelled Stressed");
        System.out.println("Actual is: " + output);

        // isWordPalindrome
        input = "forever eating cheese";
        boolean boolOutput = isWordPalindrome(input);
        System.out.println("\n" + input + (boolOutput ? " is " : " is not ") + "a word palindrome");

        input = "fall leaves when leaves fall";
        boolOutput = isWordPalindrome(input);
        System.out.println("\n" + input + (boolOutput ? " is " : " is not ") + "a word palindrome");
        
        input = null;
        boolOutput = isWordPalindrome(input);
        System.out.println("\n" + input + (boolOutput ? " is " : " is not ") + "a word palindrome");
        
    }

}
