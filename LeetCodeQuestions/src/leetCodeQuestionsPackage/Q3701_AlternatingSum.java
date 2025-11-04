package leetCodeQuestionsPackage;

/**
 * LeetCode Q3701 - Alternating Sum
 * ----------------------------------------
 * 📘 Overview:
 * Given an integer array `nums`, the task is to compute the alternating sum.
 * Alternating sum means:
 *     result = nums[0] - nums[1] + nums[2] - nums[3] + ...
 *
 * 🧩 Example:
 * Input  : [1, 3, 5, 7]
 * Output : 1 - 3 + 5 - 7 = -4
 *
 * ⚙️ Approach:
 * - Maintain a running total `sum`.
 * - Use a `multiplier` initialized to +1.
 * - For each element in the array:
 *      sum += num * multiplier
 *      multiplier *= -1  (this flips +1 to -1 and vice versa)
 *
 * 🧮 Complexity Analysis:
 * Time  : O(n)  — We scan the array once.
 * Space : O(1)  — No extra data structure used except a few variables.
 *
 * 🧠 Key Insights:
 * - Alternating addition/subtraction can be handled cleanly using a flipping multiplier.
 * - No need to check for index parity (`i % 2 == 0`), multiplier flip handles everything.
 *
 * 🔍 Related Topics:
 * - Array
 * - Prefix Operations
 * - Math
 *
 * ----------------------------------------
 * Author: Nishant Anand
 * Repository: leetcode
 */
public class Q3701_AlternatingSum {

    public static void main(String[] args) {
        int[] nums = new int[] { 1, 3, 5, 7 };

        int sum = alternatingSum(nums);
        System.out.println(sum);
    }

    /**
     * Calculates the alternating sum of an integer array.
     *
     * @param nums Integer array
     * @return Alternating sum result
     */
    public static int alternatingSum(int[] nums) {
        int sum = 0;
        int multiplier = 1; // +1 for addition, -1 for subtraction (flips every iteration)

        for (int num : nums) {
            sum += num * multiplier;
            multiplier *= -1; // Flip sign for next iteration
        }

        return sum;
    }
}