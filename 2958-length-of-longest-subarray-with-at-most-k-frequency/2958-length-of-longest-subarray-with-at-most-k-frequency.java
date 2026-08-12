import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        Map<Integer, Integer> frequency = new HashMap<>();

        int left = 0;
        int answer = 0;

        for (int right = 0; right < nums.length; right++) {
            int value = nums[right];

            frequency.put(
                    value,
                    frequency.getOrDefault(value, 0) + 1
            );

            /*
             * Only nums[right] can newly violate the condition,
             * so shrink until its frequency is at most k.
             */
            while (frequency.get(value) > k) {
                int leftValue = nums[left];

                frequency.put(
                        leftValue,
                        frequency.get(leftValue) - 1
                );

                left++;
            }

            answer = Math.max(answer, right - left + 1);
        }

        return answer;
    }
}