package linkedListUtilityPackage;

import java.util.List;

public class LinkedListHelper {
	
	public static <T> SinglyLinkedListNode<T> createSinglyLinkedList(List<T> list) {
		if (list == null || list.size() == 0) {
			return null;
		}
		
		SinglyLinkedListNode<T> rootNode = null;
		SinglyLinkedListNode<T> prev = null;
		
		for (T value : list) {
			SinglyLinkedListNode<T> node = new SinglyLinkedListNode<>(value);
			
			if (rootNode == null) {
				rootNode = node;
			} else {
				prev.next = node;
			}
			
			prev = node;
		}
		
		return rootNode;
		
	}

}