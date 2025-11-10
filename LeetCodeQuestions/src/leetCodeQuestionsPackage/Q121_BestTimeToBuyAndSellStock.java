/**
 * LeetCode Q121 - Best Time to Buy and Sell Stock
 * ------------------------------------------------
 * We are given an array where each element represents the stock price on that day.
 * We must choose ONE day to buy and ONE future day to sell to maximize profit.
 *
 * Constraint:
 * - Only one transaction allowed (no multiple buys/sells).
 *
 * 🧠 Key Observation:
 * To maximize profit: Buy at the cheapest price seen so far, and check if selling
 * today yields a better profit than seen before.
 *
 * 🏁 Approach (Single Pass, O(n)):
 * 1. Track the minimum price encountered while iterating.
 * 2. For each day, compute potential profit: price[i] - minTillHere.
 * 3. Update maxProfit if this profit is better.
 *
 * ✅ This guarantees:
 * - Best buy is always before sell.
 * - No nested loops (efficient).
 *
 * ⏱️ Time Complexity  : O(n)
 * 🧮 Space Complexity : O(1)
 */
package leetCodeQuestionsPackage;

public class Q121_BestTimeToBuyAndSellStock {

	public static void main(String[] args) {
		int[] prices = new int[] { 7, 1, 5, 3, 6, 4 };
		int maxProfit = maxProfit(prices);
		System.out.println("==> Max Profit: " + maxProfit);
	}

	public static int maxProfit(int[] prices) {
		int maxProfit = 0;
		int minTillHere = Integer.MAX_VALUE;

		for (int price : prices) {
			// Keep track of the minimum price seen so far
			if (price < minTillHere) {
				minTillHere = price;
			}
			// Check if selling today gives better profit
			else {
				maxProfit = Math.max(maxProfit, price - minTillHere);
			}
		}

		return maxProfit;
	}
}