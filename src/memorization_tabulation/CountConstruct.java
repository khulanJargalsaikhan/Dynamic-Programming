package memorization_tabulation;

import java.util.HashMap;
import java.util.Map;

public class CountConstruct {

	public static void main(String[] args) {
		System.out.println(countConstruct1("abcdef", new String[] {"ab", "abc", "cd", "def", "abcd"}));  // 1
		System.out.println(countConstruct1("skateboard", new String[] {"bo", "rd", "ate", "t", "ska", "sk", "boar"})); // 0
		System.out.println(countConstruct1("enterapotentpot", new String[] {"a", "p", "ent", "enter", "ot", "o", "t"})); //4 
		//System.out.println(countConstruct1("eeeeeeeeeeeeeeeeeeeeeeeeeeeeef", new String[] {"e", "ee", "eee", "eeee"})); // 0

		System.out.println("-------- Memorization Strategy ---------");
		System.out.println(countConstructMemo("abcdef", new String[] {"ab", "abc", "cd", "def", "abcd"}));  // 1
		System.out.println(countConstructMemo("skateboard", new String[] {"bo", "rd", "ate", "t", "ska", "sk", "boar"})); // 0
		System.out.println(countConstructMemo("enterapotentpot", new String[] {"a", "p", "ent", "enter", "ot", "o", "t"})); //4 
		System.out.println(countConstructMemo("eeeeeeeeeeeeeeeeeeeeeeeeeeeeef", new String[] {"e", "ee", "eee", "eeee"})); // 0

		System.out.println("-------- Tabulation Strategy ---------");
		System.out.println(countConstructTab("purple", new String[] {"purp", "p", "ur", "le", "purpl"}));  // 2
		System.out.println(countConstructTab("abcdef", new String[] {"ab", "abc", "cd", "def", "abcd"}));  // 1
		System.out.println(countConstructTab("skateboard", new String[] {"bo", "rd", "ate", "t", "ska", "sk", "boar"})); // 0
		System.out.println(countConstructTab("enterapotentpot", new String[] {"a", "p", "ent", "enter", "ot", "o", "t"})); //4 
		System.out.println(countConstructTab("eeeeeeeeeeeeeeeeeeeeeeeeeeeeef", new String[] {"e", "ee", "eee", "eeee"})); // 0


	}
	/*
	 * Write a function 'countConstruct(target, wordBank)' that accepts a target string and an array of strings.
	 * The function should return the number of ways that the 'target' can be constructed by concatenating 
	 * elements of the 'wordBank' array.
	 * You may reuse elements of 'wordBank' as many times as needed.
	 */
	// m = target.length;
	// n = wordBank.length;
	// time: O(n^m *m)
	// space: O(m*m)
	public static int countConstruct1(String target, String[] wordBank) {
		if (target.equals("")) return 1;

		int count = 0;

		for(int i=0; i<wordBank.length; i++) {
			if(target.indexOf(wordBank[i]) == 0) {
				String suffix = target.substring(wordBank[i].length());
				count += countConstruct1(suffix, wordBank);
			}
		}

		return count;
	}

	// time: O(n*m^2)
	// space: O(m^2)
	public static int countConstructMemo(String target, String[] wordBank) {
		Map<String, Integer> memolist = new HashMap<>();
		return countConstructMemo(target, wordBank, memolist);
	}

	private static int countConstructMemo(String target, String[] wordBank, Map<String, Integer> memolist) {
		//this is the main part of saving time
		if(memolist.containsKey(target)) return memolist.get(target);
		if (target.equals("")) return 1;

		int count = 0;

		for(int i=0; i<wordBank.length; i++) {
			if(target.indexOf(wordBank[i]) == 0) {
				String suffix = target.substring(wordBank[i].length());
				count += countConstructMemo(suffix, wordBank, memolist);
			}
		}

		memolist.put(target, count);
		return count;
	}


	//	Tabulation strategy  
	//	m = targetSum
	//	n = numbers.length 
	//	Time: O(m^2 * n)  -  Space: O(m)
	public static int countConstructTab(String target, String[] wordBank) {

		int[] table = new int[target.length()+1]; //1 extra slot for substring up to, but not including logic

		//base case
		table[0] = 1; // saying 1 way to make this string (empty string)

		for (int i=0; i<=target.length(); i++) {
			for(String word : wordBank) {
				// if the word matches the characters starting at position i
				if(i+word.length()<table.length && target.substring(i, i+word.length()).equals(word)) {
					table[i+word.length()] += table[i];
				}
			}
		}
		return table[target.length()];
	}


}
