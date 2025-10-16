package leetCodeQuestionsPackage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import linkedListUtilityPackage.LinkedListHelper;
import linkedListUtilityPackage.SinglyLinkedListNode;

/**
 * LeetCode Q2 - Add Two Numbers 
 * ---------------------------------------- 
 * This program reads two numbers as singly linked lists, where each node contains a
 * single digit, and the digits are stored in reverse order.
 * 
 * It adds the two numbers and returns the sum as a new linked list in the same
 * format.
 * 
 * Example: 
 * Input: l1 = [2 -> 4 -> 3], l2 = [5 -> 6 -> 4] 
 * Output: [7 -> 0 -> 8]
 * (because 342 + 465 = 807)
 * 
 * Time Complexity: O(max(m, n)) Space Complexity: O(max(m, n))
 * 
 * @author Nishant Anand
 */
public class Q2_AddTwoNumbers {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.println("==> Enter first Linked List (e.g., 342 as 243): ");
		String[] ll1String = sc.nextLine().split("");
		List<Integer> ll1List = new ArrayList<>();

		// Create list in reverse order (LeetCode format)
		for (int i = ll1String.length - 1; i >= 0; --i) {
			ll1List.add(Integer.parseInt(ll1String[i]));
		}

		SinglyLinkedListNode<Integer> linkedList1 = LinkedListHelper.createSinglyLinkedList(ll1List);
		System.out.println("==> Linked List 1: " + linkedList1);

		System.out.println("==> Enter second Linked List (e.g., 465 as 564): ");
		String[] ll2String = sc.nextLine().split("");
		List<Integer> ll2List = new ArrayList<>();

		for (int i = ll2String.length - 1; i >= 0; --i) {
			ll2List.add(Integer.parseInt(ll2String[i]));
		}

		SinglyLinkedListNode<Integer> linkedList2 = LinkedListHelper.createSinglyLinkedList(ll2List);
		System.out.println("==> Linked List 2: " + linkedList2);

		SinglyLinkedListNode<Integer> finalLinkedList = addTwoNumbers(linkedList1, linkedList2);
		System.out.println("==> Final Linked List (Sum): " + finalLinkedList);
	}

	/**
	 * Adds two non-negative integers represented by linked lists. Each node
	 * contains a single digit and the digits are stored in reverse order.
	 *
	 * @param l1 First linked list representing a number.
	 * @param l2 Second linked list representing a number.
	 * @return A new linked list representing the sum of the two numbers.
	 */
	public static SinglyLinkedListNode<Integer> addTwoNumbers(SinglyLinkedListNode<Integer> l1,
			SinglyLinkedListNode<Integer> l2) {
		// If both lists are empty, return null
		if (l1 == null && l2 == null) {
			return null;
		}

		SinglyLinkedListNode<Integer> resultHead = null; // Head of the result list
		SinglyLinkedListNode<Integer> currentResultNode = null; // Pointer to last node in result
		int carry = 0;

		// Traverse both lists until both are null
		while (l1 != null || l2 != null) {
			int sum = carry; // Start with carry from previous addition

			if (l1 != null) {
				sum += l1.data;
				l1 = l1.next;
			}

			if (l2 != null) {
				sum += l2.data;
				l2 = l2.next;
			}

			carry = sum / 10; // Carry for next digit
			int digit = sum % 10; // Current digit for result

			// Create a new node for the sum digit
			SinglyLinkedListNode<Integer> newNode = new SinglyLinkedListNode<>(digit);

			// Initialize or append to result list
			if (resultHead == null) {
				resultHead = newNode;
				currentResultNode = resultHead;
			} else {
				currentResultNode.next = newNode;
				currentResultNode = newNode;
			}
		}

		// Add final carry if present
		if (carry > 0) {
			currentResultNode.next = new SinglyLinkedListNode<>(carry);
		}

		return resultHead;
	}
}