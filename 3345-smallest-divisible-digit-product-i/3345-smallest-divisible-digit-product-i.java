class Solution {
    public int smallestNumber(int n, int t) {
        while (true) {
            int product = digitProduct(n);

            if (product % t == 0) {
                return n;
            }

            n++;
        }
    }

    private int digitProduct(int num) {
        int product = 1;

        while (num > 0) {
            int digit = num % 10;
            product *= digit;

            // Once the product is zero, it is divisible by every t.
            if (product == 0) {
                return 0;
            }

            num /= 10;
        }

        return product;
    }
}