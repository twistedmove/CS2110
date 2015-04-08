public class E {

    /** String-loop question 1 
     * Return the number of occurrences of c in s */
    public static int occurrences(char c, String s) {
        int count = 0;
    	for (int x=0; x < s.length(); x++){
        	if (s.charAt(x) == c){
        		count++;
        	}
    	}        
        return count;
    	    }


    /* String-loop question 2 
     * Return a copy of the parameter s but with all instance of ‘d’ duplicated. 
     * For example, dupD(“adopted”) equals “addoptedd”. */

    public static String dupD(String s){
    	String word = "";
    	for (int x=0; x < s.length(); x++){
    		if (s.charAt(x) == 'd'){
    			word = word + 'd' + 'd';
    		}
    		word = word + s.charAt(x);
    	}
    	return word;
    }


    /* String-loop question 3*/ 
    /** Returns a copy of s with no blanks at the beginning and end, exactly one blank between adjacent 
words, and all words capitalized.*/

    public static String fixCap(String s){
    	s.trim();
    	for (int x=0; x < s.length(); x++){
    		if ((s.charAt(x) == ' ') && (s.charAt(x+1) == ' ')){
    			s = s.substring(0,x) + s.substring(x+1);
    		}
    	}
    	return s.toUpperCase();
    	}
    


    /*String-loop question 4*/ 
    /**Returns s with the same names but in the form “last, first” and with only 1 blank 
after each “;”.*/

    public static String fixNames(String s){
    	String a = "";
    	for (int x=0; x < s.length(); x++){
    		if (s.charAt(x) != ' ' && s.charAt(x) != ';'){
    		a = a + s.charAt(x);
    		}
    		if (s.charAt(x) == ' '){
    			a = a + ';';
    		}
    		if (s.charAt(x) == ';'){
    			a = a + ' ';
    		}
    	}
    	return a;
    		}

    /* String-loop question 5*/
    /** Returns time s in 12-hour time.*/
    public static String make12hr(String s){
    	String hour = s.substring(0,s.indexOf(':'));
    	String minute = s.substring(s.indexOf(':')+1); 
    	if (hour.length() < 2){
    		hour = '0' + hour;
    	}
    	if ((hour.charAt(1) == '2') && (hour.length()==2)){
    		hour = '1' + hour.substring(1);
    	}
        if (minute.length() < 2){
        	minute = '0' + minute;
        }
        return hour+':'+minute;
        
    }


    /* String-loop question 6 */
    /** Returns its parameter s but with “_” inserted before and after each occurrence of ‘a’. 
    For example, if s is “anna”, the answer is “_a_nn_a_”. */
    
    public static String fixA(String s){
    	for (int x=0; x < s.length(); x++){
    		if (s.charAt(x) == 'a'){
    			s = s.substring(0,x) + '_' + s.substring(x,x+1) + '_' + s.substring(x+1);
    		}
    	}
    	return s;
    }


    /** Return the index of the first occurrence of x in s (-1 f it is not in) */
    public static int find(String x, String s) {
        int h= x.length();
        for (int k= 0; k+h <= s.length(); k= k+1) {
            if (x.equals(s.substring(k, k+h))) {
                return k;
            }
        }
        return -1;
    }

}
