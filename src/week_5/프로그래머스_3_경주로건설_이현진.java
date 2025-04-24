package week_5;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class 프로그래머스_3_경주로건설_이현진 {
    public static void main(String[] args) {
        int[][] board = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        System.out.println(solution(board));
    }

    private static int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static int solution(int[][] board) {
        int size = board.length;
        int[][][] cost = new int[size][size][4]; // [row][col][direction] 별 최소 비용 저장

        for (int[][] row : cost)
            for (int[] col : row)
                Arrays.fill(col, Integer.MAX_VALUE);

        Queue<int[]> q = new LinkedList<>(); // row, col, 누적비용, direction
        // 출발점에서 4방향 모두 시도
        for (int i = 0; i < 4; i++) {
            cost[0][0][i] = 0;
            q.offer(new int[]{0, 0, 0, i});
        }

        while (!q.isEmpty()) {
            int[] current = q.poll();
            int r = current[0], c = current[1], curCost = current[2], dir = current[3];

            for (int i = 0; i < 4; i++) {
                int nr = r + DIRS[i][0];
                int nc = c + DIRS[i][1];

                if (!inside(nr, nc, size) || board[nr][nc] == 1) continue;

                int newCost = curCost + (dir == i ? 100 : 600); // 직진 vs 코너

                if (cost[nr][nc][i] > newCost) {
                    cost[nr][nc][i] = newCost;
                    q.offer(new int[]{nr, nc, newCost, i});
                }
            }
        }
        return Arrays.stream(cost[size - 1][size - 1]).min().getAsInt();
    }

    private static boolean inside(int nr, int nc, int size) {
        return (nr >= 0 && nr < size && nc >= 0 && nc < size);
    }
}
