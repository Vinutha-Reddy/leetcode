class Solution {
    public String lexGreaterPermutation(String s, String target) {
        // "smallest" that is strictly greater than
        // it means we match as far as we can
        // then when we can't match anymore, we go up.
        // after we go up on one, we do the rest by minimal lexicographical ordering.

        // 1. Count characters in s
        int[] sCount = new int[26];

        for (int i = 0; i < s.length(); i++) {
            sCount[s.charAt(i) - 'a']++;
        }

        // 2. Match as far as possible
        // Think of count in s as your bank, then greedily pay out.
        int startIndex = 0;

        while (startIndex < target.length()
                && sCount[target.charAt(startIndex) - 'a'] > 0) {

            sCount[target.charAt(startIndex) - 'a']--;
            startIndex++;
        }

        // 3. Try to make the result lexicographically greater
        for (int i = startIndex; i >= 0; i--) {

            if (i < startIndex) {
                // Pay back the character
                sCount[target.charAt(i) - 'a']++;
            }

            if (i < s.length()) {
                int targetChar = target.charAt(i) - 'a';

                // Find the smallest character greater than target[i]
                for (int c = targetChar + 1; c < 26; c++) {

                    if (sCount[c] > 0) {
                        StringBuilder result = new StringBuilder();

                        // Keep the prefix unchanged
                        result.append(target.substring(0, i));

                        // Add the smallest character greater than target[i]
                        result.append((char) (c + 'a'));
                        sCount[c]--;

                        // Arrange remaining characters in lexicographical order
                        for (int j = 0; j < 26; j++) {
                            while (sCount[j] > 0) {
                                result.append((char) (j + 'a'));
                                sCount[j]--;
                            }
                        }

                        return result.toString();
                    }
                }
            }
        }

        // No valid lexicographically greater permutation exists
        return "";
    }
}