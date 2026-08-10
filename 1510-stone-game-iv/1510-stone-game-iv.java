class Solution {
    public boolean winnerSquareGame(int n) {
        boolean[] dp = new boolean[n + 1];

        for (int stones = 1; stones <= n; stones++) {
            for (int squareRoot = 1;
                 squareRoot * squareRoot <= stones;
                 squareRoot++) {

                int square = squareRoot * squareRoot;

                if (!dp[stones - square]) {
                    dp[stones] = true;
                    break;
                }
            }
        }

        return dp[n];
    }
}