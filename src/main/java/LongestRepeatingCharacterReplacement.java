import java.util.HashMap;
import java.util.Map;

public class LongestRepeatingCharacterReplacement {

    public static void main(String[] args) {
        String s = "AABABBA";
        int k = 1;
        System.out.println(characterReplacement(s, k)); // Output: 4
    }

    public static int characterReplacement(String s, int k) {

        Map<Character, Integer> map = new HashMap<>();
        int left = 0;
        int maxFrequent = 0;
        int response = 0;

        for (int right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);
            map.put(rightChar, map.getOrDefault(rightChar, 0) + 1); // count frequency of char at right pointer
            maxFrequent = Math.max(maxFrequent, map.get(rightChar)); // track the max frequency of a single char in the current window

            // current window size is (right - left + 1)
            // if we need to replace more than k chars, shrink the window from the left
            while ((right - left + 1) - maxFrequent > k) {
                map.put(s.charAt(left), map.get(s.charAt(left)) - 1);
                left++;
            }

            response = Math.max(response, right - left + 1);
        }

        return response;
    }
}
