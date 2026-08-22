class Solution {
    public boolean isPalindrome(int x) {
        // Negative numbers and numbers ending with 0 (except 0 itself) are not palindromes.
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int rev = 0;

        // Reverse only the second half.
        while (x > rev) {
            rev = rev * 10 + x % 10;
            x /= 10;
        }

        // Even length: x == rev
        // Odd length: x == rev / 10 (drop middle digit)
        return x == rev || x == rev / 10;
    }
}