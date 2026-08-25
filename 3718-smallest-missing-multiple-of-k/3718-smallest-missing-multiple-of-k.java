import java.util.HashSet;
import java.util.Set;

class Solution {
    public int missingMultiple(int[] nums, int k) {
        Set<Integer> seen = new HashSet<>();

        for (int x : nums) {
            seen.add(x);
        }

        int multiple = k;

        while (seen.contains(multiple)) {
            multiple += k;
        }

        return multiple;
    }
}