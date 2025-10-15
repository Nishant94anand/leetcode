package hackerrankQuestionsPackage;

import java.util.Arrays;

import linkedListUtilityPackage.LinkedListHelper;
import linkedListUtilityPackage.SinglyLinkedListNode;

public class InsertAtIndexOfSinglyLinkedList {

	public static void main(String[] args) {
		SinglyLinkedListNode<Integer> head = LinkedListHelper.createSinglyLinkedList(Arrays.asList(1,2,3));
		System.out.println("Initial LinkedList: " + head);
		int data = 55;
		int position = 1;
		
		SinglyLinkedListNode<Integer> newHead = insertNodeAtPosition(head, data, position);
		System.out.println("Final LinkedList: " + newHead);

	}
	
	public static SinglyLinkedListNode<Integer> insertNodeAtPosition(SinglyLinkedListNode<Integer> rootNode, int data, int position) {
        if (rootNode == null) {
        	if (position == 0) {
        		return new SinglyLinkedListNode<Integer>(data);
        	} else {
        		return null;
        	}
        }
        
        SinglyLinkedListNode<Integer> newNode = new SinglyLinkedListNode<Integer>(data);
        SinglyLinkedListNode<Integer> currNode = rootNode;
        
        int currIndex = 0;
        
        while (currNode.next != null && currIndex < position - 1) {
        	currNode = currNode.next;
        	currIndex++;
        }
        
        if (currIndex != position -1) {
        	return null;
        }
        
        newNode.next = currNode.next;
        currNode.next = newNode;
        
        return rootNode;

    }

}
