import java.util.HashSet;
import java.util.Set;

/**
 * Longest Substring Without Repeating Characters
 * Given a string s, find the length of the longest substring without duplicate characters.
 *
 * A substring is a contiguous sequence of characters within a string.
 *
 * Example 1:
 *
 * Input: s = "zxyzxyz"
 *
 * Output: 3
 * Explanation: The string "xyz" is the longest without duplicate characters.
 *
 * Example 2:
 *
 * Input: s = "xxxx"
 *
 * Output: 1
 *
 */

public class LongestSubstringWithoutRepeatingCharacters {

    public static void main(String[] args) {
        String s="pwwkew";
        String x = "xxxx";
        System.out.println(lengthOfLongestSubstring(s));
        System.out.println(lengthOfLongestSubstring(x));
    }

    private static int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int left = 0;
        int response = 0;

        for (int right = 0; right < s.length(); right++) {
            while (set.contains(s.charAt(right))) {
                set.remove(s.charAt(left));
                left++;
            }
            set.add(s.charAt(right));
            response = Math.max(response, right - left + 1);
        }

        return response;
    }
}
