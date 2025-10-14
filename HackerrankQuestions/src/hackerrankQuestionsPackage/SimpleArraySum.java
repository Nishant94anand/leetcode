package hackerrankQuestionsPackage;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class SimpleArraySum {

	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		
		int arCount = Integer.parseInt(sc.nextLine());

        List<Integer> ar = Stream.of(sc.nextLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(java.util.stream.Collectors.toList());

        int result = simpleArraySum(ar);
        System.out.println(result);

	}

	public static int simpleArraySum(List<Integer> ar) {
		int sum = 0;
		
		for (Integer num : ar) {
			sum += num;
		}
		
		return sum;

	}

}
