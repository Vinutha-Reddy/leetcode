import java.util.HashSet;
import java.util.Set;

class Solution {
    public int missingInteger(int[] nums) {
        int sum = nums[0];

        // Find the sum of the longest sequential prefix.
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1] + 1) {
                sum += nums[i];
            } else {
                break;
            }
        }

        Set<Integer> seen = new HashSet<>();

        for (int value : nums) {
            seen.add(value);
        }

        // Find the smallest missing integer >= sum.
        while (seen.contains(sum)) {
            sum++;
        }

        return sum;
    }
}