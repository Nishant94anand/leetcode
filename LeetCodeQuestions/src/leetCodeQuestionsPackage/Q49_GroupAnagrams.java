package leetCodeQuestionsPackage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LeetCode Q49 - Group Anagrams
 * ----------------------------------------
 *
 * 📘 Overview:
 * Given an array of strings, group all anagrams together.
 * Two strings are anagrams if they contain the same characters
 * with the same frequency (ordering does not matter).
 *
 * 🧩 Example:
 * Input  : ["eat","tea","tan","ate","nat","bat"]
 * Output :
 * [
 *   ["eat","tea","ate"],
 *   ["tan","nat"],
 *   ["bat"]
 * ]
 *
 * ⚙️ Approach:
 * - For each string, build a **frequency signature** using an int[26]
 * - Convert frequency array into a key string ("a1b0c0...z0")
 * - Use a HashMap<Key, List<Words>> to group anagrams
 *
 * 🧮 Complexity Analysis:
 * Time  : O(N * K) where N = number of strings, K = average length of string
 * Space : O(N * K)
 *
 * ----------------------------------------
 */
public class Q49_GroupAnagrams {

    public static void main(String[] args) {
        String[] strs = new String[] {"eat","tea","tan","ate","nat","bat"};
        List<List<String>> resp = groupAnagrams(strs);

        for (List<String> list : resp) {
            System.out.println(list);
        }
    }

    /**
     * Groups strings into anagram clusters.
     *
     * @param strs input array of strings
     * @return grouped anagram list
     */
    public static List<List<String>> groupAnagrams(String[] strs) {

        Map<String, List<String>> map = new HashMap<>();

        for (String word : strs) {

            // Generate a unique frequency signature key
            String key = generateStringKeyForWord(word);

            // Create bucket if key doesn't exist
            map.putIfAbsent(key, new ArrayList<>());

            // Add word to group
            map.get(key).add(word);
        }

        return new ArrayList<>(map.values());
    }

    /**
     * Creates a string key representing frequency of characters.
     * Example: "eat" → "a1e1t1" (frequency-based key)
     *
     * @param word input string
     * @return signature key
     */
    private static String generateStringKeyForWord(String word) {

        int[] freq = new int[26];

        // Count characters
        for (int i = 0; i < word.length(); i++) {
            freq[word.charAt(i) - 'a']++; // Subtracts the offset of a to get index of array. So 'a' is 0 and all others follow.
        }

        // Convert frequency array into signature
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
        	if (freq[i] > 0) {
        		sb.append((char)(i + 'a')).append(freq[i]);
        	}
        }
        return sb.toString();
    }
}