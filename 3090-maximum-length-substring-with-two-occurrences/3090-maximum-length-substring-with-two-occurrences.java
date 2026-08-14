class Solution {
    public int maximumLengthSubstring(String s) {
        int[] frequency = new int[26];

        int left = 0;
        int answer = 0;

        for (int right = 0; right < s.length(); right++) {
            int index = s.charAt(right) - 'a';

            frequency[index]++;

            while (frequency[index] > 2) {
                int leftIndex = s.charAt(left) - 'a';

                frequency[leftIndex]--;
                left++;
            }

            answer = Math.max(answer, right - left + 1);
        }

        return answer;
    }
}