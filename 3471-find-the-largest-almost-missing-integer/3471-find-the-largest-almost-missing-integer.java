import java.util.HashMap;
import java.util.Map;

class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;

        // Only one subarray: every element appears in exactly one subarray.
        if (k == n) {
            int max = nums[0];
            for (int x : nums) {
                max = Math.max(max, x);
            }
            return max;
        }

        // k == 1: need elements that occur exactly once in the array.
        if (k == 1) {
            Map<Integer, Integer> freq = new HashMap<>();

            for (int x : nums) {
                freq.put(x, freq.getOrDefault(x, 0) + 1);
            }

            int answer = -1;

            for (int x : nums) {
                if (freq.get(x) == 1) {
                    answer = Math.max(answer, x);
                }
            }

            return answer;
        }

        // 1 < k < n: only nums[0] and nums[n-1] can be almost missing.
        int first = nums[0];
        int last = nums[n - 1];

        boolean firstUnique = true;
        boolean lastUnique = true;

        for (int i = 1; i < n; i++) {
            if (nums[i] == first) {
                firstUnique = false;
            }
        }

        for (int i = 0; i < n - 1; i++) {
            if (nums[i] == last) {
                lastUnique = false;
            }
        }

        int answer = -1;

        if (firstUnique) {
            answer = Math.max(answer, first);
        }

        if (lastUnique) {
            answer = Math.max(answer, last);
        }

        return answer;
    }
}