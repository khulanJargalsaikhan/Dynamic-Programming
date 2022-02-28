package memorization_tabulation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class AllConstruct {

	public static void main(String[] args) {
		System.out.println("-------- Recursion without memorization ---------");
		System.out.println(allConstruct("purple", new String[] {"purp", "p", "ur", "le", "purpl"}));
		// 	[["purp", "le"], ["p", "ur", "p", "le"]]
		System.out.println(allConstruct("abcdef", new String[] {"ab", "abc", "cd", "def", "abcd", "ef", "c"}));
		// 	[["ab", "cd", "ef"], ["ab", "c", "def"], ["abc", "def"], ["abcd", "ef"]]
		System.out.println(allConstruct("skateboard", new String[] {"bo", "rd", "ate", "t", "ska", "sk", "boar"}));
		// 	[]
		System.out.println(allConstruct("aaaaaaaaaaaaaaaaaaaaaaz", new String[] {"a", "aa", "aaa", "aaaa", "aaaaa"}));
		// 	[]
		
		
		System.out.println("-------- Memorization strategy ---------");
		System.out.println(allConstructMemo("purple", new String[] {"purp", "p", "ur", "le", "purpl"}));
		// 	[["purp", "le"], ["p", "ur", "p", "le"]]
		System.out.println(allConstructMemo("abcdef", new String[] {"ab", "abc", "cd", "def", "abcd", "ef", "c"}));
		// 	[["ab", "cd", "ef"], ["ab", "c", "def"], ["abc", "def"], ["abcd", "ef"]]
		System.out.println(allConstructMemo("skateboard", new String[] {"bo", "rd", "ate", "t", "ska", "sk", "boar"}));
		// 	[]
		System.out.println(allConstructMemo("aaaaaaaaaaaaaaaaaaaaaaz", new String[] {"a", "aa", "aaa", "aaaa", "aaaaa"}));
		// 	[]
		

		System.out.println("-------- Tabulation Strategy ---------");
		//System.out.println(Arrays.deepToString(allConstructTabulation("purple", new String[] {"purp", "p", "ur", "le", "purpl"})) );
		// 	[["purp", "le"], ["p", "ur", "p", "le"]]
		System.out.println(Arrays.deepToString(allConstructTabulation("abcdef", new String[] {"ab", "abc", "cd", "def", "abcd", "ef", "c"})) );
		// 	[["ab", "cd", "ef"], ["ab", "c", "def"], ["abc", "def"], ["abcd", "ef"]]
		//System.out.println(Arrays.deepToString(allConstructTabulation("skateboard", new String[] {"bo", "rd", "ate", "t", "ska", "sk", "boar"})) );
		// 	[]
		//System.out.println(Arrays.deepToString(allConstructTabulation("aaaaaaaaaz", new String[] {"a", "aa", "aaa", "aaaa", "aaaaa"})));
		// 	[]
		
		
	}
	/*
	 * Write a function 'allConstruct(target, wordBank)' that accepts a target string and an array of strings.
	 * The function should return a 2D array containing all of the ways that the 'target' can be constructed 
	 * by concatenating elements of the 'wordBank' array. Each element of the 2D array should represent one
	 * combination that constructs the 'target'.
	 * You may reuse elements of 'wordBank' as many times as needed.
	 */

	
	 /* Recursive call without memorization.
	 * 
	 * m = target.length
	 * n = wordBank.length
	 * 
	 * Time: O(n^m)  Space: O(m)
	 */
	static List<List<String>> allConstruct(String target, String[] wordBank) {
	 
	    // **** base case(s)****
	    if (target.length() == 0) return new ArrayList<>(Arrays.asList(new ArrayList<>()));
	 
	    // **** initialization ****
	    List<List<String>> result = new ArrayList<>();
	 
	    // **** loop through the word bank making a recursive call (if needed) ****
	    for (String word : wordBank) {
	 
	        // **** recursive call (if word is a prefix of target) ****
	        if (target.indexOf(word) == 0) {
	 
	            // **** generate suffix for target ****
	            String suffix = target.substring(word.length());
	 
	            // **** make recursive call ****
	            List<List<String>> suffixWays = allConstruct(suffix, wordBank);
	 
	            // **** ****
	            List<List<String>> targetWays = new ArrayList<>();
	             
	            // **** add prefix to the head of each list ****
	            for (int i = 0; i < suffixWays.size(); i++) {
	 
	                // **** create a new list ****
	                List<String> tmp = new ArrayList<>(suffixWays.get(i));
	 
	                // **** add prefix to this list ****
	                tmp.add(0, word);
	 
	                // **** add this list to this list of lists ****
	                targetWays.add(tmp);
	            }
	 
	            // **** add lists to result ****
	            for (int i = 0; i < targetWays.size(); i++) 
	                result.add(new ArrayList<>(targetWays.get(i)));
	        }
	    }
	 
	    // **** return all possible combinations ****
	    return result;
	}
	
	
	
	 /* Recursive call with memorization.
	  * 
	  * m = target.length
	  * n = wordBank.length
	  * 
	  * Time: O(n^m)  Space: O(m)
	  */
	 static List<List<String>> allConstructMemo(String target, String[] wordBank) {
	 
	     // **** sanity check(s) ****
	     if (target.length() == 0) return new ArrayList<>(Arrays.asList(new ArrayList<>()));
	 
	     // **** initialization ****
	     HashMap<String, List<List<String>>> memo = new HashMap<>();
	 
	     // **** recursive call ****
	     List<List<String>> ans = allConstructMemo(target, wordBank, memo);
	 
	     // ???? ????
	     System.out.println("<<< memo: " + memo.toString());
	 
	     // **** return ans ****
	     return ans;
	 }
	
	 /**
	  * Recursive call with memorization.
	  */
	 static List<List<String>> allConstructMemo( String target, 
	                                             String[] wordBank,
	                                             HashMap<String, List<List<String>>> memo) {
	  
	     // **** base case(s)****
	     if (memo.containsKey(target)) return memo.get(target);
	     if (target.length() == 0) return new ArrayList<>(Arrays.asList(new ArrayList<>()));
	  
	     // **** initialization ****
	     List<List<String>> result = new ArrayList<>();
	  
	     // **** loop through the word bank making a recursive call (if needed) ****
	     for (String word : wordBank) {
	  
	         // **** recursive call (if word is a prefix of target) ****
	         if (target.indexOf(word) == 0) {
	  
	             // **** generate suffix for target ****
	             String suffix = target.substring(word.length());
	  
	             // **** make recursive call ****
	             List<List<String>> suffixWays = allConstructMemo(suffix, wordBank, memo);
	  
	             // **** ****
	             List<List<String>> targetWays = new ArrayList<>();
	              
	             // **** add prefix to the head of each list ****
	             for (int i = 0; i < suffixWays.size(); i++) {
	  
	                 // **** create a new list ****
	                 List<String> tmp = new ArrayList<>(suffixWays.get(i));
	  
	                 // **** add prefix to this list ****
	                 tmp.add(0, word);
	  
	                 // **** add this list to this list of lists ****
	                 targetWays.add(tmp);
	             }
	  
	             // **** add lists to result ****
	             for (int i = 0; i < targetWays.size(); i++) 
	                 result.add(new ArrayList<>(targetWays.get(i)));
	         }
	     }
	  
	     // **** save the key-value pair ****
	     memo.put(target, result);
	  
	     // **** return all possible combinations ****
	     return result;
	 }
	 
	 
	 
	 
	 

		//	Tabulation strategy  
		//	m = targetSum
		//	n = numbers.length 
		//	Time: O(n^m)  -  Space: O(n^m)   -> Exponential complexity
	//solution from Internet (https://www.johncanessa.com/2021/06/24/all-construct-tabulation/)
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
	 
	                // **** add destination list to destination list of lists ****
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
