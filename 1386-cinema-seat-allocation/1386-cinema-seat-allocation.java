import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> rowMask = new HashMap<>();

        // Build bitmask for each row with reservations.
        // Seat j corresponds to bit (10 - j).
        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];

            rowMask.put(
                    row,
                    rowMask.getOrDefault(row, 0) | (1 << (10 - col))
            );
        }

        // Rows without any reservation can always fit 2 families.
        int answer = (n - rowMask.size()) * 2;

        // Masks for the three possible 4-seat blocks.
        int leftMask   = 0b0111100000; // seats 2,3,4,5
        int rightMask  = 0b0000011110; // seats 6,7,8,9
        int middleMask = 0b0001111000; // seats 4,5,6,7

        for (int mask : rowMask.values()) {
            int placed = 0;

            // Try left block.
            if ((mask & leftMask) == 0) {
                placed++;
                mask |= leftMask;
            }

            // Try right block.
            if ((mask & rightMask) == 0) {
                placed++;
                mask |= rightMask;
            }

            // If we couldn't place two families, try middle as fallback.
            if (placed == 0 || (placed == 1 && (mask & middleMask) == 0)) {
                if (placed == 1 && (mask & middleMask) == 0) {
                    placed++;
                } else if (placed == 0 && (mask & middleMask) == 0) {
                    placed = 1;
                }
            }

            answer += placed;
        }

        return answer;
    }
}