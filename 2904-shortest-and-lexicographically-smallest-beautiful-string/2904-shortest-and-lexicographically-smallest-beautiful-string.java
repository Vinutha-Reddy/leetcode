class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        String answer = "";

        int left = 0;
        int ones = 0;

        for (int right = 0; right < n; right++) {
            if (s.charAt(right) == '1') {
                ones++;
            }

            // Shrink while we have too many ones or a leading zero.
            while (ones > k || (left < right && s.charAt(left) == '0')) {
                if (s.charAt(left) == '1') {
                    ones--;
                }
                left++;
            }

            if (ones == k) {
                String candidate = s.substring(left, right + 1);

                if (answer.isEmpty()
                        || candidate.length() < answer.length()
                        || (candidate.length() == answer.length()
                                && candidate.compareTo(answer) < 0)) {
                    answer = candidate;
                }
            }
        }

        return answer;
    }
}