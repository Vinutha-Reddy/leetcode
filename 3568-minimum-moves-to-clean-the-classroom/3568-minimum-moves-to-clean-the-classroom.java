import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();

        int startRow = -1, startCol = -1;
        int litterCount = 0;
        Map<String, Integer> litterIndex = new HashMap<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = classroom[i].charAt(j);

                if (c == 'S') {
                    startRow = i;
                    startCol = j;
                } else if (c == 'L') {
                    litterIndex.put(i + "," + j, litterCount++);
                }
            }
        }

        if (litterCount == 0) {
            return 0;
        }

        int fullMask = (1 << litterCount) - 1;

        // bestEnergy[row][col][mask] = maximum energy seen for this state
        int[][][] bestEnergy = new int[m][n][1 << litterCount];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(bestEnergy[i][j], -1);
            }
        }

        Queue<int[]> queue = new ArrayDeque<>();

        // state: {row, col, energy, mask, moves}
        queue.offer(new int[]{startRow, startCol, energy, 0, 0});
        bestEnergy[startRow][startCol][0] = energy;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int r = cur[0], c = cur[1], e = cur[2], mask = cur[3], moves = cur[4];

            if (mask == fullMask) {
                return moves;
            }

            for (int dir = 0; dir < 4; dir++) {
                int nr = r + dr[dir];
                int nc = c + dc[dir];

                if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                    continue;
                }

                char cell = classroom[nr].charAt(nc);

                if (cell == 'X') {
                    continue;
                }

                int ne = e - 1;

                if (ne < 0) {
                    continue;
                }

                if (cell == 'R') {
                    ne = energy;
                }

                int nmask = mask;

                if (cell == 'L') {
                    int idx = litterIndex.get(nr + "," + nc);
                    nmask |= (1 << idx);
                }

                if (bestEnergy[nr][nc][nmask] >= ne) {
                    continue;
                }

                bestEnergy[nr][nc][nmask] = ne;
                queue.offer(new int[]{nr, nc, ne, nmask, moves + 1});
            }
        }

        return -1;
    }
}