package memorization_tabulation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class HowSum {

	public static void main(String[] args) {
		System.out.println("-------- Memorization Strategy ---------");
		System.out.println(howSum2(7, new int[] {5,3,4,7})); // --> [4,3], [7] (2 possibilities)
		System.out.println(howSum2(7, new int[] {2,4})); // null
		System.out.println(howSum2(8, new int[] {2,3,5})); // [2,2,2,2], [3,5] (output depends on input order)
		System.out.println(howSum2(300, new int[] {7,14})); // null 

		System.out.println("-------- Tabulation Strategy ---------");
		System.out.println(howSumTab(7, new int[] {5,3,4,7})); // --> [4,3], [7] (2 possibilities)
		System.out.println(howSumTab(7, new int[] {2,4})); // null
		System.out.println(howSumTab(8, new int[] {2,3,5})); // [2,2,2,2], [3,5] (output depends on input order)
		System.out.println(howSumTab(300, new int[] {7,14})); // null 
		
		System.out.println("-------- Tabulation Strategy 2 ---------");
		System.out.println(Arrays.toString(howSumTab2(7, new int[] {5,3,4,7}))); // --> [4,3], [7] (2 possibilities)
		System.out.println(Arrays.toString(howSumTab2(7, new int[] {2,4}))); // null
		System.out.println(Arrays.toString(howSumTab2(8, new int[] {2,3,5}))); // [2,2,2,2], [3,5] (output depends on input order)
		System.out.println(Arrays.toString(howSumTab2(300, new int[] {7,14}))); // null 

	}
	/* Medium-problem
	 * Write a function howSum(targetSum, nums) that takes in a targetSum and an array of numbers as arguments.
	 * The function should return an array containing any combination of elements that add up to exactly 
	 * the targetSum. If there is no combination that adds up to the targetSum, then return null.
	 * If there are multiple combination possible, you may return any single one. 
	 * 
	 */

	//recursive solution  
	//m = targetSum
	//n = nums.length
	//time: O(n^m * m)
	//space: O(m)
	public static ArrayList<Integer> howSum(int targetSum, int[] nums) {
		// base case-> if targetSum reaches 0, it returns empty array list
		if (targetSum == 0) return new ArrayList<>();
		if (targetSum < 0) return null;

		for(int i=0; i<nums.length; i++) {
			//new targetSum
			int remainder = targetSum - nums[i];
			ArrayList<Integer> combination = howSum(remainder, nums);
			if (combination != null) {
				//copying array elements requires m steps (nums.length)
				combination.add(nums[i]);
				return combination;
			}
		}
		return null;
	} 



	//memorized solution  
	//m = targetSum
	//n = nums.length
	//time: O(n * m^2)
	//space: O(m^2)   each m key (all unique values), and values (ArrayList of Integer)
	public static ArrayList<Integer> howSum2(int targetSum, int[] nums) {

		Map<Integer, ArrayList<Integer>> memo = new HashMap<>();
		return howSum2(targetSum, nums, memo);
	} 

	private static ArrayList<Integer> howSum2(int targetSum, int[] nums, Map<Integer, ArrayList<Integer>> memo) {
		// key is targetSum, value is array list of numbers copied from nums array
		// if key is already in the memo, we get the value
		if (memo.containsKey(targetSum))
			return memo.get(targetSum);
		// base case-> if targetSum reaches 0, it returns empty array list	
		if (targetSum == 0) return new ArrayList<>();
		// gone too far, remainder can't be negative, returns null
		if (targetSum < 0) return null;

		for(int i=0; i<nums.length; i++) {
			//new targetSum
			int remainder = targetSum - nums[i];

			//save the return value of recursive call (either new ArrayList created OR null)
			ArrayList<Integer> combination = howSum2(remainder, nums, memo);
			if (combination != null) {
				//copying array elements requires m steps (nums.length)
				combination.add(nums[i]);
				//caching the values with keys (targetSum)
				memo.put(remainder, combination);
				return memo.get(remainder);
			}
		}

		memo.put(targetSum, null);
		return null;
	} 

	//	Tabulation strategy  
	//	m = targetSum
	//	n = numbers.length 
	//	Time: O(m^2 * n)  -  Space: O(m^2)
	public static ArrayList<Integer> howSumTab(int targetSum, int[] numbers) {

		if (targetSum == 0) new ArrayList<Integer>();
		
		@SuppressWarnings("unchecked")
		ArrayList<Integer>[] table = new ArrayList[targetSum + 1];

		//base case
		table[0] = new ArrayList<Integer>();

		// **** iterate through the table ****
		for (int i = 0; i <= targetSum; i++) {
			if (table[i] != null)
				// **** loop through the numbers array ****
				for (int j = 0; j < numbers.length; j++) {
					int num = numbers[j];
	
					// **** skip this index (out of range) ****
					if (i+num > targetSum)
						continue;

					if (table[i+num] == null)
						table[i+num] = new ArrayList<Integer>();
	
					// **** copy all elements from table[i] to table[i+num] ****
					ArrayList<Integer> src = table[i]; 
					ArrayList<Integer> dst = table[i+num];
					dst.clear();
					dst.addAll(src);
					dst.add(num);
				}
			
		}

		return table[targetSum];
	}
	

	//	Tabulation solution - more detailed
	public static int[] howSumTab2(int targetSum, int[] numbers) {

		if (targetSum == 0) return null;
		
		@SuppressWarnings("unchecked")
		ArrayList<Integer>[] table = new ArrayList[targetSum + 1];

		//base case
		table[0] = new ArrayList<Integer>();

		for (int i = 0; i < numbers.length; i++) {
			ArrayList<Integer> al = new ArrayList<Integer>();
			al.add(numbers[i]);
			table[numbers[i]] = al;
		}

		// **** iterate through the table ****
		for (int i = 1; i <= targetSum; i++) {

			if (table[i] != null)
				// **** loop through the numbers array ****
				for (int j = 0; j < numbers.length; j++) {
					int num = numbers[j];
	
					// **** compute target index ****
					int ndx = i + num;
	
					// **** skip this index (out of range) ****
					if (ndx > targetSum)
						continue;
	
					// **** initialize list (if needed) ****
					if (table[ndx] == null)
						table[ndx] = new ArrayList<Integer>();
	
					// **** copy all elements from table[i] to table[ndx] ****
					ArrayList<Integer> src = table[i]; 
					ArrayList<Integer> dst = table[ndx];
					dst.clear();
					dst.addAll(src);
	
					// **** add current element to table[ndx] ****
					dst.add(num);
	
					// **** check if done ****
					if (ndx == targetSum) {
	
						// **** convert List<Integer> to int[] ****
						int[] arr = dst.stream().mapToInt( x -> x).toArray();
	
						// **** return int[] ****
						return arr;
					}
				}
			
		}

		// **** check if no answer was found ****
		if (table[targetSum] == null) return null;

		// **** get last list in the table ****
		ArrayList<Integer> lst = table[targetSum];

		// **** convert List<Integer> to int[] ****
		int[] arr = lst.stream().mapToInt( x -> x).toArray();

		// **** return int[] ****
		return arr;
	}
}
