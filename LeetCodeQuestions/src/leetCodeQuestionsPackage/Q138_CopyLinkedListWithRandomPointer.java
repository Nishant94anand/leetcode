package leetCodeQuestionsPackage;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * LeetCode Q138 - Copy List with Random Pointer
 * ----------------------------------------
 * 📘 Overview:
 * Given a linked list where each node contains an additional 'random' pointer
 * (that may point to any node in the list or null), this algorithm creates a
 * deep copy of the entire structure — including the random links.
 *
 * 🧩 Example:
 * Input:
 *   7 -> 13 -> 11 -> 10 -> 1
 *   Random Pointers:
 *   13 → 7, 11 → 1, 10 → 11, 1 → 7
 *
 * Output:
 *   A deep-copied linked list with the same structure and random mappings.
 *
 * ⚙️ Approach (Optimized O(1) Space):
 * 1️⃣ Interweave copied nodes between original nodes.
 * 2️⃣ Assign random pointers for each copied node using the interleaved structure.
 * 3️⃣ Unweave (separate) the two lists to restore original and extract the copied list.
 *
 * 🧮 Complexity Analysis:
 * Time  : O(n)  — Three linear passes through the list.
 * Space : O(1)  — Constant extra space, no hash maps.
 *
 * 🧠 Key Insights:
 * - Interleaving copies directly beside originals allows constant-space mapping.
 * - The key trick is using `curr.next.random = curr.random.next` for linking.
 *
 * 🔍 Related Topics:
 * - Linked List
 * - HashMap (alternative O(n) approach)
 * - Deep Copy / Cloning
 *
 * 💻 Sample I/O:
 * ----------------------------------------
 * 🧠 Input:
 *  7 -> 13 -> 11 -> 10 -> 1
 *  Random: { 1→0, 2→4, 3→2, 4→0 }
 *
 * ✅ Output:
 *  Deep Copied List with identical random structure
 *
 * ----------------------------------------
 * Author: Nishant Anand
 * Repository: LeetCode-Java-Playground
 */
public class Q138_CopyLinkedListWithRandomPointer {

    public static void main(String[] args) {
        List<Node> list = Arrays.asList(
                new Node(7),
                new Node(13),
                new Node(11),
                new Node(10),
                new Node(1)
        );

        Map<Integer, Integer> randomIndex = Map.of(
                1, 0,
                2, 4,
                3, 2,
                4, 0
        );

        Node head = createNodeWithRandomPointer(list, randomIndex);
        System.out.println("Original:\t" + head);

        Node newHead = copyRandomList(head);
        System.out.println("Copied:\t\t" + newHead);
    }

    /**
     * Creates a deep copy of a linked list where each node has a random pointer.
     * Uses in-place interleaving to achieve O(1) extra space.
     */
    public static Node copyRandomList(Node head) {
        if (head == null) return null;

        // 1️⃣ Interleave copy nodes
        Node curr = head;
        while (curr != null) {
            Node copy = new Node(curr.val);
            copy.next = curr.next;
            curr.next = copy;
            curr = copy.next;
        }

        // 2️⃣ Update random pointers for copied nodes
        curr = head;
        while (curr != null) {
            if (curr.random != null)
                curr.next.random = curr.random.next;
            curr = curr.next.next;
        }

        // 3️⃣ Separate the intertwined lists
        curr = head;
        Node newHead = head.next;
        Node copy = newHead;

        while (curr != null) {
            curr.next = curr.next.next;
            copy.next = (copy.next == null) ? null : copy.next.next;
            curr = curr.next;
            copy = copy.next;
        }

        return newHead;
    }

    /** Helper: Builds a linked list and assigns random pointers. */
    private static Node createNodeWithRandomPointer(List<Node> list, Map<Integer, Integer> randomIndex) {
        for (int i = 0; i < list.size() - 1; ++i)
            list.get(i).next = list.get(i + 1);

        if (randomIndex != null)
            for (Entry<Integer, Integer> entry : randomIndex.entrySet())
                list.get(entry.getKey()).random = list.get(entry.getValue());

        return list.get(0);
    }

    /** Node definition for Linked List with Random Pointer. */
    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
        }

		@Override
		public String toString() {
			String randomText = this.random == null ? null : this.random.val + "";
			Integer identityHash = this.random == null ? null : System.identityHashCode(this.random);
			return val + " | " + System.identityHashCode(this) + " [" + randomText + " | " + identityHash + "] "
					+ "\t->\t" + next;
		}
    }
}
