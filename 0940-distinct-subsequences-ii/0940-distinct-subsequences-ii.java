class Solution {
    public int distinctSubseqII(String s) {
        final int MOD = 1_000_000_007;
        int n = s.length();

        // dp[i] = number of distinct subsequences (including empty) using first i chars
        int[] dp = new int[n + 1];
        dp[0] = 1; // empty subsequence

        int[] last = new int[26];
        java.util.Arrays.fill(last, -1);

        for (int i = 1; i <= n; i++) {
            char c = s.charAt(i - 1);
            int idx = c - 'a';

            dp[i] = (dp[i - 1] * 2) % MOD;

            if (last[idx] != -1) {
                dp[i] = (dp[i] - dp[last[idx]] + MOD) % MOD;
            }

            last[idx] = i - 1;
        }

        // Subtract the empty subsequence
        return (dp[n] - 1 + MOD) % MOD;
    }
}