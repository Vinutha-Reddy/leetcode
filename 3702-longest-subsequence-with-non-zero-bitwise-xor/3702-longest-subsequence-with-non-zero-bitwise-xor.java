class Solution {
    public int longestSubsequence(int[] nums) {
        int totalXor = 0;
        int zeroCount = 0;

        for (int value : nums) {
            totalXor ^= value;

            if (value == 0) {
                zeroCount++;
            }
        }

        if (totalXor != 0) {
            return nums.length;
        }

        if (zeroCount == nums.length) {
            return 0;
        }

        return nums.length - 1;
    }
}