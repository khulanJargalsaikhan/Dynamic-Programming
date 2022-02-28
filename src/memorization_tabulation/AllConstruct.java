package memorization_tabulation;

import java.util.ArrayList;
import java.util.Arrays;

public class AllConstruct {

	public static void main(String[] args) {
//		System.out.println("-------- Memorization Strategy ---------");
//		System.out.println(allConstruct("purple", new String[] {"purp", "p", "ur", "le", "purpl"}));
//		// 	[["purp", "le"], ["p", "ur", "p", "le"]]
//		System.out.println(allConstruct("abcdef", new String[] {"ab", "abc", "cd", "def", "abcd", "ef", "c"}));
//		// 	[["ab", "cd", "ef"], ["ab", "c", "def"], ["abc", "def"], ["abcd", "ef"]]
//		System.out.println(allConstruct("skateboard", new String[] {"bo", "rd", "ate", "t", "ska", "sk", "boar"}));
//		// 	[]
		
		
		System.out.println("-------- Tabulation Strategy ---------");
		//System.out.println(allConstructTab("purple", new String[] {"purp", "p", "ur", "le", "purpl"}));
		// 	[["purp", "le"], ["p", "ur", "p", "le"]]
		System.out.println(allConstructTab("abcdef", new String[] {"ab", "abc", "cd", "def", "abcd", "ef", "c"}));
		// 	[["ab", "cd", "ef"], ["ab", "c", "def"], ["abc", "def"], ["abcd", "ef"]]
		//System.out.println(allConstructTab("skateboard", new String[] {"bo", "rd", "ate", "t", "ska", "sk", "boar"}));
		// 	[]
		
		
		System.out.println("-------- Tabulation Strategy from internet ---------");
		System.out.println(Arrays.deepToString(allConstructTabulation("purple", new String[] {"purp", "p", "ur", "le", "purpl"})) );
		// 	[["purp", "le"], ["p", "ur", "p", "le"]]
		System.out.println(Arrays.deepToString(allConstructTabulation("abcdef", new String[] {"ab", "abc", "cd", "def", "abcd", "ef", "c"})) );
		// 	[["ab", "cd", "ef"], ["ab", "c", "def"], ["abc", "def"], ["abcd", "ef"]]
		System.out.println(Arrays.deepToString(allConstructTabulation("skateboard", new String[] {"bo", "rd", "ate", "t", "ska", "sk", "boar"})) );
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


		//	Tabulation strategy  
		//	m = targetSum
		//	n = numbers.length 
		//	Time: O(n^m)  -  Space: O(n^m)   -> Exponential complexity

	public static ArrayList<ArrayList<String>> allConstructTab(String target, String[] wordBank) {

		@SuppressWarnings("unchecked")
		ArrayList<ArrayList<String>>[] table = new ArrayList[target.length()+1];

		//base case
		table[0] = new ArrayList<ArrayList<String>>();


		for (int i=0; i<=target.length(); i++) {
			if (table[i] != null) {
				for(String word : wordBank) {
					// if the word matches the characters starting at position i
					if(i+word.length()<table.length && target.substring(i, i+word.length()).equals(word)) {
						
						ArrayList<ArrayList<String>> temp = new ArrayList<>();
						temp = table[i];

						if (!temp.isEmpty()) {	
							for (int k=0; k<temp.size(); k++) {
								temp.get(k).add(word);
							}
						} else {
							ArrayList<String> al = new ArrayList<>();
							al.add(word);
							temp.add(al);
						}

						if (table[i+word.length()] == null) {
							table[i+word.length()] = new ArrayList<ArrayList<String>>();
						}
						 
						table[i+word.length()].addAll(temp);
						
						temp.clear();

					}
					
				}
			}
		}
		
		return table[target.length()];
	}



	
	
	//from internet
	static String[][] allConstructTabulation(String target, String[] wordBank) {
		 
	    // **** sanity check(s) [[]]****
	    if (target.length() == 0) {
	        String[][] ans = new String[1][1];
	        ans[0] = new String[] {""};
	        return ans;
	    }
	 
	    // **** create and initialize table ****
	    ArrayList<ArrayList<ArrayList<String>>> table = new ArrayList<ArrayList<ArrayList<String>>>();
	    for (int i = 0; i < target.length() + 1; i++)
	        table.add(i, new ArrayList<ArrayList<String>>());
	    ArrayList<ArrayList<String>> lol = table.get(0);
	    lol.add(new ArrayList<String>());
	 
	    // ???? ????
	    System.out.println("<<<  table: " + table.toString() + " size: " + table.size());
	 
	    // **** iterate through the table ****
	    for (int i = 0; i < table.size(); i++) {
	 
	        // **** get to the current list of lists ****
	        lol = table.get(i);
	 
	        // **** if blank entry (skip) ****
	        if (lol.size() == 0)
	            continue;
	 
	        // **** iterate through the word bank ****
	        for (int j = 0; j < wordBank.length; j++) {
	 
	            // **** for ease of use ****
	            String word = wordBank[j];
	 
	            // **** generate index ****
	            int ndx = i + word.length();
	 
	            // **** if we can NOT extract prefix from target (skip) ****
	            if (ndx > target.length())
	                continue;
	 
	            // **** extract prefix from the target ****
	            String prefix = target.substring(i, ndx);
	 
	            // **** if word and prefix do NOT match (skip) ****
	            if (!word.equals(prefix))
	                continue;
	 
	            // **** source list of lists ****
	            ArrayList<ArrayList<String>> src = table.get(i);
	 
	            // **** destination list of lists ****
	            ArrayList<ArrayList<String>> dst = table.get(ndx);
	 
	            // **** copy source list(s) to this table entry ****
	            for (int k = 0; k < src.size(); k++) {
	 
	                // **** source list to copy ****
	                ArrayList<String> srcLst = src.get(k);
	 
	                // **** destination list ****
	                ArrayList<String> dstLst = new ArrayList<String>();
	 
	                // **** add source to destination list ****
	                dstLst.addAll(srcLst);
	 
	                // **** append word to destination list ****
	                dstLst.add(word);
	 
	                // **** add destination list to destinaltion list of lists ****
	                dst.add(dstLst);
	            }
	 
	            // ???? ????
	            System.out.println("<<<  table: " + table.toString() + " size: " + table.size());
	        }
	    }
	 
	    // **** get last list of lists in table ****
	    ArrayList<ArrayList<String>> ansLst = table.get(target.length());
	 
	    // **** 2D array to hold answer ****
	    String[][] ans = new String[ansLst.size()][];
	 
	    // **** traverse the list of lists ****
	    for (int i = 0; i < ansLst.size(); i++) {
	 
	        // **** get this array list ****
	        ArrayList<String> al = ansLst.get(i);
	 
	        // **** generate array from array list ****
	        String[] arr = al.toArray(String[]::new);
	 
	        // **** insert array into 2D array ****
	        ans[i] = arr;
	    }
	 
	    // **** return 2D array ****
	    return ans;
	}


}
