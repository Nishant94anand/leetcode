package leetCodeQuestionsPackage;

import java.util.Scanner;

public class Q6_ZigZagConversion {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("String: ");
		String s = sc.nextLine();
		
		System.out.println("No. of Rows: ");
		int numRows = sc.nextInt();
		
		String encryptedString = convert(s, numRows);
		
		System.out.println(encryptedString);
	}

	public static String convert(String s, int numRows) {
		if (numRows == 1 || numRows >= s.length()) {
			return s;
		}
		StringBuilder[] sbArray = new StringBuilder[numRows];
		
		for(int i=0; i<numRows; ++i) {
			sbArray[i] = new StringBuilder();
		}
		
		int rowNum = 0;
		boolean increaseRow = true;
		
		for (int i=0; i<s.length(); ++i) {
			char ch = s.charAt(i);
			sbArray[rowNum].append(ch);
			
			if (rowNum == 0) {
				increaseRow = true;
			} else if (rowNum == numRows - 1) {
				increaseRow = false;
			}
			
			if (increaseRow) {
				rowNum++;
			} else {
				rowNum--;
			}
		}
		
		StringBuilder finalSb = new StringBuilder();
		for (int i=0; i<numRows; ++i) {
			StringBuilder sb = sbArray[i];
			if (sb.isEmpty()) {
				break;
			}
			
			finalSb.append(sb);
		}
		
		return finalSb.toString();
	}

}
