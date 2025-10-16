package leetCodeQuestionsPackage;

import linkedListUtilityPackage.LinkedListHelper;
import linkedListUtilityPackage.SinglyLinkedListNode;

/**
 * LeetCode Q2807 - Insert Greatest Common Divisors in Linked List
 * ----------------------------------------
 * Overview:
 * Given the head of a singly linked list, this algorithm inserts a new node
 * between every pair of adjacent nodes, where the new node’s value is the
 * Greatest Common Divisor (GCD) of those two adjacent node values.
 *
 * Example:
 * Input  : 18 -> 6 -> 10 -> 3
 * Output : 18 -> 6 -> 6 -> 2 -> 10 -> 1 -> 3
 *
 * Approach:
 * - Traverse the list using two pointers: `curr` (current node) and `next`.
 * - For every pair (curr, next):
 *     - Compute gcd(curr.data, next.data)
 *     - Create a new node with this gcd value
 *     - Insert the new node between curr and next
 * - Move to the next original pair and repeat until the list ends.
 *
 * Complexity Analysis:
 * Time  : O(n * log(min(a, b))) ~= O(n)
 * Space : O(1) — in-place modification of the linked list
 *
 * Key Insights:
 * - Uses the Euclidean algorithm for efficient GCD computation.
 * - Handles edge cases gracefully (empty list or single-node list).
 * - Traverses and modifies the list in a single pass.
 *
 * Related Topics:
 * - Linked List
 * - Math
 * - Greatest Common Divisor (GCD)
 *
 * ----------------------------------------
 * Author: Nishant Anand
 * Repository: LeetCode-Java-Playground
 */
public class Q2807_GCDInSinglyLinkedList {

    public static void main(String[] args) {
        // Read input linked list from console helper
        SinglyLinkedListNode<Integer> linkedList = LinkedListHelper.createIntegerSinglyLinkedListFromInput();

        // Perform GCD insertion
        SinglyLinkedListNode<Integer> linkedListWithGCD = insertGreatestCommonDivisors(linkedList);

        // Display final result
        System.out.println("==> Linked List with GCD: " + linkedListWithGCD);
    }

    /**
     * Inserts nodes representing the GCD between every two adjacent nodes.
     *
     * @param head the head of the input singly linked list
     * @return the head of the modified linked list with inserted GCD nodes
     */
    public static SinglyLinkedListNode<Integer> insertGreatestCommonDivisors(SinglyLinkedListNode<Integer> head) {
        // Edge case: empty or single-node list
        if (head == null || head.next == null) {
            return head;
        }

        SinglyLinkedListNode<Integer> curr = head;

        // Traverse while both current and next nodes exist
        while (curr != null && curr.next != null) {
            int gcdValue = gcd(curr.data, curr.next.data);

            // Create new node with GCD value
            SinglyLinkedListNode<Integer> gcdNode = new SinglyLinkedListNode<>(gcdValue, curr.next);

            // Insert between current and next
            curr.next = gcdNode;

            // Move to next original pair
            curr = gcdNode.next;
        }

        return head;
    }

    /**
     * Computes GCD of two integers using the Euclidean algorithm.
     *
     * @param a first integer
     * @param b second integer
     * @return gcd(a, b)
     */
    private static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}