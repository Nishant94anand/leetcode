package hackerrankQuestionsPackage;

import java.util.Arrays;

import linkedListUtilityPackage.LinkedListHelper;
import linkedListUtilityPackage.SinglyLinkedListNode;

public class InsertAtTailOfSinglyLinkedList {

	public static void main(String[] args) {
		SinglyLinkedListNode<Integer> head = LinkedListHelper.createSinglyLinkedList(Arrays.asList(1,2,3));
		int data = 55;
		
		SinglyLinkedListNode<Integer> newHead = insertNodeAtTail(head, data);
		System.out.println(newHead);
	}
	
	static SinglyLinkedListNode<Integer> insertNodeAtTail(SinglyLinkedListNode<Integer> head, int data) {
		if (head == null) {
			return new SinglyLinkedListNode<Integer>(data);
		}
		
		SinglyLinkedListNode<Integer> curr = head;
		SinglyLinkedListNode<Integer> newNode = new SinglyLinkedListNode<Integer>(data);
		
		while (curr.next != null) {
			curr = curr.next;
		}
		
		curr.next = newNode;
		
		return head;
    }

}
