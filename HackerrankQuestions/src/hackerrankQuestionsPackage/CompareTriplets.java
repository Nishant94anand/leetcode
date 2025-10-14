package hackerrankQuestionsPackage;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class CompareTriplets {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		List<Integer> a = Stream.of(sc.nextLine().replaceAll("\\s+$", "").split(" ")).map(Integer::parseInt)
				.collect(java.util.stream.Collectors.toList());

		List<Integer> b = Stream.of(sc.nextLine().replaceAll("\\s+$", "").split(" ")).map(Integer::parseInt)
				.collect(java.util.stream.Collectors.toList());

		List<Integer> result = compareTriplets(a, b);

		System.out.println(result);

	}

	public static List<Integer> compareTriplets(List<Integer> a, List<Integer> b) {
		int alice = 0, bob = 0;

		for (int i = 0; i < 3; ++i) {
			int aliceRate = a.get(i);
			int bobRate = b.get(i);
			if (aliceRate > bobRate) {
				alice++;
			} else if (aliceRate < bobRate) {
				bob++;
			}
		}

		return List.of(alice, bob);

	}

}
