package edu.yale.cpsc112_lesson1;

public class Circles {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static String parsePhoneNumber (String s) {
		String n0 ="";
		int count = 0;
	      
	      for (int i = 0; i < s.length();i++) {
	    	  if (s.charAt(i) == ',' ) {
	    		  count++;
	    	  } 
	      }
	      n0 = s.substring(s.charAt(1), s.indexOf('='));
	      n0 = n0+s.substring(s.indexOf(','), s.indexOf('='));
	      for (int j =0; j <= count; j++) {
	    	  s = s.substring(s.indexOf(',' + 1), s.length());
	    	  n0 = n0+s.substring(s.indexOf(','), s.indexOf('='));
	      }
		
		
	return n0;
	}

}
