package memorization;

import java.util.HashMap;
import java.util.Map;

public class GridTraveler {

	public static void main(String[] args) {
		System.out.println(gridTraveler(1,1));  // 1
		System.out.println(gridTraveler(2,3));	// 3
		System.out.println(gridTraveler(3,2));	// 3
		System.out.println(gridTraveler(3,3));	// 6
		//System.out.println(gridTraveler(18,18)); //2333606220
		
		System.out.println(gridTravelerMemo(1,1));  // 1
		System.out.println(gridTravelerMemo(2,3));	// 3
		System.out.println(gridTravelerMemo(3,2));	// 3
		System.out.println(gridTravelerMemo(3,3));	// 6
		System.out.println(gridTravelerMemo(18,18)); //2333606220

	}
	
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
}
