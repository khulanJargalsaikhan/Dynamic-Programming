package memorization;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HowSum {

	public static void main(String[] args) {
		System.out.println(howSum2(7, new int[] {5,3,4,7})); // --> [4,3], [7] (2 possibilities)
		System.out.println(howSum2(7, new int[] {2,4})); // null
		System.out.println(howSum2(8, new int[] {2,3,5})); // [2,2,2,2], [3,5] (output depends on input order)
		System.out.println(howSum2(300, new int[] {7,14})); // null 
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

}
