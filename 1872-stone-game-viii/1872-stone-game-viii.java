class Solution {
    public int stoneGameVIII(int[] stones) {
        int n = stones.length;

        // Build prefix sums in-place.
        for (int i = 1; i < n; i++) {
            stones[i] += stones[i - 1];
        }

        // dp represents the best score difference the current player can achieve
        // starting from the current position.
        int dp = stones[n - 1];

        // Iterate from right to left, starting from the last valid move position.
        for (int i = n - 2; i >= 1; i--) {
            dp = Math.max(stones[i] - dp, dp);
        }

        return dp;
    }
}