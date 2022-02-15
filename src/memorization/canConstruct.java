package memorization;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class canConstruct {

	public static void main(String[] args) {
		System.out.println(canConstruct1("abcdef", new String[] {"ab", "abc", "cd", "def", "abcd"}));   // true
		System.out.println(canConstruct1("skateboard", new String[] {"bo", "rd", "ate", "t", "ska", "sk", "boar"}));  // false
		System.out.println(canConstruct1("", new String[] {"cat", "dog", "mouse"}));  // true (because it's base case)
		System.out.println(canConstruct1("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeef", new String[] {"e", "ee", "eee", "eeee"}));  // false
		
		System.out.println(canConstructMemo("abcdef", new String[] {"ab", "abc", "cd", "def", "abcd"}));   // true
		System.out.println(canConstructMemo("skateboard", new String[] {"bo", "rd", "ate", "t", "ska", "sk", "boar"}));  // false
		System.out.println(canConstructMemo("", new String[] {"cat", "dog", "mouse"}));  // true (because it's base case)
		System.out.println(canConstructMemo("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeef", new String[] {"e", "ee", "eee", "eeee"}));  // false
	}
	/*
	 * Write a function 'canConstruct(target, wordBank)' that accepts a target string and an array of strings.
	 * The function should return a boolean indicating whether or not the 'target' can be constructed by concatenating
	 * elements of the 'wordBank' array.
	 * You may reuse elements of 'wordBank' as many times as needed.
	 * 
	 */
	// m = target.length;
	// n = wordBank.length;
	// time: O(n^m *m)
	// space: O(m*m)
	public static boolean canConstruct1(String target, String[] wordBank) {
		//base case
		if(target.equals("")) return true;

		for(int i=0; i<wordBank.length; i++) {
			if(target.indexOf(wordBank[i]) == 0) {
				String suffix = target.substring(wordBank[i].length());
				if (canConstruct1(suffix, wordBank) == true) {
					return true;
				}
			}
		}

		return false;
	}


	// time: O(n*m^2)
	// space: O(m^2)
	public static boolean canConstructMemo(String target, String[] wordBank) {
		Map<String, Boolean> memolist = new HashMap<>();
		return canConstructMemo(target, wordBank, memolist);
	}
	
	public static boolean canConstructMemo(String target, String[] wordBank, Map<String, Boolean> memolist) {
		//adding base case for memorization
		if(memolist.containsKey(target)) return memolist.get(target);
		if(target.equals("")) return true;

		for(int i=0; i<wordBank.length; i++) {
			if(target.indexOf(wordBank[i]) == 0) {
				String suffix = target.substring(wordBank[i].length());
				//don't forget to add memolist parameter to recursion call here
				if (canConstructMemo(suffix, wordBank, memolist) == true) {
					memolist.put(suffix, true);
					return true;
				}
			}
		}
		memolist.put(target, false);
		return false;
	}
}
