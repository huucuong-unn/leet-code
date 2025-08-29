import java.util.*;

public class TopKFrequentElements {

    public static void main(String[] args) {
       int[] nums = new int[] {2,3,3,4,4,4};

       int k = 2;

        Arrays.stream(topKFrequent(nums, k)).forEach(System.out::println);
    }

    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> frequentMap = new HashMap<>();

        for (int num : nums) {
            // key: num at i - value: occurrence
            frequentMap.put(num, frequentMap.getOrDefault(num, 0) + 1);
        }

        return frequentMap.entrySet()
                .stream()
                .sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed())
                .limit(k)
                .mapToInt(Map.Entry::getKey)
                .toArray();
    }
}
