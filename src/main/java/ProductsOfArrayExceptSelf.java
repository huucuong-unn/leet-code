import java.util.Arrays;

public class ProductsOfArrayExceptSelf {

    /***
     *  Products of Array Except Self
     * Given an integer array nums, return an array output where output[i] is the product of all the elements of nums except nums[i].
     *
     * Each product is guaranteed to fit in a 32-bit integer.
     *
     * Follow-up: Could you solve it in O(n) time without using the division operation?
     *
     * Example 1:
     *
     * Input: nums = [1,2,4,6]
     *
     * Output: [48,24,12,8]
     * Example 2:
     *
     * Input: nums = [-1,0,1,2,3]
     *
     * Output: [0,-6,0,0,0]
     * Constraints:
     *
     * 2 <= nums.length <= 1000
     * -20 <= nums[i] <= 20
     */

    public static void main(String[] args) {
        int[] nums = {0,4,0};

        Arrays.stream(productExceptSelf(nums)).forEach(System.out::println);
    }

    public static int[] productExceptSelf(int[] nums) {
        int product = 1;
        int[] response = new int[nums.length];
        int zeros = 0;

        for (int num : nums) {
            if (num != 0) {
                product *= num;
            } else {
                zeros += 1;
            }
        }

        if (zeros > 1) {
            return response;
        }


        for (int i = 0; i < nums.length; i++) {
            if (zeros == 1) {
                response[i] = (nums[i] == 0) ? product : 0;
            } else {
                response[i] = product / nums[i];
            }
        }

        return response;
    }

}
