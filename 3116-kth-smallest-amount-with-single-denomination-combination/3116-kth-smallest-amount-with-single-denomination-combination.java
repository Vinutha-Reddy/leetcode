class Solution {
    public long findKthSmallest(int[] coins, int k) {
        int minCoin = coins[0];

        for (int coin : coins) {
            minCoin = Math.min(minCoin, coin);
        }

        long left = 1;
        long right = (long) minCoin * k;
        long answer = right;

        while (left <= right) {
            long mid = left + (right - left) / 2;

            if (countAtMost(mid, coins) >= k) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return answer;
    }

    private long countAtMost(long x, int[] coins) {
        int n = coins.length;
        long count = 0;

        // Enumerate all non-empty subsets using bitmask.
        for (int mask = 1; mask < (1 << n); mask++) {
            long lcm = 1;
            int bits = 0;

            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    bits++;
                    lcm = lcm(lcm, coins[i]);

                    if (lcm > x) {
                        break;
                    }
                }
            }

            if (lcm > x) {
                continue;
            }

            if ((bits & 1) == 1) {
                count += x / lcm;
            } else {
                count -= x / lcm;
            }
        }

        return count;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }
}