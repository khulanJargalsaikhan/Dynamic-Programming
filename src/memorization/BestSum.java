package memorization;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BestSum {

	public static void main(String[] args) {
		System.out.println(bestSum(7, new int[] {5,3,4,7})); 		// [7] 
		System.out.println(bestSum(8, new int[] {1,4,5})); 			// [4,4]
		System.out.println(bestSum(8, new int[] {2,3,5})); 			// [3,5]
		//System.out.println(bestSum(100, new int[] {1,2,5,25})); 		// [25,25,25,25]
		
		System.out.println(bestSum2(7, new int[] {5,3,4,7})); 		// [7] 
		System.out.println(bestSum2(8, new int[] {1,4,5})); 		// [4,4]
		System.out.println(bestSum2(8, new int[] {2,3,5})); 		// [3,5]
		System.out.println(bestSum2(100, new int[] {1,2,5,25})); 	// [25,25,25,25]

	}
	//	Harder-problem
	/* Write a function bestSum(targetSum, numbers) that takes in a targetSum and an array of numbers as arguments.
	 * The function should return an array containing the shortest combination of numbers that add up to exactly the targetSum.
	 * If there is a tie for the shortest combination, you may return any one of the shortest.
	 */

	
	//Brute Force solution  
	//m = targetSum
	//n = nums.length
	//time: O(n^m * m)  exponential  
	//space: O(m^2)
	public static ArrayList<Integer> bestSum(int targetSum, int[] nums){

		if (targetSum == 0) return new ArrayList<>();
		if (targetSum < 0) return null;

		// creating null array list and compare with every combination to find out the shortest
		ArrayList<Integer> shortestCombination = null;

		//branching logic - iterate through array elements
		for (int i=0; i<nums.length; i++) {
			int remainder = targetSum - nums[i];
			ArrayList<Integer> remainderResult = bestSum(remainder, nums);
			if (remainderResult != null) {
				remainderResult.add(nums[i]);

				//if the combination is shorter than the current "shortest", update it
				if (shortestCombination == null || remainderResult.size() < shortestCombination.size() ) {
					shortestCombination = remainderResult;
				}
			}
		}	
		return shortestCombination;
	}

	//memorized solution  
	//m = targetSum
	//n = nums.length
	//time: O(n * m^2)
	//space: O(m^2) 
	public static ArrayList<Integer> bestSum2(int targetSum, int[] nums){

		Map<Integer, ArrayList<Integer>> memo = new HashMap<>();
		return bestSum2(targetSum, nums, memo);
	}
	
	private static ArrayList<Integer> bestSum2(int targetSum, int[] nums, Map<Integer, ArrayList<Integer>> memo){
		
		if(memo.containsKey(targetSum))
			return memo.get(targetSum);
		if (targetSum == 0) return new ArrayList<>();
		if (targetSum < 0) return null;

		// creating null array list to compare with every combination in order to find out the shortest
		ArrayList<Integer> shortestCombination = null;

		//branching logic - iterate through array elements
		for (int i=0; i<nums.length; i++) {
			int remainder = targetSum - nums[i];
			ArrayList<Integer> remainderResult = bestSum2(remainder, nums, memo);
			if (remainderResult != null) {
				//a good idea to assign a new list while using recursion technique
				ArrayList<Integer> tempCombination = new ArrayList<>(remainderResult);
				tempCombination.add(nums[i]);
				
				//if the combination is shorter than the current "shortest", update it
				if (shortestCombination == null || tempCombination.size() < shortestCombination.size()) {
					shortestCombination = tempCombination;
				}
			}
		}	
		
		memo.put(targetSum, shortestCombination);
		return shortestCombination;
	}


}


