class Solution {
    private int n;
    private int[] freq;
    private char middle;
    private String target;
    private String answer;

    public String lexPalindromicPermutation(String s, String target) {
        n = s.length();
        this.target = target;

        freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        int oddCount = 0;
        middle = 0;

        for (int i = 0; i < 26; i++) {
            if ((freq[i] & 1) == 1) {
                oddCount++;
                middle = (char) ('a' + i);
            }
        }

        // More than one odd frequency -> no palindrome possible.
        if (oddCount > 1) {
            return "";
        }

        // Use half frequencies for the left half.
        for (int i = 0; i < 26; i++) {
            freq[i] /= 2;
        }

        answer = null;
        char[] left = new char[n / 2];

        backtrack(left, 0);

        return answer == null ? "" : answer;
    }

    private void backtrack(char[] left, int pos) {
        if (answer != null) {
            return; // Already found the smallest valid palindrome.
        }

        if (pos == left.length) {
            // Build full palindrome and compare with target.
            StringBuilder sb = new StringBuilder();

            sb.append(left);

            if (middle != 0) {
                sb.append(middle);
            }

            for (int i = left.length - 1; i >= 0; i--) {
                sb.append(left[i]);
            }

            String candidate = sb.toString();

            if (candidate.compareTo(target) > 0) {
                answer = candidate;
            }

            return;
        }

        // Try characters in increasing order.
        for (int c = 0; c < 26; c++) {
            if (freq[c] == 0) {
                continue;
            }

            // Pruning: if we are already strictly greater than target's prefix,
            // any completion will be greater; otherwise, we must not go below target[pos].
            char currentChar = (char) ('a' + c);

            if (currentChar < target.charAt(pos) && !isAlreadyGreater(left, pos)) {
                continue;
            }

            left[pos] = currentChar;
            freq[c]--;

            backtrack(left, pos + 1);

            freq[c]++;

            if (answer != null) {
                return;
            }
        }
    }

    // Check whether the current prefix is already strictly greater than target's prefix.
    private boolean isAlreadyGreater(char[] left, int pos) {
        for (int i = 0; i <= pos; i++) {
            char lc = left[i];
            char tc = target.charAt(i);

            if (lc > tc) {
                return true;
            }

            if (lc < tc) {
                return false;
            }
        }

        return false;
    }
}