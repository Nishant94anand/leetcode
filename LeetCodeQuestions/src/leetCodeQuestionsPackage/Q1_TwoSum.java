package leetCodeQuestionsPackage;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode Q1 - Two Sum
 * ----------------------------------------
 *
 * <p><b>Problem (brief):</b> Given an integer array {@code nums} and an integer {@code target},
 * return indices of the two numbers such that they add up to {@code target}.
 * You may assume that each input would have <b>exactly one</b> solution, and you may not
 * use the same element twice.
 *
 * <p><b>Example:</b>
 * <pre>
 * nums = [2, 7, 11, 15], target = 9
 * returns [0, 1] because nums[0] + nums[1] == 9
 * </pre>
 *
 * <p><b>Approach used:</b> Single-pass hash map (value -> index). While iterating the array,
 * for each element {@code x} we check whether {@code target - x} is already seen (present in map).
 * If yes -> we found the pair; otherwise we store {@code x} with its index for future checks.
 *
 * <p><b>Time Complexity:</b> O(n) — one pass through the array.<br>
 * <b>Space Complexity:</b> O(n) — for the hash map storing seen values and their indices.
 *
 * <p><b>Note:</b> LeetCode guarantees exactly one solution. If you want a defensive version
 * for general use, handle the "not found" case appropriately (e.g., return {@code null} or throw).
 *
 * @author Nishant Anand
 */
public class Q1_TwoSum {

    /**
     * Simple driver to test the {@link Solution#twoSum(int[], int)} method.
     *
     * @param args not used
     */
    public static void main(String[] args) {
        int[] nums = new int[] {2, 7, 11, 15};
        int target = 9;

        Solution s = new Solution();

        int[] resp = s.twoSum(nums, target);

        // Print indices found
        System.out.println(resp[0]);
        System.out.println(resp[1]);
    }
}

/**
 * The {@code Solution} class contains the implementation of the two-sum algorithm.
 * This implementation performs a single pass over the input array and uses a hash map
 * to look up complements in O(1) average time.
 */
class Solution {

    /**
     * Finds indices of the two numbers that add up to {@code target}.
     *
     * <p>Algorithm (single-pass hash map):
     * <ol>
     *   <li>Create a map from number value -> index.</li>
     *   <li>For each element {@code nums[i]} compute {@code complement = target - nums[i]}.</li>
     *   <li>If {@code complement} exists in the map, return the pair of indices
     *       {@code [map.get(complement), i]}.</li>
     *   <li>Otherwise put {@code nums[i]} -> {@code i} into the map and continue.</li>
     * </ol>
     *
     * <p><b>Precondition:</b> The problem guarantees exactly one valid pair exists.
     *
     * @param nums   input array of integers
     * @param target sum target for the two numbers
     * @return an array of size 2 containing the indices of the two numbers that add up to {@code target}
     */
    public int[] twoSum(int[] nums, int target) {
        // Map to store previously seen number -> its index
        Map<Integer, Integer> numToIndexMap = new HashMap<>();

        // Result array to hold the two indices (guaranteed to exist per problem statement)
        int[] resp = new int[2];

        // Iterate over the array once
        for (int i = 0; i < nums.length; ++i) {
            // The complementary number we need to find among previously seen elements
            int numToFind = target - nums[i];

            // If complement already seen, we have our answer
            if (numToIndexMap.containsKey(numToFind)) {
                resp[0] = numToIndexMap.get(numToFind); // index of complement
                resp[1] = i;                             // current index
                break;                                   // we can stop early
            } else {
                // Store current number with its index for future complements to find
                numToIndexMap.put(nums[i], i);
            }
        }

        return resp;
    }
}
