
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ValidAnagram {

    public static void main(String[] args) {
        String s1 = "racecar";
        String t1 = "carrace";

        System.out.println("Success case - Is Anagram: " + isAnagramUsingHashMap(s1, t1));

        String s2 = "jar";
        String t2 = "jam";

        System.out.println("Failed case - Is Anagram: " + isAnagramUsingHashMap(s2, t2));
    }

    // Brute force - O(nlogn + mlogm)
    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        char[] sArray = s.toCharArray();
        char[] tArray = t.toCharArray();
        Arrays.sort(sArray);
        Arrays.sort(tArray);

        return Arrays.equals(sArray, tArray);
    }

    // Hash Map - O(n+m)
    public static boolean isAnagramUsingHashMap(String s, String t) {

        if (s.length() != t.length()) {
            return false;
        }

        Map<Character, Integer> sMap = new HashMap<>();
        Map<Character, Integer> tMap = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            sMap.put(s.charAt(i), sMap.getOrDefault(s.charAt(i), 0) + 1);
            tMap.put(t.charAt(i), tMap.getOrDefault(t.charAt(i), 0) + 1);
        }

        return sMap.equals(tMap);
    }

    /*
Group Anagrams (Medium)
    Nhóm các chuỗi là anagram (các ký tự giống nhau, khác thứ tự).
    public List<List<String>> groupAnagrams(String[] strs) {
    Map<String, List<String>> map = new HashMap<>();
    for (String s : strs) {
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        String key = new String(arr);
        map.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
    }
    return new ArrayList<>(map.values());
}
     */
}
