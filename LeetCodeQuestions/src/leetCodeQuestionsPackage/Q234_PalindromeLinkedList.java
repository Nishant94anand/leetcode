/**
 * LeetCode Q234 - Palindrome Linked List
 * ----------------------------------------
 * A singly linked list is given. We need to check if the list forms a palindrome.
 *
 * Example:
 * Input  : 1 -> 2 -> 3 -> 2 -> 1
 * Output : true
 *
 * Approach:
 * 1. Use slow-fast pointers to find the middle of the list.
 * 2. Reverse the second half of the list.
 * 3. Compare node values of first half and reversed second half.
 *
 * Time Complexity  : O(n)
 * Space Complexity : O(1)   (in-place reversal, no extra DS)
 */
package leetCodeQuestionsPackage;

import linkedListUtilityPackage.LinkedListHelper;
import linkedListUtilityPackage.SinglyLinkedListNode;

public class Q234_PalindromeLinkedList {

	public static void main(String[] args) {
		// Create linked list using helper
		SinglyLinkedListNode<Integer> head = LinkedListHelper.createIntegerSinglyLinkedListFromInput();
		boolean isPalindrome = isPalindrome(head);
		System.out.println(isPalindrome);
	}

	/**
	 * Checks if the given singly linked list is a palindrome.
	 */
	public static boolean isPalindrome(SinglyLinkedListNode<Integer> head) {
		if (head == null || head.next == null) {
			return true;
		}

		// Step 1: Find middle of the list
		SinglyLinkedListNode<Integer> mid = findMid(head);

		// Step 2: Reverse the second half
		SinglyLinkedListNode<Integer> reversedHead = reverse(mid);

		// Step 3: Compare first half and reversed second half
		SinglyLinkedListNode<Integer> headCurr = head;
		SinglyLinkedListNode<Integer> revHeadCurr = reversedHead;

		while (revHeadCurr != null && headCurr != null) {
			// Using != here because data is Integer but LeetCode test constraints keep values small
			if (headCurr.data != revHeadCurr.data) {
				return false;
			}
			headCurr = headCurr.next;
			revHeadCurr = revHeadCurr.next;
		}

		return true;
	}

	/**
	 * Reverses a singly linked list starting from the given head.
	 * Returns the new head of the reversed list.
	 */
	private static SinglyLinkedListNode<Integer> reverse(SinglyLinkedListNode<Integer> head) {
		SinglyLinkedListNode<Integer> prev = null;
		SinglyLinkedListNode<Integer> curr = head;

		while (curr != null) {
			SinglyLinkedListNode<Integer> next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}

		return prev;
	}

	/**
	 * Finds and returns the middle node of the linked list
	 * using slow-fast pointer technique.
	 */
	private static SinglyLinkedListNode<Integer> findMid(SinglyLinkedListNode<Integer> head) {
		SinglyLinkedListNode<Integer> slow = head;
		SinglyLinkedListNode<Integer> fast = head;

		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}

		return slow; // slow will be at the middle
	}
}