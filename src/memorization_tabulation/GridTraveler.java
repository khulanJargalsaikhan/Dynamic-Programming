package memorization_tabulation;

import java.util.HashMap;
import java.util.Map;

public class GridTraveler {

	public static void main(String[] args) {
		
		System.out.println("----- Recursive -------");
		System.out.println(gridTraveler(1,1));  // 1
		System.out.println(gridTraveler(2,3));	// 3
		System.out.println(gridTraveler(3,2));	// 3
		System.out.println(gridTraveler(3,3));	// 6
		//System.out.println(gridTraveler(18,18)); //2333606220

		System.out.println("----- Memorization -------");
		System.out.println(gridTravelerMemo(1,1));  // 1
		System.out.println(gridTravelerMemo(2,3));	// 3
		System.out.println(gridTravelerMemo(3,2));	// 3
		System.out.println(gridTravelerMemo(3,3));	// 6
		System.out.println(gridTravelerMemo(18,18)); //2333606220
		
		System.out.println("----- Tabulation -------");
		System.out.println(gridTravelerMemo(1,1));  // 1
		System.out.println(gridTravelerMemo(2,3));	// 3
		System.out.println(gridTravelerMemo(3,2));	// 3
		System.out.println(gridTravelerMemo(3,3));	// 6
		System.out.println(gridTravelerMemo(18,18)); //2333606220
		

	}
	/*
	 * Say that you are a traveler on a 2D grid. You begin in the top-left corner and your goal is 
	 * to travel to the bottom-right corner. You many only move down or right.
	 * In how many ways can you travel to the goal on a grid with dimensions m*n?
	 * Write a function 'gridTraveler(m,n)' that calculates this.
	 */


	//recursive solution m=row, n=col
	// time: O(2^(n+m)) exponential
	// space: O(n+m)
	public static int gridTraveler(int row, int col) {
		if (row == 0 || col == 0) return 0;
		if (row == 1 && col == 1) return 1;

		return gridTraveler(row-1, col) + gridTraveler(row, col-1); 
	}



	//memorized solution m=row, n=col
	// time: O(n*m) 
	// space: O(n+m)
	public static int gridTravelerMemo(int row, int col) {

		Map<String, Integer> memolist = new HashMap<>();
		return gridTravelerMemo(row, col, memolist);
	}

	private static int gridTravelerMemo(int row, int col, Map<String, Integer> memolist) {
		// are the args in the memolist?
		String key = row + "," + col;   // comma is separator (important!)
		if (memolist.containsKey(key)) return memolist.get(key);

		if (row == 0 || col == 0) return 0;
		if (row == 1 && col == 1) return 1;

		int value = gridTravelerMemo(row-1, col, memolist) + gridTravelerMemo(row, col-1, memolist); 
		// cashing the values
		memolist.put(key, value);
		return memolist.get(key);
	}


	// Tabulation strategy (basically creating TABLE) m=row, n=col   
	// time: O(m*n) 
	// space: O(m*n)
	public static int gridTravelerTabulation(int row, int col) {
		
		int[][] table = new int[row+1][col+1];
		
		table[1][1] = 1;
		
		for(int i=0; i<=row; i++) {
			for (int j=0; j<=col; j++) {
				int current = table[i][j];
				
				if (j+1 <= col) // bound checking
					table[i][j+1] += current;  // adding current to right neighbor
				if (i+1 <= row) // bound checking
					table[i+1][j] += current;  // adding current to down neighbor
			}
		}
		return table[row][col];
	}

	
}
