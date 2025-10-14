package hackerrankQuestionsPackage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

public class MigratoryBird {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

        int arrCount = Integer.parseInt(sc.nextLine().trim());

        List<Integer> arr = Stream.of(sc.nextLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(java.util.stream.Collectors.toList());

        int result = migratoryBirds(arr);

	}
	
	public static int migratoryBirds(List<Integer> arr) {
        Map<Integer, Integer> idToCountMap = new HashMap<>();
        int maxId = -1, maxCount = 0;
        
        
        for(Integer id : arr) {
            int count = idToCountMap.containsKey(id) ? idToCountMap.get(id) + 1 : 1;
            idToCountMap.put(id, count);
            
            if (count > maxCount) {
                maxId = id;
                maxCount = count;
            } else if (count == maxCount) {
                maxId = Math.min(maxId, id);
            }
        }
        
        return maxId;
    }

}
