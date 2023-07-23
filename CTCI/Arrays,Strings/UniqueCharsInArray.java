// Find if String has unique chars or not, Restriction-1: Cant use DS



import java.io.*;

class UniqueCharsInArray {
	public static void main (String[] args) {
		String s = "ztbhg";
		System.out.println(isUniqueCharsBitVector(s));
	}

    //Clarifications: Check if its ASCII/Unicode char string. (ASCII is a character encoding system that only includes up to 256 characters, primarily composed of English letters, numbers, and symbols. It uses up to eight bits to represent each character. In contrast, Unicode includes over 149,000 characters)

    //Coding -
    // assumption: its an ascii string.
    // Edge conditions: since ascii has max 128 chars, if >128 return false


    // Approach:1 Loop through array, store each char in new charArr if not already there
	private static boolean isUniqueChars(String str) {
         if (str.length() > 128 ) { return false; }
         
         boolean[] char_set = new boolean[128];
         for (int i=0; i< str.length(); i++) {
             char val = str.charAt(i);
             if (char_set[val]) { return false; }
             char_set[val] = true;
         }
         return true;
	}
	
	// Approach2: Using Bit Vector (assumption is string has only a-z small alphabets because we are considering int with capacity 4bytes=32bits & a-z themselves makes 26)
	// saves space
	private static boolean isUniqueCharsBitVector(String str) {
         if (str.length() > 128 ) { return false; }
         
         int checker = 0;
         for (int i=0; i< str.length(); i++) {
             int val = str.charAt(i) - 'a';
             //System.out.println(val + "value" + Integer.toBinaryString(1<<val));
             if ( (checker & (1 << val)) > 0) { return false; }
             checker = checker | (1 << val);
         }
         return true;
	}

}

