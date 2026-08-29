import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;

        // Pair each value with its original index.
        List<int[]> pairs = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            pairs.add(new int[]{nums[i], i});
        }

        // Sort by value.
        Collections.sort(pairs, (a, b) -> Integer.compare(a[0], b[0]));

        int[] result = new int[n];
        int i = 0;

        while (i < n) {
            int j = i + 1;

            // Extend group while consecutive values differ by at most limit.
            while (j < n && pairs.get(j)[0] - pairs.get(j - 1)[0] <= limit) {
                j++;
            }

            // Collect original indices for this group.
            List<Integer> indices = new ArrayList<>();

            for (int k = i; k < j; k++) {
                indices.add(pairs.get(k)[1]);
            }

            Collections.sort(indices);

            // Assign smallest values to smallest original indices.
            for (int k = 0; k < indices.size(); k++) {
                result[indices.get(k)] = pairs.get(i + k)[0];
            }

            i = j;
        }

        return result;
    }
}