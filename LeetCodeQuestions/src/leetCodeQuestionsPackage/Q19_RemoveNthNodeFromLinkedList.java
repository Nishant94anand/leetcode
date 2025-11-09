package leetCodeQuestionsPackage;

import java.util.Scanner;

import linkedListUtilityPackage.LinkedListHelper;
import linkedListUtilityPackage.SinglyLinkedListNode;

/*
* LeetCode Q19 - Remove Nth Node From End of List
* ----------------------------------------
* 📘 Overview:
* Given the head of a singly linked list, remove the nth node from the end
* of the list and return its head.
*
* 🧩 Example:
* Input  : head = [1,2,3,4,5], n = 2
* Output : [1,2,3,5]
*
* ⚙️ Approach:
* - Use a dummy node before head to simplify edge cases (like removing head).
* - Move `fast` pointer through the list.
* - When `fast` has moved `n` steps, begin moving `slow` from `dummy`.
* - Both pointers now move together until `fast` reaches end.
* - `slow.next` now points to the node BEFORE the one to be removed.
* - Adjust link: `slow.next = slow.next.next`
*
* 🧮 Complexity Analysis:
* Time  : O(L), where L is the length of the list (single traversal)
* Space : O(1), no extra data structures used
*
* ----------------------------------------
* Author: Nishant Anand
* Repository: LeetCode-Java-Playground
*/
public class Q19_RemoveNthNodeFromLinkedList {

	public static void main(String[] args) {
		ListNode head = ListNode.createIntegerSinglyLinkedListFromInput();
		System.out.println("==> n: ");
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		head = removeNthFromEnd(head, n);
		System.out.println("Removed: " + head);
	}

	/**
	 * LeetCode Q19 - Remove Nth Node From End of List
	 * ----------------------------------------
	 * Removes the nth node from the end of a singly linked list.
	 *
	 * @param head the head of the linked list
	 * @param n    nth position from the end to remove
	 * @return modified head of the linked list
	 */
	public static ListNode removeNthFromEnd(ListNode head, int n) {
		if (head == null) {
			return null;
		}

		// Dummy node to handle head removal gracefully
		ListNode dummy = new ListNode(-1);
		dummy.next = head;

		ListNode fast = dummy;
		ListNode slow = null;
		int index = 0;

		// Traverse the list
		while (fast.next != null) {
			fast = (ListNode) fast.next;
			index++;

			if (slow != null) {
				slow = (ListNode) slow.next;
			}

			if (index == n) {
				slow = dummy;
			}
		}

		// If slow is still null, n > length -> no deletion possible
		if (slow == null) {
			System.out.println("List is smaller - no change");
			return head;
		}

		// Remove the nth node
		slow.next = slow.next.next;

		return (ListNode) dummy.next;
	}

	// Local ListNode class for LeetCode consistency
	static class ListNode extends SinglyLinkedListNode<Integer> {

		public ListNode(Integer value) {
			super(value);
		}

		public static ListNode createIntegerSinglyLinkedListFromInput() {
			SinglyLinkedListNode<Integer> head = LinkedListHelper.createIntegerSinglyLinkedListFromInput();
			return convert(head);
		}

		private static ListNode convert(SinglyLinkedListNode<Integer> head) {
			if (head == null) {
				return null;
			}

			ListNode newHead = new ListNode(head.data);
			ListNode currentNew = newHead;
			SinglyLinkedListNode<Integer> currentOld = head.next;

			while (currentOld != null) {
				currentNew.next = new ListNode(currentOld.data);
				currentNew = (ListNode) currentNew.next;
				currentOld = currentOld.next;
			}

			return newHead;
		}
	}

}