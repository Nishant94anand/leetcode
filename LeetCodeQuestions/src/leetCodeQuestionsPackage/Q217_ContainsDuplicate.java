/**
 * LeetCode Q217 - Contains Duplicate
 * ----------------------------------------
 * Given an integer array nums, determine if any value appears at least twice.
 * Return true if any duplicate is found, otherwise return false.
 *
 * 🧠 Core Idea:
 * Use a HashSet to track elements seen so far.
 * As we iterate over the array:
 * - If the current element is already in the set, a duplicate exists.
 * - Otherwise, add the element to the set and continue.
 *
 * ✅ Why HashSet?
 * HashSet provides O(1) average time complexity for `contains()` and `add()`.
 * This allows us to detect duplicates in a single pass.
 *
 * ⏱️ Time Complexity  : O(n)
 * 🧮 Space Complexity : O(n)
 *
 * This is the most efficient general-purpose solution.
 */
package leetCodeQuestionsPackage;

import java.util.HashSet;
import java.util.Set;

public class Q217_ContainsDuplicate {

	public static void main(String[] args) {
		int[] nums = new int[] { 1, 2, 3, 4, 3 };
		System.out.println(containsDuplicate(nums));
	}

	public static boolean containsDuplicate(int[] nums) {
		Set<Integer> set = new HashSet<>();

		for (int num : nums) {
			// Duplicate found
			if (set.contains(num)) {
				return true;
			}

			// Insert into set
			set.add(num);
		}

		// No duplicates
		return false;
	}
}