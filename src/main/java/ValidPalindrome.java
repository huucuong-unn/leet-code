public class ValidPalindrome {

    /**
     *
     * Given a string s, return true if it is a palindrome, otherwise return false.
     *
     * A palindrome is a string that reads the same forward and backward. It is also case-insensitive and ignores all non-alphanumeric characters.
     *
     * Note: Alphanumeric characters consist of letters (A-Z, a-z) and numbers (0-9).
     *
     * Example 1:
     *
     * Input: s = "Was it a car or a cat I saw?"
     *
     * Output: true
     * Explanation: After considering only alphanumerical characters we have "wasitacaroracatisaw", which is a palindrome.
     *
     * Example 2:
     *
     * Input: s = "tab a cat"
     *
     * Output: false
     * Explanation: "tabacat" is not a palindrome.
     */

    public static void main(String[] args) {
        String testString = "Was it a car or a cat I saw?";
        System.out.println(isPalindrome(testString));

    }

    // Using reverse String approach of String Builder
    private static boolean isPalindrome(String str) {
        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                sb.append(Character.toLowerCase(c));
            }
        }

        return sb.toString().equals(sb.reverse().toString());
    }
}
