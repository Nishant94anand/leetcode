package linkedListUtilityPackage;

public class SinglyLinkedListNode<T> {
	public T data;
	public SinglyLinkedListNode<T> next;
	
	public SinglyLinkedListNode(T value) {
		super();
		this.data = value;
	}
	
	public SinglyLinkedListNode(T data, SinglyLinkedListNode<T> next) {
		this.data = data;
		this.next = next;		
	}

	@Override
	public String toString() {
		return data + " -> " +next;
	}
}