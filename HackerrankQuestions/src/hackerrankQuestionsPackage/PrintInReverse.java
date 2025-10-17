package hackerrankQuestionsPackage;

import java.util.Stack;

import linkedListUtilityPackage.LinkedListHelper;
import linkedListUtilityPackage.SinglyLinkedListNode;

public class PrintInReverse {

	public static void main(String[] args) {
		SinglyLinkedListNode<Integer> linkedList = LinkedListHelper.createIntegerSinglyLinkedListFromInput();
		reversePrint(linkedList);
	}

	private static void reversePrint(SinglyLinkedListNode<Integer> linkedList) {
		Stack<Integer> stack = new Stack<Integer>();
		SinglyLinkedListNode<Integer> curr = linkedList;
		
		while (curr != null) {
			stack.push(curr.data);
			curr = curr.next;
		}
		
		while (!stack.isEmpty()) {
			System.out.println(stack.pop());
		}
		
	}

}
