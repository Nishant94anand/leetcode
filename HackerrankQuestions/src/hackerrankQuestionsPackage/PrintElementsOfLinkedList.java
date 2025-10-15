package hackerrankQuestionsPackage;

import java.util.Arrays;

import linkedListUtilityPackage.LinkedListHelper;
import linkedListUtilityPackage.SinglyLinkedListNode;

public class PrintElementsOfLinkedList {

	public static void main(String[] args) {
		LinkedListHelper.createSinglyLinkedList(Arrays.asList(1,2,3));
	}
	
	static void printLinkedList(SinglyLinkedListNode<Integer> head) {
        while (head != null) {
            System.out.println(head.data);
            head = head.next;
        }

    }

}
