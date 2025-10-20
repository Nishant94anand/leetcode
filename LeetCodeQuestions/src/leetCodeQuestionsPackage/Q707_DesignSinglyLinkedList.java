package leetCodeQuestionsPackage;

/**
 * LeetCode Q707 - Design Linked List
 * ----------------------------------------
 * 📘 Overview:
 * Implement a singly linked list supporting the following operations:
 *  - get(index): Get value of node at the given index.
 *  - addAtHead(val): Insert a node at the head.
 *  - addAtTail(val): Append a node at the tail.
 *  - addAtIndex(index, val): Insert a node before the given index.
 *  - deleteAtIndex(index): Delete node at the given index.
 *
 * This version uses a `tail` pointer for O(1) tail insertions and tracks `length` for bounds checking.
 *
 * 🧩 Example:
 * MyLinkedList list = new MyLinkedList();
 * list.addAtHead(1);
 * list.addAtTail(3);
 * list.addAtIndex(1, 2);  // Linked list becomes 1 → 2 → 3
 * list.get(1);            // Returns 2
 * list.deleteAtIndex(1);  // Now becomes 1 → 3
 * list.get(1);            // Returns 3
 *
 * ⚙️ Approach:
 * - Maintain three key members: `head`, `tail`, and `length`.
 * - For operations requiring traversal, use an iterative pointer loop.
 * - Ensure proper tail/head updates for edge cases (e.g., deleting last node).
 *
 * 🧮 Complexity Analysis:
 * get(index)       → O(n)
 * addAtHead(val)   → O(1)
 * addAtTail(val)   → O(1)
 * addAtIndex(i,v)  → O(n)
 * deleteAtIndex(i) → O(n)
 * Space            → O(n)
 *
 * 🧠 Key Insights:
 * - Maintaining a `tail` pointer makes tail insertions O(1).
 * - Always check bounds using the `length` field to avoid invalid access.
 * - Carefully update both `head` and `tail` when deleting boundary nodes.
 *
 * 🔍 Related Topics:
 * Linked List, Design, Data Structure Implementation
 *
 * ----------------------------------------
 * Author: Nishant Anand
 * Repository: DSA-Java-Playground
 */
public class Q707_DesignSinglyLinkedList {

	public static void main(String[] args) {
		// Example usage (LeetCode driver normally handles this)
		MyLinkedList list = new MyLinkedList();
		list.addAtHead(1);
		list.addAtTail(3);
		list.addAtIndex(1, 2);
		System.out.println(list.get(1)); // 2
		list.deleteAtIndex(1);
		System.out.println(list.get(1)); // 3
	}
}

/**
 * Represents a singly linked list supporting multiple operations.
 */
class MyLinkedList {
	MyLinkedListNode head;
	MyLinkedListNode tail;
	int length;

	/** Initialize data members */
	public MyLinkedList() {
		length = 0;
		head = null;
		tail = null;
	}

	/**
	 * Returns the value of the node at the given index.
	 * If the index is invalid, returns -1.
	 */
	public int get(int index) {
		if (index < 0 || index >= length) {
			return -1;
		}

		int currIndex = 0;
		MyLinkedListNode curr = head;

		while (curr != null) {
			if (currIndex == index) {
				return curr.data;
			}
			curr = curr.next;
			currIndex++;
		}
		return -1;
	}

	/**
	 * Adds a node with the given value at the head of the list.
	 * O(1) time complexity.
	 */
	public void addAtHead(int val) {
		MyLinkedListNode newNode = new MyLinkedListNode(val, head);
		head = newNode;
		if (tail == null) {
			tail = newNode; // First node
		}
		length++;
	}

	/**
	 * Adds a node with the given value at the tail of the list.
	 * O(1) time complexity (due to maintained tail pointer).
	 */
	public void addAtTail(int val) {
		MyLinkedListNode newNode = new MyLinkedListNode(val);

		if (head == null) {
			head = newNode;
		}
		if (tail != null) {
			tail.next = newNode;
		}
		tail = newNode;
		length++;
	}

	/**
	 * Adds a node with the given value at the specified index.
	 * If index == length, it is appended at the tail.
	 * If index > length, the operation is ignored.
	 */
	public void addAtIndex(int index, int val) {
		if (index == 0) {
			addAtHead(val);
		} else if (index == length) {
			addAtTail(val);
		} else if (index > length) {
			return;
		} else {
			MyLinkedListNode newNode = new MyLinkedListNode(val);
			int currIndex = 0;
			MyLinkedListNode curr = head;
			while (currIndex < index - 1) {
				curr = curr.next;
				currIndex++;
			}
			newNode.next = curr.next;
			curr.next = newNode;
			length++;
		}
	}

	/**
	 * Deletes the node at the specified index.
	 * If index is invalid, no operation is performed.
	 */
	public void deleteAtIndex(int index) {
		if (index < 0 || index >= length) {
			return;
		}

		if (index == 0) {
			head = head.next;
			if (head == null) {
				tail = null; // List became empty
			}
		} else {
			int currIndex = 0;
			MyLinkedListNode curr = head;
			while (currIndex < index - 1) {
				curr = curr.next;
				currIndex++;
			}
			curr.next = curr.next.next;
			if (index == length - 1) {
				tail = curr; // Deleted last node
			}
		}
		length--;
	}
}

/**
 * Node class representing each element in the linked list.
 */
class MyLinkedListNode {
	public int data;
	public MyLinkedListNode next;

	public MyLinkedListNode(int data) {
		this.data = data;
	}

	public MyLinkedListNode(int data, MyLinkedListNode next) {
		this.data = data;
		this.next = next;
	}
}