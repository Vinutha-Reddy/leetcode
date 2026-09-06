class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();

        // dp[i][j] = number of distinct subsequences of s[0..i) equal to t[0..j)
        int[][] dp = new int[m + 1][n + 1];

        // Base case: empty t can be formed in exactly one way from any prefix of s.
        for (int i = 0; i <= m; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // Option 1: skip s[i-1]
                dp[i][j] = dp[i - 1][j];

                // Option 2: if characters match, use s[i-1] to match t[j-1]
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] += dp[i - 1][j - 1];
                }
            }
        }

        return dp[m][n];
    }
}