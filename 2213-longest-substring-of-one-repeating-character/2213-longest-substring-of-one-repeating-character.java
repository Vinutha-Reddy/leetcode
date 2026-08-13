class Solution {
    private int[] prefix;
    private int[] suffix;
    private int[] best;
    private int[] leftChar;
    private int[] rightChar;

    public int[] longestRepeating(
            String s,
            String queryCharacters,
            int[] queryIndices) {

        int n = s.length();
        int q = queryIndices.length;

        prefix = new int[4 * n];
        suffix = new int[4 * n];
        best = new int[4 * n];
        leftChar = new int[4 * n];
        rightChar = new int[4 * n];

        build(1, 0, n - 1, s);

        int[] answer = new int[q];

        for (int i = 0; i < q; i++) {
            int index = queryIndices[i];
            int character = queryCharacters.charAt(i) - 'a';

            update(1, 0, n - 1, index, character);
            answer[i] = best[1];
        }

        return answer;
    }

    private void build(int node, int left, int right, String s) {
        if (left == right) {
            int character = s.charAt(left) - 'a';

            prefix[node] = 1;
            suffix[node] = 1;
            best[node] = 1;
            leftChar[node] = character;
            rightChar[node] = character;

            return;
        }

        int mid = left + (right - left) / 2;

        build(node * 2, left, mid, s);
        build(node * 2 + 1, mid + 1, right, s);

        merge(node, mid - left + 1, right - mid);
    }

    private void update(
            int node,
            int left,
            int right,
            int index,
            int character) {

        if (left == right) {
            prefix[node] = 1;
            suffix[node] = 1;
            best[node] = 1;
            leftChar[node] = character;
            rightChar[node] = character;

            return;
        }

        int mid = left + (right - left) / 2;

        if (index <= mid) {
            update(node * 2, left, mid, index, character);
        } else {
            update(node * 2 + 1, mid + 1, right, index, character);
        }

        merge(node, mid - left + 1, right - mid);
    }

    private void merge(
            int node,
            int leftLength,
            int rightLength) {

        int leftNode = node * 2;
        int rightNode = node * 2 + 1;

        leftChar[node] = leftChar[leftNode];
        rightChar[node] = rightChar[rightNode];

        prefix[node] = prefix[leftNode];
        if (prefix[leftNode] == leftLength
                && rightChar[leftNode] == leftChar[rightNode]) {
            prefix[node] = leftLength + prefix[rightNode];
        }

        suffix[node] = suffix[rightNode];
        if (suffix[rightNode] == rightLength
                && rightChar[leftNode] == leftChar[rightNode]) {
            suffix[node] = rightLength + suffix[leftNode];
        }

        best[node] = Math.max(best[leftNode], best[rightNode]);

        if (rightChar[leftNode] == leftChar[rightNode]) {
            best[node] = Math.max(
                    best[node],
                    suffix[leftNode] + prefix[rightNode]
            );
        }
    }
}