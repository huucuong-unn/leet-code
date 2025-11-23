public class MissingNumber {

    /**
     * Missing Number
     * Given an array nums containing n integers in the range [0, n] without any duplicates, return the single number in the range that is missing from nums.
     *
     * Follow-up: Could you implement a solution using only O(1) extra space complexity and O(n) runtime complexity?
     *
     */

    public static void main(String[] args) {

        int[] nums = {3,0,1};

        System.out.println(missingNumber(nums)); // Output: 2
    }

    // Gauss' formula
    // Sum of first n natural numbers = n * (n + 1) / 2
    private static int missingNumber(int[] nums) {
        int n = nums.length;
        int expectedSum = n * (n + 1) / 2;
        int actualSum = 0;

        for (int num : nums) {
            actualSum += num;
        }

        return expectedSum - actualSum;
    }
}
