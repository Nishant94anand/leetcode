package hackerrankQuestionsPackage;

import linkedListUtilityPackage.LinkedListHelper;
import linkedListUtilityPackage.SinglyLinkedListNode;

public class DeleteANode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SinglyLinkedListNode<Integer> linkedList = LinkedListHelper.createIntegerSinglyLinkedListFromInput();
		int index = 0;
		
		deleteANode(linkedList, index);
		System.out.println("==> Linked List: " + linkedList);

	}

	private static void deleteANode(SinglyLinkedListNode<Integer> linkedList, int index) {
		// TODO Auto-generated method stub
		if (linkedList == null) {
			return;
		}
		
		if (index == 0) {
			linkedList = linkedList.next;
			return;
		}
		
		SinglyLinkedListNode<Integer> curr = linkedList;
		int currIndex = 0;
		
		while (currIndex < index-1) {
			if (curr == null) {
				return;
			}
			curr = curr.next;
			currIndex++;
		}
		
		if (curr.next == null) {
			return;
		}
		
		curr.next = curr.next.next;
	}

}
