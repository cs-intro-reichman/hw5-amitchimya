/**
 * A library of string functions.
 */
public class MyString {
    // the frequncy of the letters in english, the CDF of their appierence, and all the letters themself
	static final double[] FREQ_LETTER = {0.085, 0.02, 0.045, 0.034, 0.111, 0.018, 0.025, 0.03, 0.075, 0.004, 0.011, 0.054, 0.03, 0.066, 0.072, 0.032, 0.002, 0.075, 0.057, 0.07, 0.036, 0.01, 0.012, 0.005, 0.017, 0.004 };
    static final double [] CDF = CDF(FREQ_LETTER);
    static final char [] letters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j','k','l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    public static void main(String args[]) {
        String hello = "hello";
        System.out.println(countChar(hello, 'h'));
        System.out.println(countChar(hello, 'l'));
        System.out.println(countChar(hello, 'z'));
        System.out.println(spacedString(hello));
        //// Put your other tests here.
        System.out.println(subsetOf("sap","space")); //returns true
        System.out.println(subsetOf("spa","space")); //returns false
        System.out.println(subsetOf("pass","space")); //returns false
        System.out.println(subsetOf("e","space")); //returns true

        System.out.println(randomStringOfLetters(5));
        System.out.println(randomStringOfLetters(10));
        System.out.println(randomStringOfLetters(20));

        System.out.println(remove("space", "spa"));
        System.out.println(remove("space", "c"));
        System.out.println(remove("sppace", "pa"));

        System.out.println(insertRandomly('c', hello));
        System.out.println(insertRandomly('c', hello));
        System.out.println(insertRandomly('c', hello));
        System.out.println(insertRandomly('c', hello));
    }

    /**
     * Returns the number of times the given character appears in the given string.
     * Example: countChar("Center",'e') returns 2 and countChar("Center",'c') returns 0. 
     * 
     * @param str - a string
     * @param c - a character
     * @return the number of times c appears in str
     */
    public static int countChar(String str, char ch) {
        int count = 0;
        for (int i=0; i<str.length(); i++) {
            if (str.charAt(i)==ch) {
                count++;
            }
        }
        return count;
    }

    /** Returns true if str1 is a subset string str2, false otherwise
     *  Examples:
     *  subsetOf("sap","space") returns true
     *  subsetOf("spa","space") returns false
     *  subsetOf("pass","space") returns false
     *  subsetOf("c","space") returns true
     *
     * @param str1 - a string
     * @param str2 - a string
     * @return true is str1 is a subset of str2, false otherwise
     */
    public static boolean subsetOf(String str1, String str2) {
        if (str1=="") {
            return true;
        }
        if (str2=="") {
            return false;
        }
        for (int i=0; i<str1.length(); i++) {
            if (str2.length()<1) {
                return false;
            }
            boolean found = false;
            for (int j=0; j<str2.length(); j++) {
                if (str1.charAt(i)==str2.charAt(j)) {
                    found = true;
                    if (j==0) {
                        str2 = str2.substring(1);
                        break;
                    }
                    else if (j==str2.length()-1) {
                        str2 = str2.substring(0, str2.length()-1);
                        break;
                    }
                    else {
                        str2 = str2.substring(0,j) + str2.substring(j+1);
                        break;
                    } 
                }
            }
            if (!found) return false;
        }
        return true;
    
    }


    /////////////////Something is woring with the last function!!!!!!!

    /** Returns a string which is the same as the given string, with a space
     * character inserted after each character in the given string, except
     * for the last character. 
     * Example: spacedString("silent") returns "s i l e n t"
     * 
     * @param str - a string
     * @return a string consisting of the characters of str, separated by spaces.
     */
    public static String spacedString(String str) {
        if (str.length()<=1) {
            return str;
        }
        String newString = str.charAt(0) + " ";
        for (int i=1; i<str.length()-1; i++) {
            newString = newString + str.charAt(i) + " ";
        }
        newString= newString + str.charAt(str.length()-1);
        return newString;
    }
  
    /**
     * Returns a string of n lowercase letters, selected randomly from 
     * the English alphabet 'a', 'b', 'c', ..., 'z'. Note that the same
     * letter can be selected more than once.
     * 
     * Example: randomStringOfLetters(3) can return "zoo"
     * 
     * @param n - the number of letter to select
     * @return a randomly generated string, consisting of 'n' lowercase letters
     */
    // I transformed it also to do it randomaly but in the frequncy of the letter in english
    public static String randomStringOfLetters(int n) {
        String newString = "";
        for (int i=0; i<n; i++) {
            char random = letters[rnd(CDF)];
            newString = newString + random;
        }
        return newString;
    }

    /**
     * Returns a string consisting of the string str1, minus all the characters in the
     * string str2. Assumes (without checking) that str2 is a subset of str1.
     * Example: remove("meet","committee") returns "comit" 
     * 
     * @param str1 - a string
     * @param str2 - a string
     * @return a string consisting of str1 minus all the characters of str2
     */
    public static String remove(String str1, String str2) {
       for (int i=0; i<str2.length(); i++) {
        for (int j=0; j<str1.length(); j++) {
            if (str2.charAt(i)==str1.charAt(j)) {
                if (j==0) {
                    str1 = str1.substring(j+1); 
                }
                else {
                    str1 = str1.substring(0,j) + str1.substring(j+1);
                } 
                break; 
            }
        }
       }
        return str1;
    }

    /**
     * Returns a string consisting of the given string, with the given 
     * character inserted randomly somewhere in the string.
     * For example, insertRandomly("s","cat") can return "scat", or "csat", or "cast", or "cats".  
     * @param ch - a character
     * @param str - a string
     * @return a string consisting of str with ch inserted somewhere
     */
    public static String insertRandomly(char ch, String str) {
         // Generate a random index between 0 and str.length()
         int randomIndex = (int) (Math.random() * (str.length() + 1));
         // Insert the character at the random index
         String result = str.substring(0, randomIndex) + ch + str.substring(randomIndex);
         return result;
    }    

    public static String lowerCase(String str) {
        String newStr = "";
        for (int i=0; i<str.length(); i++) {
            if (str.charAt(i)>='A' && str.charAt(i)<='Z') {
                newStr= newStr+ (char)(str.charAt(i)+32);
            }
            else newStr=newStr+str.charAt(i);
        }

        return newStr;
    }

    public static double[] CDF (double[] p) {
		double[] P = new double[p.length];
		P[0] = p[0];
		for (int i=1; i<p.length; i++) {
			P[i] = P[i-1] + p[i];
		}
		return P;
	}

	public static int rnd(double[] P) {
		double r=Math.random();
		for (int i=0; i<P.length; i++) {
			if (r<=P[i]) return i;
		}
		return 0;
	}
}
