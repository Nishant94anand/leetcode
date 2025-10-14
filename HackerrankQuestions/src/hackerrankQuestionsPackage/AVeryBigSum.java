package hackerrankQuestionsPackage;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;
import java.util.stream.Collectors;

public class AVeryBigSum {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

        int arCount = Integer.parseInt(sc.nextLine().trim());

        List<Long> ar = Stream.of(sc.nextLine().replaceAll("\\s+$", "").split(" "))
            .map(Long::parseLong)
            .collect(java.util.stream.Collectors.toList());

        long result = aVeryBigSum(ar);
        System.out.println(result);
	}
	
	/*
     * Complete the 'aVeryBigSum' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts LONG_INTEGER_ARRAY ar as parameter.
     */

    public static long aVeryBigSum(List<Long> ar) {
        long sum = 0;
        
        for (Long num : ar) {
            sum += num;
        }
        
        return sum;   

    }

}
