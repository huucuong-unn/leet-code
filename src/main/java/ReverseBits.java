import java.util.Stack;

/**
 * Reverse bits of a given 32 bits signed integer.
 * Input: n = 43261596
 * Output: 964176192
 */

public class ReverseBits {
    public static void main(String[] args) {

        System.out.println(reverseBits(43261596)); // Output: 964176192
    }

    private static int reverseBits(int n) {

        String s = convertIntTo32BitBinaryStringReversed(n);
        System.out.println(s);
        return convert32BitBinaryStringToInt(s);
    }

    private static String convertIntTo32BitBinaryStringReversed(int n) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 32; i++) {
            int b = n % 2;
            sb.append(b);
            n = n / 2;
        }

        return sb.toString();
    }


    private static int convert32BitBinaryStringToInt(String s) {
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                result += Math.pow(2, 31 - i);
            }
        }

        return result;
    }
}