class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        char[] result = new char[n];
        int pos = 0;

        while (pos < n) {
            char t = target.charAt(pos);
            int tIdx = t - 'a';

            // Try to keep target[pos] if available.
            if (freq[tIdx] > 0) {
                freq[tIdx]--;

                if (canBeatSuffix(freq, target, pos + 1)) {
                    result[pos] = t;
                    pos++;
                    continue;
                }

                // Cannot beat suffix, backtrack this choice.
                freq[tIdx]++;
            }

            // Try the smallest character strictly greater than target[pos].
            int nextChar = -1;

            for (int c = tIdx + 1; c < 26; c++) {
                if (freq[c] > 0) {
                    nextChar = c;
                    break;
                }
            }

            if (nextChar != -1) {
                // Place this larger character.
                freq[nextChar]--;
                result[pos] = (char) ('a' + nextChar);

                // Fill the rest in ascending order.
                int idx = pos + 1;

                for (int c = 0; c < 26; c++) {
                    while (freq[c] > 0) {
                        result[idx++] = (char) ('a' + c);
                        freq[c]--;
                    }
                }

                return new String(result);
            }

            // No valid character at this position: backtrack.
            if (pos == 0) {
                return "";
            }

            // Undo the previous character and try a larger one there.
            pos--;
            char prev = result[pos];
            freq[prev - 'a']++;
        }

        // We matched target exactly; need something strictly greater.
        // Try to increase the last position.
        char last = result[n - 1];
        freq[last - 'a']++;

        int nextChar = -1;

        for (int c = (last - 'a') + 1; c < 26; c++) {
            if (freq[c] > 0) {
                nextChar = c;
                break;
            }
        }

        if (nextChar == -1) {
            return "";
        }

        freq[nextChar]--;
        result[n - 1] = (char) ('a' + nextChar);

        int idx = n - 1;

        for (int c = 0; c < 26; c++) {
            while (freq[c] > 0) {
                result[idx++] = (char) ('a' + c);
                freq[c]--;
            }
        }

        return new String(result);
    }

    // Check whether the remaining frequencies can form a suffix
    // strictly greater than target[start..].
    private boolean canBeatSuffix(int[] freq, String target, int start) {
        int n = target.length();
        int[] copy = freq.clone();

        // Build the largest possible suffix from remaining chars.
        StringBuilder largest = new StringBuilder();

        for (int c = 25; c >= 0; c--) {
            while (copy[c] > 0) {
                largest.append((char) ('a' + c));
                copy[c]--;
            }
        }

        if (largest.length() < n - start) {
            return false;
        }

        String suffix = largest.toString();

        // Compare largest suffix with target[start..].
        for (int i = 0; i < n - start; i++) {
            char lc = suffix.charAt(i);
            char tc = target.charAt(start + i);

            if (lc > tc) {
                return true;
            }

            if (lc < tc) {
                return false;
            }
        }

        // They are equal, so not strictly greater.
        return false;
    }
}