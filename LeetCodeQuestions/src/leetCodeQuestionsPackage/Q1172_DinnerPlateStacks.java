package leetCodeQuestionsPackage;

import java.util.Stack;

public class Q1172_DinnerPlateStacks {

	public static void main(String[] args) {
		int capacity = 2;
		DinnerPlates obj = new DinnerPlates(0);

	}

}

class DinnerPlates {
	int[] db;
	int capacity;
	Stack<Integer> empty;
	int rightMostFull;

    public DinnerPlates(int capacity) {
        db = new int[Integer.MAX_VALUE];
        this.capacity = capacity;
        empty = new Stack<Integer>();
    }
    
    public void push(int val) {
    	
        
    }
    
    public int pop() {
        return 0;
    }
    
    public int popAtStack(int index) {
        return 0;
    }
}
