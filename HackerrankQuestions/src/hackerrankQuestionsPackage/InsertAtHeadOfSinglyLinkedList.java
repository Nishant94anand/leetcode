package hackerrankQuestionsPackage;

import java.util.Arrays;

import linkedListUtilityPackage.LinkedListHelper;
import linkedListUtilityPackage.SinglyLinkedListNode;

public class InsertAtHeadOfSinglyLinkedList {

	public static void main(String[] args) {
		SinglyLinkedListNode<Integer> head = LinkedListHelper.createSinglyLinkedList(Arrays.asList(1,2,3));
		System.out.println("Initial LinkedList: " + head);
		int data = 55;
		
		SinglyLinkedListNode<Integer> newHead = insertNodeAtHead(head, data);
		System.out.println("Final LinkedList: " + newHead);

	}
	
	static SinglyLinkedListNode<Integer> insertNodeAtHead(SinglyLinkedListNode<Integer> rootNode, int data) {
		return new SinglyLinkedListNode<Integer>(data, rootNode);
    }

}
