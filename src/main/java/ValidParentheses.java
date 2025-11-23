import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ValidParentheses {

    /**
     * Valid Parentheses - Easy
     * You are given a string s consisting of the following characters: '(', ')', '{', '}', '[' and ']'.
     *
     * The input string s is valid if and only if:
     *
     * Every open bracket is closed by the same type of close bracket.
     * Open brackets are closed in the correct order.
     * Every close bracket has a corresponding open bracket of the same type.
     * Return true if s is a valid string, and false otherwise.
     *
     * Example 1:
     *
     * Input: s = "[]"
     *
     * Output: true
     * Example 2:
     *
     * Input: s = "([{}])"
     *
     * Output: true
     * Example 3:
     *
     * Input: s = "[(])"
     *
     * Output: false
     * Explanation: The brackets are not closed in the correct order.
     */

    public static void main(String[] args) {
        String s = "{([])}";
        System.out.println(isValid(s));
        System.out.println(isValidP(s));
    }

    private static boolean isValid(String s) {
        Map<Character, Character> closeToOpen = new HashMap<>();
        closeToOpen.put(')', '(');
        closeToOpen.put('}', '{');
        closeToOpen.put(']', '[');

        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (closeToOpen.containsKey(c)) {
                if (!stack.isEmpty() && stack.peek() == closeToOpen.get(c)) {
                    stack.pop();
                } else {
                     return false;
                }
            } else {
                stack.push(c);
            }
        }

        return stack.isEmpty();
    }

    public static boolean isValidP(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(') stack.push(')');
            else if (c == '{') stack.push('}');
            else if (c == '[') stack.push(']');
            else if (stack.isEmpty() || stack.pop() != c) return false;
        }
        return stack.isEmpty();
    }
}
