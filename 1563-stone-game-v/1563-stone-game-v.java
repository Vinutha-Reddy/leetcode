class Solution {
    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;

        int[] prefix = new int[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + stoneValue[i];
        }

        int[][] dp = new int[n][n];

        // dp[i][i] = 0 (only one stone, no split possible)

        for (int length = 2; length <= n; length++) {
            for (int i = 0; i <= n - length; i++) {
                int j = i + length - 1;

                for (int k = i; k < j; k++) {
                    int leftSum = prefix[k + 1] - prefix[i];
                    int rightSum = prefix[j + 1] - prefix[k + 1];

                    if (leftSum < rightSum) {
                        dp[i][j] = Math.max(
                                dp[i][j],
                                leftSum + dp[i][k]
                        );
                    } else if (leftSum > rightSum) {
                        dp[i][j] = Math.max(
                                dp[i][j],
                                rightSum + dp[k + 1][j]
                        );
                    } else {
                        dp[i][j] = Math.max(
                                dp[i][j],
                                leftSum + Math.max(
                                        dp[i][k],
                                        dp[k + 1][j]
                                )
                        );
                    }
                }
            }
        }

        return dp[0][n - 1];
    }
}