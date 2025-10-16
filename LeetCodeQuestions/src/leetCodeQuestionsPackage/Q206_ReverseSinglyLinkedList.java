package leetCodeQuestionsPackage;

import linkedListUtilityPackage.LinkedListHelper;
import linkedListUtilityPackage.SinglyLinkedListNode;

/**
 * LeetCode Q206 - Reverse Linked List
 * ----------------------------------------
 * Overview:
 * Given the head of a singly linked list, this algorithm reverses the list
 * and returns the new head. The reversal is done in-place using an iterative
 * approach with O(1) extra space.
 *
 * Example:
 * Input  : 1 -> 2 -> 3 -> 4 -> 5
 * Output : 5 -> 4 -> 3 -> 2 -> 1
 *
 * Approach:
 * - Initialize three pointers: prev (null), curr (head), and next (for tracking).
 * - Iterate through the list:
 *     1. Store next = curr.next
 *     2. Reverse the link: curr.next = prev
 *     3. Move prev = curr and curr = next
 * - At the end, prev becomes the new head of the reversed list.
 *
 * Complexity Analysis:
 * Time  : O(n)
 * Space : O(1)
 *
 * Key Insights:
 * - Reverses in-place without recursion.
 * - Clean and iterative, avoids stack overflow on large lists.
 * - Handles edge cases like null or single node naturally.
 *
 * Related Topics:
 * - Linked List
 * - Two Pointers
 * - Iteration vs Recursion
 *
 * Sample I/O:
 * ----------------------------------------
 * Input:
 *  Enter Linked List values: 1 2 3 4 5
 *
 * Output:
 *  Reversed Linked List: 5 -> 4 -> 3 -> 2 -> 1
 *
 * ----------------------------------------
 * Author: Nishant Anand
 * Repository: LeetCode-Java-Playground
 */
public class Q206_ReverseSinglyLinkedList {

    public static void main(String[] args) {
        // Read input linked list using helper
        SinglyLinkedListNode<Integer> linkedList = LinkedListHelper.createIntegerSinglyLinkedListFromInput();

        // Reverse the linked list
        SinglyLinkedListNode<Integer> reversedLinkedList = reverseSinglyLinkedList(linkedList);

        // Display final result
        System.out.println("==> Reversed Linked List: " + reversedLinkedList);
    }

    /**
     * Reverses a singly linked list iteratively.
     *
     * @param head head node of the input linked list
     * @return new head of the reversed linked list
     */
    private static SinglyLinkedListNode<Integer> reverseSinglyLinkedList(SinglyLinkedListNode<Integer> head) {
        SinglyLinkedListNode<Integer> prev = null;
        SinglyLinkedListNode<Integer> curr = head;

        while (curr != null) {
            // Store next node
            SinglyLinkedListNode<Integer> next = curr.next;

            // Reverse the current node's pointer
            curr.next = prev;

            // Move forward
            prev = curr;
            curr = next;
        }

        // 'prev' now points to the new head
        return prev;
    }
}