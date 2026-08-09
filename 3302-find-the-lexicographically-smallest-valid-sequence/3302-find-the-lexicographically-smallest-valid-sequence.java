import java.util.Arrays;

class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        int[] suffixStart = new int[m];
        Arrays.fill(suffixStart, -1);

        int i = n - 1;
        int j = m - 1;

        while (i >= 0 && j >= 0) {
            if (word1.charAt(i) == word2.charAt(j)) {
                suffixStart[j] = i;
                j--;
            }
            i--;
        }

        int[] answer = new int[m];
        int answerSize = 0;
        int mismatchesUsed = 0;

        for (i = 0; i < n && answerSize < m; i++) {
            j = answerSize;

            boolean charactersMatch =
                    word1.charAt(i) == word2.charAt(j);

            boolean canUseMismatch =
                    mismatchesUsed == 0
                    && (
                        j == m - 1
                        || (
                            suffixStart[j + 1] != -1
                            && i < suffixStart[j + 1]
                        )
                    );

            if (!charactersMatch && !canUseMismatch) {
                continue;
            }

            answer[answerSize++] = i;

            if (!charactersMatch) {
                mismatchesUsed++;
            }
        }

        if (answerSize != m) {
            return new int[0];
        }

        return answer;
    }
}