package leetCodeQuestionsPackage;

import linkedListUtilityPackage.LinkedListHelper;
import linkedListUtilityPackage.SinglyLinkedListNode;

/**
 * LeetCode Q21 - Merge Two Sorted Lists
 * ----------------------------------------
 * 📘 Overview:
 * Merge two sorted singly linked lists into a single sorted list.
 * This implementation avoids using a dummy (sentinel) node and instead
 * builds the merged list directly using `newHead` and `newCurr` pointers.
 *
 * 🧩 Example:
 * Input  : list1 = [1,2,4], list2 = [1,3,4]
 * Output : [1,1,2,3,4,4]
 *
 * ⚙️ Approach:
 * - Initialize `newHead` and `newCurr` as null.
 * - Compare the current nodes of both lists:
 *     1️⃣ Choose the smaller node (`lower`).
 *     2️⃣ Move that list’s pointer forward.
 *     3️⃣ Attach `lower` to the merged list.
 * - If merged list is empty, assign `newHead = lower`.
 * - Continue until one list ends.
 * - Attach the remaining part of the non-empty list.
 *
 * 🧮 Complexity Analysis:
 * Time  : O(n + m) — linear in the total number of nodes.
 * Space : O(1) — in-place pointer manipulation, no new nodes created.
 *
 * 🧠 Key Insights:
 * - Avoiding dummy node saves one variable and keeps logic minimal.
 * - Stable merge can be ensured by using `<=` instead of `<`.
 * - Works seamlessly for all edge cases including empty lists.
 *
 * 🔍 Related Topics:
 * - Linked List
 * - Two Pointers
 * - Merge Sort (foundation concept)
 *
 * 💻 Sample I/O:
 * ----------------------------------------
 * 🧠 Input:
 *  list1: 1 2 4
 *  list2: 1 3 4
 *
 * ✅ Output:
 *  Merged Sorted List: 1 → 1 → 2 → 3 → 4 → 4
 *
 * ----------------------------------------
 * Author: Nishant Anand
 * Repository: LeetCode-Java-Playground
 */
public class Q21_MergeTwoSortedLists {

    public static void main(String[] args) {
        System.out.println("===== Welcome. Enter 2 sorted lists. =====\n");

        // Read two sorted linked lists
        SinglyLinkedListNode<Integer> linkedList1 = LinkedListHelper.createIntegerSinglyLinkedListFromInput();
        SinglyLinkedListNode<Integer> linkedList2 = LinkedListHelper.createIntegerSinglyLinkedListFromInput();

        // Merge the lists and print the result (no dummy)
        SinglyLinkedListNode<Integer> mergedList = mergeTwoLists(linkedList1, linkedList2);
        System.out.println("==> Merged Sorted List: " + mergedList);

        // Merge the lists using dummy node and print the result
        SinglyLinkedListNode<Integer> mergedListDummy = mergeTwoListsWithDummyNode(linkedList1, linkedList2);
        System.out.println("==> Merged Sorted List (Dummy Node): " + mergedListDummy);
    }

    /**
     * Merges two sorted singly linked lists without using a dummy node.
     *
     * @param linkedList1 the head of the first sorted linked list
     * @param linkedList2 the head of the second sorted linked list
     * @return head of the merged sorted linked list
     */
    private static SinglyLinkedListNode<Integer> mergeTwoLists(
            SinglyLinkedListNode<Integer> linkedList1,
            SinglyLinkedListNode<Integer> linkedList2) {

        // Handle edge cases
        if (linkedList1 == null) return linkedList2;
        if (linkedList2 == null) return linkedList1;

        SinglyLinkedListNode<Integer> newHead = null;  // start of merged list
        SinglyLinkedListNode<Integer> newCurr = null;  // current tail of merged list

        // Traverse both lists
        while (linkedList1 != null && linkedList2 != null) {
            SinglyLinkedListNode<Integer> lower;

            // Pick smaller node and move its pointer
            if (linkedList1.data <= linkedList2.data) {
                lower = linkedList1;
                linkedList1 = linkedList1.next;
            } else {
                lower = linkedList2;
                linkedList2 = linkedList2.next;
            }

            // Initialize head or attach next node
            if (newHead == null) {
                newHead = lower;
                newCurr = lower;
            } else {
                newCurr.next = lower;
                newCurr = newCurr.next;
            }
        }

        // Attach any remaining nodes
        newCurr.next = (linkedList1 != null) ? linkedList1 : linkedList2;

        return newHead;
    }

    /**
     * Merges two sorted singly linked lists using a dummy (sentinel) node.
     * ----------------------------------------
     *
     * 📘 Overview:
     * This variant uses a dummy node to simplify pointer manipulation.
     * It avoids handling special cases for the head node and makes the
     * logic cleaner, especially for beginners.
     *
     * ⚙️ Approach:
     * - Create a dummy node (-1) as the head of the merged list.
     * - Use a `tail` pointer to build the list.
     * - Iterate through both lists, attaching the smaller node each time.
     * - Append remaining nodes when one list ends.
     *
     * 🧮 Complexity:
     * Time  : O(m + n)
     * Space : O(1)
     *
     * 🧠 Key Insights:
     * - The dummy node helps avoid null checks on head initialization.
     * - Produces the same output as the non-dummy approach.
     *
     * ----------------------------------------
     * @param linkedList1 first sorted linked list
     * @param linkedList2 second sorted linked list
     * @return head of the merged sorted linked list
     */
    private static SinglyLinkedListNode<Integer> mergeTwoListsWithDummyNode(
            SinglyLinkedListNode<Integer> linkedList1,
            SinglyLinkedListNode<Integer> linkedList2) {

        // Dummy node simplifies head handling
        SinglyLinkedListNode<Integer> newHead = new SinglyLinkedListNode<Integer>(-1);
        SinglyLinkedListNode<Integer> tail = newHead;

        // Merge while both lists have nodes
        while (linkedList1 != null && linkedList2 != null) {
            if (linkedList1.data <= linkedList2.data) {
                tail.next = linkedList1;
                linkedList1 = linkedList1.next;
            } else {
                tail.next = linkedList2;
                linkedList2 = linkedList2.next;
            }
            tail = tail.next;
        }

        // Attach remaining nodes (if any)
        tail.next = (linkedList1 != null) ? linkedList1 : linkedList2;

        // Return merged list skipping dummy node
        return newHead.next;
    }
}