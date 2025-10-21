package leetCodeQuestionsPackage;

import java.util.Scanner;

public class Q4_MedianOfTwoSortedArrays {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter space separated numbers of Array #1: ");
		String str1 = sc.nextLine();
		String[] str1Array = str1.split(" ");
		int[] nums1 = new int[str1Array.length];
		for (int i=0; i<str1Array.length; ++i) {
			nums1[i] = Integer.parseInt(str1Array[i]);
		}
		
		System.out.println("Enter space separated numbers of Array #2: ");
		String str2 = sc.nextLine();
		String[] str2Array = str2.split(" ");
		int[] nums2 = new int[str2Array.length];
		for (int i=0; i<str2Array.length; ++i) {
			nums2[i] = Integer.parseInt(str2Array[i]);
		}
		
		double median = findMedianSortedArrays(nums1, nums2);
		System.out.println("==> Median: " + median);
	}
	
	public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int totalLength = nums1.length + nums2.length;
        
        int targetIndex1 = -1, targetIndex2 = -1;
        
        if (totalLength % 2 == 0 ) {
        	// Even
        	targetIndex2 = totalLength / 2;
        	targetIndex1 = targetIndex2 - 1;
        } else {
        	// Odd
        	targetIndex1 = totalLength / 2;
        }
        
        int achievedIndex = -1;
        int ptr1 = 0, ptr2 = 0;
        System.out.printf("targetIndex1: %d targetIndex2: %d", targetIndex1, targetIndex2).println();
        
        Double num1 = null, num2 = null;
        
        while (ptr1 < nums1.length || ptr2 < nums2.length) {
        	Integer minNum = null;
        	
        	if (ptr1 >= nums1.length) {
        		minNum = nums2[ptr2++];
        	} else if (ptr2 >= nums2.length) {
        		minNum = nums1[ptr1++];
        	} else {
        		// both within range
        		if (nums1[ptr1] < nums2[ptr2]) {
        			minNum = nums1[ptr1];
        			ptr1++;        		
        		} else {
        			minNum = nums2[ptr2];
        			ptr2++;
        		}        		
        	}        	
        	
        	achievedIndex ++;
        	
        	if (achievedIndex == targetIndex1) {
        		num1 = (double)minNum;
        		if (targetIndex2 == -1) {
        			return num1;
        		}
        	} else if (achievedIndex == targetIndex2) {
        		num2 = (double)minNum;
        		break;
        	}
        }
        
        return (num1 + num2)/2;
    }

}
