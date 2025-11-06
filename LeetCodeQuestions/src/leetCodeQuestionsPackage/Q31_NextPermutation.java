package leetCodeQuestionsPackage;

/**
 * LeetCode Q31 - Next Permutation
 * ----------------------------------------
 * 📘 Problem Summary:
 * Given an array representing a permutation of numbers, rearrange it into the
 * next lexicographically greater permutation. If such permutation isn’t
 * possible, rearrange it into the lowest (sorted ascending) order.
 *
 * 🧩 Example:
 * Input  : [1, 2, 3]
 * Output : [1, 3, 2]
 *
 * Input  : [3, 2, 1]
 * Output : [1, 2, 3]
 *
 * ⚙️ Approach:
 * 1️⃣ Scan from the end to find the first decreasing element → pivot index `i`.
 *     - This marks where the permutation can still be improved.
 * 2️⃣ Find the smallest element greater than nums[i] to the right → swap index `j`.
 * 3️⃣ Swap nums[i] and nums[j].
 * 4️⃣ Reverse the suffix after index `i` to get the smallest ordering.
 *
 * 🧮 Complexity:
 * Time  : O(n)
 * Space : O(1)
 *
 * 🧠 Key Insight:
 * The tail after pivot is always in descending order — reversing it yields the smallest next suffix.
 *
 * ----------------------------------------
 * Author: Nishant Anand
 * Repository: LeetCode-Java-Playground
 */
public class Q31_NextPermutation {

	public static void main(String[] args) {
		int[] nums = new int[] { 1, 5, 1 };
		System.out.println("Before Permutation:");
		print(nums);

		nextPermutation(nums);

		System.out.println("After Permutation:");
		print(nums);
	}

	/**
	 * Rearranges nums into the next lexicographical permutation.
	 */
	public static void nextPermutation(int[] nums) {
		if (nums.length < 2) {
			return;
		}

		// Step 1: Find pivot index
		int pivotIndex = findSwapSourceIndex(nums);

		// If pivot not found, permutation is max → reverse to smallest
		if (pivotIndex < 0) {
			reverse(nums, 0);
			return;
		}

		// Step 2: Find element just greater than pivot
		int swapIndex = findSwapTargetIndex(nums, pivotIndex);

		// Step 3: Swap pivot and swapIndex
		swap(nums, pivotIndex, swapIndex);

		// Step 4: Reverse suffix to get smallest possible sequence
		reverse(nums, pivotIndex + 1);
	}

	/** Reverse array from startIndex to end */
	private static void reverse(int[] nums, int startIndex) {
		int left = startIndex, right = nums.length - 1;
		while (left < right) {
			swap(nums, left++, right--);
		}
	}

	/** Swap two elements */
	private static void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}

	/**
	 * Finds the rightmost index where nums[i] < nums[i+1]. This identifies the
	 * pivot where next permutation change begins.
	 */
	private static int findSwapSourceIndex(int[] nums) {
		int i = nums.length - 2;
		while (i >= 0 && nums[i] >= nums[i + 1]) {
			i--;
		}
		return i;
	}

	/**
	 * Finds the index to swap with pivot: smallest element > pivot, scanning from
	 * right.
	 */
	private static int findSwapTargetIndex(int[] nums, int pivot) {
		int j = nums.length - 1;
		while (j > pivot && nums[j] <= nums[pivot]) {
			j--;
		}
		return j;
	}

	/** Utility: Print array */
	private static void print(int[] nums) {
		System.out.println("\n=== Nums ===");
		for (int num : nums) {
			System.out.print(num + " | ");
		}
		System.out.println("\n============\n");
	}
}
