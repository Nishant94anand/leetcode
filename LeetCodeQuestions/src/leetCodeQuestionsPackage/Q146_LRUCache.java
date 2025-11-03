package leetCodeQuestionsPackage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * LeetCode Q146 - LRU Cache
 * ----------------------------------------
 * 📘 Overview:
 * Implement Least Recently Used (LRU) Cache with O(1) `get()` and `put()`.
 *
 * ✅ Data Structures Used:
 * - HashMap (key -> Node)
 * - Doubly Linked List (stores usage order)
 *
 * ✅ Rules:
 * - Most Recently Used (MRU) node remains at **tail**
 * - Least Recently Used (LRU) node remains at **head**
 * - When capacity is full → remove head from DLL + HashMap
 *
 * 🧮 Complexity:
 * Time  : O(1) for `get()` and `put()`
 * Space : O(capacity)
 *
 * ----------------------------------------
 * Author: Nishant Anand
 * Repository: LeetCode-Java-Playground
 */
public class Q146_LRUCache {

	public static void main(String[] args) {
//		String[] methods = new String[] { "LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get" };
//		String valuesStr = "[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]";
		
		String[] methods = new String[] { "LRUCache","put","put","get","get","put","get","get","get" };
		String valuesStr = "[2],[2,1],[3,2],[3],[2],[4,3],[2],[3],[4]";
		List<Integer> expectedValues = Arrays.asList(null,null,null,2,1,null,1,-1,3);
		
//		String[] methods = new String[] { "LRUCache","put","put","get","put","get","put","get","get","get" };
//		String valuesStr = "[2],[1,1],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]";
//		List<Integer> expectedValues = Arrays.asList(null,null,null,1,null,-1,null,-1,3,4);

		List<List<Integer>> values = Arrays.stream(valuesStr.split("\\],"))
				.map(str -> str.replaceAll("[\\[\\]]", "").trim()) // remove []
				.map(str -> Arrays.stream(str.split(",")) // split numbers
						.map(String::trim).filter(x -> !x.isEmpty()).map(Integer::parseInt)
						.collect(Collectors.toList()))
				.collect(Collectors.toList());

		LRUCache cache = null;
		List<Integer> responses = new ArrayList<>();

		for (int i = 0; i < methods.length; ++i) {
			String method = methods[i];
			List<Integer> vals = values.get(i);
			Integer resp = null;
			
			StringBuilder sb = new StringBuilder("\n\n ************ Going for Action | Method : " + method + " | ");

			switch (method) {
			case "LRUCache":
				int size = vals.get(0);
				sb.append("size: " + size + " | ************ \n\n");
				cache = new LRUCache(size);
				resp = null;
				break;

			case "put":
				int key = vals.get(0);
				int val = vals.get(1);
				sb.append("key: " + key + " | value: " + val + " | ************ \n\n");
				cache.put(key, val);
				resp = null;
				break;

			case "get":
				key = vals.get(0);
				sb.append("key: " + key + " | ************ \n\n");
				resp = cache.get(key);
				System.out.println("Obtained val for key " + key + " : " + resp);
				responses.add(resp);
				break;

			default:
				System.out.println("Invalid input!");
			}
			
			responses.add(resp);
			System.out.println(sb.toString());
			
			System.out.println("\n==> Cache after operation " + method + " with values " + vals + "\n" + cache + "\n");
			
			if (resp != expectedValues.get(i)) 
			{
				System.out.println("\n\n====> $$$$$$$$ Mismatch | Expected Value: " + expectedValues.get(i) + " | Actual Value: " + resp + " | \n\n");
			}
			
			System.out.println("\n\n *********************** Done for method ******************\n****************************************\n\n");
			
		}
		
		System.out.println("\n\n====> Response: " + responses);

	}

}

class LRUCache {
	int capacity;
    Node head; // Least recently used
    Node tail; // Most recently used
    Map<Integer, Node> keyToNodeMap;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        keyToNodeMap = new HashMap<>();
    }

	/**
     * Returns value for given key.
     * Also marks node as "recently used".
     */
    public int get(int key) {
        if (keyToNodeMap.containsKey(key)) {
            Node node = keyToNodeMap.get(key);
            makeRecentNode(node);
            return node.value;
        }
        return -1;
    }

	/**
     * Adds or updates key-value in cache.
     * Evicts least recently used item when cache is full.
     */
    public void put(int key, int value) {

        // Key exists → update and make recent
        if (keyToNodeMap.containsKey(key)) {
            Node node = keyToNodeMap.get(key);
            updateNode(node, value);
            return;
        }

        // Capacity reached → remove LRU (head)
        if (keyToNodeMap.size() == capacity) {
            evict();
        }

        // Insert new key
        addNewNode(key, value);
    }

	/** Removes least recently used node from head */
    private void evict() {
        if (head == null) {
        	return;
        }

        Node evictNode = head;
        head = head.next;

        if (head != null) {
            head.prev = null;
        }

        // Emptying the list
        if (evictNode.next == null) {
            tail = null; // list becomes empty
        }

        keyToNodeMap.remove(evictNode.key);
    }

    /** Updates existing node's value & marks it recent */
    private void updateNode(Node node, int value) {
        node.value = value;
        makeRecentNode(node);
    }

    /** Moves given node to tail (most recently used) */
    private void makeRecentNode(Node node) {

        if (node == tail) {
            return; // already most recent
        }

        // So now cache has at least 2 entries.
        if (node == head) {
            head = head.next;
            head.prev = null;
        } else {
            // Not tail and not head implies node is central. So Disconnect node from current position and connect left and right. 
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        // Attach at tail - even if it was or wasn't head earlier
        tail.next = node;
        node.prev = tail;
        node.next = null;
        tail = node;
    }
	
    /** Inserts new node at tail */
    private void addNewNode(int key, int value) {
        Node node = new Node(key, value);

        if (head == null && tail == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }

        keyToNodeMap.put(key, node);
    }

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("\n\n ========= Cache ========= \n");
		sb.append("capacity: "+ capacity);

		for (Integer key : keyToNodeMap.keySet()) {
			sb.append("\n"+key+" : "+keyToNodeMap.get(key));
		}
		sb.append("\n\n ========= Head ========= \n");
		sb.append(head == null ? "null" : head.key);
		sb.append("\n\n ========= Tail ========= \n");
		sb.append(tail == null ? "null" : tail.key);
		sb.append("\n\n ========= Linked List ========= \n");
		sb.append(head == null ? "null" : head);
		sb.append("\n==================== Done ====================");
		sb.append("\n==============================================\n\n\n");
		
		return sb.toString();
	}
}

class Node {
	public int key;
	public int value;
	public Node prev;
	public Node next;
	
	public Node(int key, int value) {
		this.key = key;
		this.value = value;
	}

	@Override
	public String toString() {
		return  " (" + key + " : " + value + ") <=>" + next;
	}	
}
