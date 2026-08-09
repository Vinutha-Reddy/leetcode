import java.util.Arrays;

class Solution {
    private int[] suffixSum;
    private int[][] memo;
    private int n;

    public int stoneGameII(int[] piles) {
        n = piles.length;

        suffixSum = new int[n + 1];

        for (int i = n - 1; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + piles[i];
        }

        memo = new int[n][n + 1];

        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        return dfs(0, 1);
    }

    private int dfs(int index, int m) {
        if (index >= n) {
            return 0;
        }

        // The player can take all remaining piles.
        if (2 * m >= n - index) {
            return suffixSum[index];
        }

        if (memo[index][m] != -1) {
            return memo[index][m];
        }

        int best = 0;

        /*
         * Try taking x piles, where 1 <= x <= 2 * m.
         */
        for (int x = 1; x <= 2 * m && index + x <= n; x++) {
            int nextM = Math.max(m, x);

            int opponentScore =
                    dfs(index + x, nextM);

            int currentPlayerScore =
                    suffixSum[index] - opponentScore;

            best = Math.max(best, currentPlayerScore);
        }

        return memo[index][m] = best;
    }
}