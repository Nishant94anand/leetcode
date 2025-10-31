package leetCodeQuestionsPackage;

/**
 * LeetCode Q430 - Flatten a Multilevel Doubly Linked List
 * --------------------------------------------------------
 * Problem:
 * You are given a doubly linked list, where in addition to the regular `next` and
 * `prev` pointers, each node may have a `child` pointer which may point to a 
 * separate doubly linked list (which can also have children).
 *
 * Flatten the list so that all nodes appear in a single-level doubly linked list.
 * The order must follow depth-first traversal.
 *
 *
 * Example:
 *
 *     1 <=> 2 <=> 3
 *           |
 *           4 <=> 5 <=> 6
 *                |
 *                10
 *
 * Output (Flattened):
 *     1 <=> 2 <=> 4 <=> 5 <=> 10 <=> 6 <=> 3
 *
 *
 * Approach (DFS Recursive):
 * - For each node, if it has a child:
 *     - Recursively flatten the child list
 *     - Insert the flattened child list between current node and its next
 *     - Connect the child's tail to the original next node
 *
 * - We return the **tail node** of the flattened list segment.
 * - Head remains unchanged, only internal linking changes.
 *
 *
 * Time Complexity: O(N)
 *      Every node is visited exactly once.
 *
 * Space Complexity: O(N)
 *      Due to recursion (worst-case if the list is like a chain of children).
 *
 * ----------------------------------------
 * Author: Nishant Anand
 * Repository: DSA-Java-Playground
 */
public class Q430_FlattenDoublyLinkedList {

    public static void main(String[] args) {

        // Quick test input (3-level deep child nesting)
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);

        n1.child = n2;
        n2.child = n3;

        print(n1);           // Before flattening
        print(flatten(n1));  // After flattening
    }

    /**
     * Flattens a multilevel doubly linked list into a single-level list.
     *
     * @param head Starting node of the list
     * @return Flattened head node
     */
    public static Node flatten(Node head) {
        getTailNodeOfFlattenedList(head);     // Recursively flatten everything
        return head;
    }

    /**
     * Recursive DFS helper that:
     * - Flattens list from 'head'
     * - Returns the tail node of the flattened list segment
     *
     * @param head Starting node to flatten
     * @return Tail node of the flattened list segment
     */
    private static Node getTailNodeOfFlattenedList(Node head) {
        Node curr = head;
        Node lastCoveredNode = null;

        while (curr != null) {

            Node next = curr.next; // Store next pointer (since it may get overwritten)

            // Case 1: Node has a child -> we need to flatten it
            if (curr.child != null) {

                // Recursively flatten the child list and receive tail node of flattened child segment
                Node childTail = getTailNodeOfFlattenedList(curr.child);

                // Link curr -> child
                curr.next = curr.child;
                curr.child.prev = curr;

                // Child processed -> remove child pointer
                curr.child = null;

                // Connect child's tail -> original next node
                if (next != null) {
                    childTail.next = next;
                    next.prev = childTail;
                }

                // Last processed node becomes childTail
                lastCoveredNode = childTail;
            }
            else {
                // Case 2: Simple node without child
                lastCoveredNode = curr;
            }

            curr = next; // Proceed in original sequence
        }

        return lastCoveredNode;
    }

    /** Utility print method - prints multilevel list like a tree structure */
    public static void print(Node head) {
        printLevel(head, 0);
    }

    private static void printLevel(Node head, int level) {
        if (head == null) return;

        String indent = "      ".repeat(level);
        Node curr = head;

        System.out.print(indent);
        while (curr != null) {
            System.out.print(curr.val);
            if (curr.next != null) System.out.print(" <=> ");
            curr = curr.next;
        }
        System.out.println();

        curr = head;
        while (curr != null) {
            if (curr.child != null) {
                System.out.println(indent + "      |");
                printLevel(curr.child, level + 1);
            }
            curr = curr.next;
        }
    }

}

/**
 * Node structure used in LeetCode Q430
 */
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;

    public Node(int val) {
        this.val = val;
    }
}
