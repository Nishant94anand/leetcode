package leetCodeQuestionsPackage;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * LeetCode Q3 - Longest Substring Without Repeating Characters
 * ----------------------------------------
 * 📘 Overview:
 * Given a string `s`, find the length of the longest substring 
 * without repeating characters.
 *
 * 🧩 Example:
 * Input  : "abcabcbb"
 * Output : 3
 * Explanation: The answer is "abc", with a length of 3.
 *
 * ⚙️ Approach 1 (Using HashSet - Sliding Window):
 * - Maintain two pointers `start` and `end` to form a sliding window.
 * - Use a HashSet to store unique characters within the current window.
 * - Expand the `end` pointer to include new characters.
 * - If a duplicate is encountered, shrink the window from the `start` pointer 
 *   until the duplicate is removed.
 * - Track and update the maximum window size throughout the process.
 *
 * ⚙️ Approach 2 (Using HashMap - Optimized Jump Window):
 * - Maintain a HashMap to store each character's latest index.
 * - When a duplicate is found, move the `start` pointer directly 
 *   past the last seen index of that character (no need to shrink one-by-one).
 * - This avoids redundant operations and improves efficiency slightly.
 *
 * 🧮 Complexity Analysis:
 * Time  : O(n) for both approaches (each character processed at most twice)
 * Space : O(min(n, charset)) for HashSet / HashMap storage
 *
 * 🧠 Key Insights:
 * - The sliding window ensures every character is processed efficiently.
 * - HashMap-based version can jump the `start` index in O(1) time.
 * - Both are equally correct; Map version tends to be slightly faster on large inputs.
 *
 * 🔍 Related Topics:
 * Sliding Window, HashSet, HashMap, String Manipulation
 *
 * ----------------------------------------
 * Author: Nishant Anand
 * Repository: DSA-Java-Playground
 */
public class Q3_LongestNonRepeatingSubstring {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();

		// Method 1: Sliding Window using HashSet
		int maxLengthSet = lengthOfLongestSubstring(str);
		System.out.println("Max Length (Using Set): " + maxLengthSet);

		// Method 2: Sliding Window using HashMap (Optimized Jumping Window)
		int maxLengthMap = lengthOfLongestSubstringWithMap(str);
		System.out.println("Max Length (Using Map): " + maxLengthMap);

		sc.close();
	}

	/**
	 * Approach 1: Sliding Window using HashSet
	 * ----------------------------------------
	 * Expands a window [start, end) and keeps unique characters inside a HashSet.
	 * When a duplicate is found, shrink the window from the left until the
	 * duplicate character is removed.
	 *
	 * @param s The input string
	 * @return Length of the longest substring without repeating characters
	 */
	public static int lengthOfLongestSubstring(String s) {
		int start = 0;
		int end = 0;
		Set<Character> set = new HashSet<>();
		int maxLength = 0;

		// Expand the window
		for (; end < s.length(); ++end) {
			char ch = s.charAt(end);

			// If duplicate is found, move 'start' forward until duplicate removed
			if (set.contains(ch)) {
				while (start < end) {
					char removed = s.charAt(start);
					set.remove(removed);
					start++;
					if (removed == ch) {
						break;
					}
				}
			}

			// Add current character and update max length
			set.add(ch);
			maxLength = Math.max(maxLength, set.size());
		}

		return maxLength;
	}

	/**
	 * Approach 2: Sliding Window using HashMap (Optimized)
	 * ----------------------------------------
	 * Uses a HashMap to store each character's latest index.
	 * Instead of removing one-by-one, directly jump the 'start' pointer
	 * past the previous occurrence of the duplicate character.
	 *
	 * @param s The input string
	 * @return Length of the longest substring without repeating characters
	 */
	public static int lengthOfLongestSubstringWithMap(String s) {
		int n = s.length();
		int maxLength = 0;
		Map<Character, Integer> map = new HashMap<>();

		for (int end = 0, start = 0; end < n; end++) {
			char ch = s.charAt(end);

			// If duplicate found, jump 'start' just beyond the last occurrence
			if (map.containsKey(ch)) {
				start = Math.max(map.get(ch) + 1, start);
			}

			// Update latest index of current character
			map.put(ch, end);

			// Update maximum window size
			maxLength = Math.max(maxLength, end - start + 1);
		}

		return maxLength;
	}
}