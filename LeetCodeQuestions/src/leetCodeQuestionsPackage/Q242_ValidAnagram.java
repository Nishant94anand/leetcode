/**
 * LeetCode Q242 - Valid Anagram
 * ----------------------------------------
 * Given two strings s and t, check if t is an anagram of s.
 * An anagram contains the exact same characters in the same frequency,
 * but possibly in a different order.
 *
 * 🧠 Core Idea:
 * Since the input consists of lowercase English letters (a-z),
 * we can use a fixed-size frequency array of length 26.
 *
 * ✅ Approach:
 * 1. If lengths differ → cannot be anagram → return false.
 * 2. Increment counts using string s.
 * 3. Decrement counts using string t.
 * 4. If all counts return to zero → strings are anagrams.
 *
 * ⏱ Time Complexity: O(n)   (single pass)
 * 🧮 Space Complexity: O(1)  (26-length array)
 *
 * This approach is optimal and avoids sorting.
 */
package leetCodeQuestionsPackage;

public class Q242_ValidAnagram {

	public static void main(String[] args) {
		String str1 = "nishant";
		String str2 = "ishannty";

		boolean isAnagram = isAnagram(str1, str2);
		System.out.println(isAnagram);
	}

	public static boolean isAnagram(String s, String t) {
		// Quick fail if lengths differ
		if (s.length() != t.length()) {
			return false;
		}

		int[] freq = new int[26];

		// Increase using s, decrease using t
		for (int i = 0; i < s.length(); ++i) {
			freq[s.charAt(i) - 'a']++;
			freq[t.charAt(i) - 'a']--;
		}

		// If all zero -> anagram
		for (int val : freq) {
			if (val != 0) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Older Implementation (for reference/comparison)
	 * Uses two frequency arrays instead of one.
	 * Same complexity, just more memory work.
	 */
	public static boolean isAnagram_OldImplementation(String s, String t) {
		if (s.length() != t.length()) {
			return false;
		}

		int[] countArrayStr1 = getCountArrayForLowerCaseString(s);
		int[] countArrayStr2 = getCountArrayForLowerCaseString(t);

		for (int i = 0; i < 26; ++i) {
			if (countArrayStr1[i] != countArrayStr2[i]) {
				return false;
			}
		}
		return true;
	}

	private static int[] getCountArrayForLowerCaseString(String s) {
		int[] arr = new int[26];

		for (int i = 0; i < s.length(); ++i) {
			arr[s.charAt(i) - 'a']++;
		}
		return arr;
	}

}