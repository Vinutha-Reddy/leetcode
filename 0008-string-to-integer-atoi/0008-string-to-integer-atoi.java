class Solution {
    public int myAtoi(String s) {
        int n = s.length();
        int i = 0;

        // Skip leading whitespace.
        while (i < n && s.charAt(i) == ' ') {
            i++;
        }

        if (i == n) {
            return 0;
        }

        int sign = 1;

        if (s.charAt(i) == '+' || s.charAt(i) == '-') {
            if (s.charAt(i) == '-') {
                sign = -1;
            }
            i++;
        }

        int result = 0;
        int limit = Integer.MAX_VALUE / 10;

        while (i < n) {
            char c = s.charAt(i);

            if (c < '0' || c > '9') {
                break;
            }

            int digit = c - '0';

            // Check for overflow before updating result.
            if (result > limit
                    || (result == limit && digit > 7)) {
                return sign == 1
                        ? Integer.MAX_VALUE
                        : Integer.MIN_VALUE;
            }

            result = result * 10 + digit;
            i++;
        }

        return result * sign;
    }
}