package memorization_tabulation;

import java.util.HashMap;
import java.util.Map;

public class CanSum {

	public static void main(String[] args) {
		System.out.println(canSum2(7, new int[] {5,3,4,7})); //true --> 3+4, 7 (2 possible way)
		System.out.println(canSum2(7, new int[] {2,4})); //false 
		System.out.println(canSum2(300, new int[] {7,14})); //false 

	}
/* Easy-problem
 Write a function canSum(targerSum, number) that takes in a targetSum and an array of numbers as arguments.
 The function should return a boolean indicating whether or not it is possible to generate the targetSum 
 using numbers from the array.
 You may use an element of the array as many times as needed.
 You may assume that all input numbers are nonnegative. 
 
 */
	//recursive solution  
	//m = targetSum
	//n = nums.length
	//time complexity O(n^m) which exponential
	//space complexity O(m)
	public static boolean canSum(int targetSum, int[] nums) {
		
		if (targetSum == 0) return true;
		if (targetSum < 0) return false;
		
		for (int i=0; i<nums.length; i++) {
			int remainder = targetSum - nums[i];
			if (canSum(remainder, nums) == true) {
				return true;
			}
		}
		return false;
	}
	
	
	//memorized solution  
	//m = targetSum
	//n = nums.length
	//time complexity O(n*m)
	//space complexity O(m)
	public static boolean canSum2(int targetSum, int[] nums) {
		
		Map<Integer, Boolean> memo = new HashMap<>();
		
		return canSumMemo(targetSum, nums, memo);
	}
	
	private static boolean canSumMemo(int targetSum, int[] nums, Map<Integer, Boolean> memo) {
		
		//pulling the values from memo that already memorized 
		if (memo.containsKey(targetSum)) {
			return memo.get(targetSum);
		}
			
		// if target == 0 then it mean targetSum is possible 
		if (targetSum == 0) return true;
		
		// this means we gone too far, remainder can't be negative
		if (targetSum < 0) return false;
		
		// iterate through array numbers
		for (int i=0; i<nums.length; i++) {
			//gives new targetSum
			int remainder = targetSum - nums[i];
			
			// recursion call -> it returns true or false, if it's true that means there is a possibility
			if (canSumMemo(remainder, nums, memo) == true) {
				//caching the value
				memo.put(remainder, true);
				return true;
			}
		}
		
		// after attempted all possibilities, if none of them meets with true, then there is not possibility 
		memo.put(targetSum, false);
		return false;
	}
	
	
}
