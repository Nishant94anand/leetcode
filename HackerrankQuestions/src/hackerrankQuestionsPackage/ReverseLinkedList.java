package hackerrankQuestionsPackage;

import linkedListUtilityPackage.LinkedListHelper;
import linkedListUtilityPackage.SinglyLinkedListNode;

public class ReverseLinkedList {

	public static void main(String[] args) {
		SinglyLinkedListNode<Integer> linkedList = LinkedListHelper.createIntegerSinglyLinkedListFromInput();
		SinglyLinkedListNode<Integer> reversedLinkedList =reverseLinkedList(linkedList);
		System.out.println("==> Reversed Linked List: " + reversedLinkedList);
	}

	private static SinglyLinkedListNode<Integer> reverseLinkedList(SinglyLinkedListNode<Integer> linkedList) {
		if (linkedList == null || linkedList.next == null) {
			return linkedList;
		}
		
		SinglyLinkedListNode<Integer> prev = null;
		SinglyLinkedListNode<Integer> curr = linkedList;
		
		while (curr != null) {
			SinglyLinkedListNode<Integer> next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}
		
		return prev;		
	}

}
