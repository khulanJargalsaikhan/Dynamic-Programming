package memorization_tabulation;

import java.util.ArrayList;

public class AllConstruct {

	public static void main(String[] args) {
		System.out.println(allConstruct("purple", new String[] {"purp", "p", "ur", "le", "purpl"}));
		// 	[["purp", "le"], ["p", "ur", "p", "le"]]
		System.out.println(allConstruct("abcdef", new String[] {"ab", "abc", "cd", "def", "abcd", "ef", "c"}));
		// 	[["ab", "cd", "ef"], ["ab", "c", "def"], ["abc", "def"], ["abcd", "ef"]]
		System.out.println(allConstruct("skateboard", new String[] {"bo", "rd", "ate", "t", "ska", "sk", "boar"}));
		// 	[]
	}
	/*
	 * Write a function 'allConstruct(target, wordBank)' that accepts a target string and an array of strings.
	 * The function should return a 2D array containing all of the ways that the 'target' can be constructed 
	 * by concatenating elements of the 'wordBank' array. Each element of the 2D array should represent one
	 * combination that constructs the 'target'.
	 * You may reuse elements of 'wordBank' as many times as needed.
	 */
	// m = target.length;
	// n = wordBank.length;
	// time: O()
	// space: O()
	public static ArrayList<ArrayList<String>> allConstruct(String target, String[] wordBank){
		//base case
		if(target.equals("")) return new ArrayList<ArrayList<String>>();
		
		ArrayList<ArrayList<String>> result = new ArrayList<>();
		ArrayList<String> inner = new ArrayList<>();
		
		for (int i=0; i<wordBank.length; i++) {
			if(target.indexOf(wordBank[i]) == 0) {
				String suffix = target.substring(wordBank[i].length());
				ArrayList<ArrayList<String>> suffixWays = allConstruct(suffix, wordBank);
				
				
				//this is not working!!!!
				inner.add(0, wordBank[i]);
				suffixWays.add(inner);
				result.addAll(suffixWays);
				
				
			}
			
		}
		
		return result;
		

		
		
		
		
		
	}

}
