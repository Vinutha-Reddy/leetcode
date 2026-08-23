class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int half = n / 2;

        int sum1 = 0;
        int sum2 = 0;
        int q1 = 0;
        int q2 = 0;

        for (int i = 0; i < half; i++) {
            char c = num.charAt(i);

            if (c == '?') {
                q1++;
            } else {
                sum1 += c - '0';
            }
        }

        for (int i = half; i < n; i++) {
            char c = num.charAt(i);

            if (c == '?') {
                q2++;
            } else {
                sum2 += c - '0';
            }
        }

        int totalQ = q1 + q2;

        // Odd number of '?' -> Alice gets the last move and wins.
        if (totalQ % 2 == 1) {
            return true;
        }

        // Bob wins only if the current difference can be exactly balanced.
        int diffSum = sum1 - sum2;
        int diffQ = q1 - q2;

        // Required balance: diffSum == 9 * (q2 - q1) / 2
        // => 2 * diffSum == 9 * (q2 - q1)
        return 2 * diffSum != 9 * (q2 - q1);
    }
}