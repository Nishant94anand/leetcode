package leetCodeQuestionsPackage;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import linkedListUtilityPackage.LinkedListHelper;
import linkedListUtilityPackage.SinglyLinkedListNode;

/**
 * LeetCode Q143 - Reorder List
 * ----------------------------------------
 * 📘 Overview:
 * Reorders a singly linked list from L0 → L1 → L2 → … → Ln
 * into the sequence L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → …
 *
 * 🧩 Example:
 * Input  : 1 → 2 → 3 → 4 → 5
 * Output : 1 → 5 → 2 → 4 → 3
 *
 * ⚙️ Approach (Optimal - O(1) Space):
 * 1️⃣ Find the middle of the linked list (slow/fast pointer approach).
 * 2️⃣ Split the list into two halves and reverse the second half.
 * 3️⃣ Merge the two lists alternately.
 *
 * 🧮 Complexity Analysis:
 * Time  : O(n)  — each node is processed a constant number of times.
 * Space : O(1)  — in-place reordering without extra data structures.
 *
 * 🧠 Key Insights:
 * - Classic three-step technique for linked list manipulation.
 * - Clean and modular: `split`, `reverse`, and `merge` are reusable.
 * - Prevents additional stack/queue overheads from suboptimal variants.
 *
 * 🔍 Related Topics:
 * - Linked List
 * - Two Pointers
 * - In-place List Manipulation
 *
 * 💻 Sample I/O:
 * ----------------------------------------
 * 🧠 Input:
 *   1 2 3 4 5
 * ✅ Output:
 *   1 → 5 → 2 → 4 → 3
 *
 * ----------------------------------------
 * Author: Nishant Anand
 * Repository: LeetCode-Java-Playground
 */
public class Q143_ReorderList {

	public static void main(String[] args) {
		// Read input linked list using helper
		SinglyLinkedListNode<Integer> linkedList = LinkedListHelper.createIntegerSinglyLinkedListFromInput();

		// Apply optimal reorder algorithm
		SinglyLinkedListNode<Integer> modifiedLinkedList = reorderList_optimal(linkedList);
		System.out.println("==> Modified Linked List: " + modifiedLinkedList);
	}

	/**
	 * ✅ Optimal Approach: Reorder linked list using O(1) space.
	 * 
	 * Steps:
	 * 1. Split the list into two halves using the fast/slow pointer method.
	 * 2. Reverse the second half.
	 * 3. Merge both halves alternately.
	 *
	 * @param linkedList the head of the linked list to reorder
	 * @return reordered linked list head
	 */
	private static SinglyLinkedListNode<Integer> reorderList_optimal(SinglyLinkedListNode<Integer> linkedList) {
		if (linkedList == null || linkedList.next == null || linkedList.next.next == null) {
			return linkedList; // Base cases: 0, 1, or 2 nodes
		}

		// Step 1: Split into two halves
		SinglyLinkedListNode<Integer> linkedListToReverse = splitLinkedListAndFindLinkedListToBeReversed(linkedList);

		// Step 2: Reverse second half
		SinglyLinkedListNode<Integer> reversedLinkedList = reverseLinkedList(linkedListToReverse);

		// Step 3: Merge alternately
		mergeLinkedListsAlternately(linkedList, reversedLinkedList);

		return linkedList;
	}

	/**
	 * Merges two linked lists alternately in-place.
	 * Example: 1→2→3 and 6→5→4 → 1→6→2→5→3→4
	 */
	private static void mergeLinkedListsAlternately(SinglyLinkedListNode<Integer> linkedList,
			SinglyLinkedListNode<Integer> reversedLinkedList) {

		SinglyLinkedListNode<Integer> curr = linkedList;
		SinglyLinkedListNode<Integer> revCurr = reversedLinkedList;

		while (revCurr != null) {
			SinglyLinkedListNode<Integer> next = curr.next;
			SinglyLinkedListNode<Integer> revNext = revCurr.next;

			curr.next = revCurr;     // Link from first list to reversed node
			revCurr.next = next;     // Link from reversed node to next original node

			revCurr = revNext;       // Move ahead in reversed list
			curr = next;             // Move ahead in original list
		}
	}

	/**
	 * Reverses a singly linked list iteratively.
	 */
	private static SinglyLinkedListNode<Integer> reverseLinkedList(SinglyLinkedListNode<Integer> linkedListToReverse) {
		if (linkedListToReverse == null || linkedListToReverse.next == null) {
			return linkedListToReverse;
		}

		SinglyLinkedListNode<Integer> prev = null;
		SinglyLinkedListNode<Integer> curr = linkedListToReverse;

		while (curr != null) {
			SinglyLinkedListNode<Integer> next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}

		return prev;
	}

	/**
	 * Splits the linked list into two halves.
	 * Returns the head of the second half (to be reversed).
	 */
	private static SinglyLinkedListNode<Integer> splitLinkedListAndFindLinkedListToBeReversed(
			SinglyLinkedListNode<Integer> linkedList) {
		SinglyLinkedListNode<Integer> back = linkedList;         // slow pointer
		SinglyLinkedListNode<Integer> front = linkedList.next;   // fast pointer

		while (front != null && front.next != null) {
			back = back.next;
			front = front.next.next;
		}

		// Split the list
		SinglyLinkedListNode<Integer> linkedListToReverse = back.next;
		back.next = null;

		return linkedListToReverse;
	}

	/**
	 * Utility method to find length of linked list (for debugging/reference).
	 */
	private static int linkedListLength(SinglyLinkedListNode<Integer> linkedList) {
		int length = 0;
		SinglyLinkedListNode<Integer> curr = linkedList;

		while (curr != null) {
			curr = curr.next;
			length++;
		}

		return length;
	}

	/**
	 * 🐢 Suboptimal Approach: Uses Stack + Queue to reorder list.
	 * 
	 * While functionally correct, it is less efficient:
	 * - Requires O(n) extra space for both stack and queue.
	 * - More object references and slower memory access.
	 * 
	 * Still useful for learning — demonstrates how to simulate
	 * the L0 → Ln → L1 → Ln-1 pattern manually.
	 */
	private static SinglyLinkedListNode<Integer> reorderList_suboptimal(SinglyLinkedListNode<Integer> linkedList) {
		if (linkedList == null || linkedList.next == null || linkedList.next.next == null) {
			return linkedList;
		}

		// Stack for last elements, Queue for first elements
		Stack<SinglyLinkedListNode<Integer>> stack = new Stack<>();
		Queue<SinglyLinkedListNode<Integer>> queue = new LinkedList<>();

		// Traverse and push each node
		SinglyLinkedListNode<Integer> curr = linkedList;
		while (curr != null) {
			SinglyLinkedListNode<Integer> next = curr.next;
			curr.next = null;
			stack.push(curr);
			queue.add(curr);
			curr = next;
		}

		int length = stack.size();
		int currLength = 0;
		SinglyLinkedListNode<Integer> prev = new SinglyLinkedListNode<Integer>(-1); // dummy head

		// Alternately connect nodes from queue and stack
		while (currLength != length) {
			SinglyLinkedListNode<Integer> next;
			if (currLength % 2 == 0) {
				next = queue.poll();
			} else {
				next = stack.pop();
			}

			prev.next = next;
			prev = next;
			currLength++;
		}

		stack.clear();
		queue.clear();

		return linkedList;
	}
}