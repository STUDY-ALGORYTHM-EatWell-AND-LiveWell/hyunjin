package week_4;

import java.util.LinkedList;
import java.util.Queue;

public class 프로그래머스_2_게임맵최단거리_이현진 {
    public static void main(String[] args) {
        int[][] maps = {{1, 0, 1, 1, 1}, {1, 0, 1, 0, 1}, {1, 0, 1, 1, 1}, {1, 1, 1, 0, 1}, {0, 0, 0, 0, 1}};
        System.out.println(solution(maps));
    }

    // 상, 하, 좌, 우
    private static int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private static boolean[][] visited;

    public static int solution(int[][] maps) {
        int row = maps.length, col = maps[0].length;
        visited = new boolean[row][col];

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0, 1});
        visited[0][0] = true;

        while (!q.isEmpty()) {
            int[] current = q.poll(); // 현재 노드의 r, c, d 값
            int r = current[0], c = current[1], distance = current[2];

            // 현재 노드가 도착 지점이면 거리 반환
            if (r == row - 1 && c == col - 1) return distance;

            for (int[] i : DIRS) {
                int nr = r + i[0], nc = c + i[1];
                // 범위 안에 있다면, 그리고 벽이 아니라면, 방문한 적도 없다면
                if (inside(nr, nc, row, col) && maps[nr][nc] == 1 && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    q.add(new int[]{nr, nc, distance + 1});
                }
            }
        }
        return -1;
    }

    private static boolean inside(int r, int c, int row, int col) {
        return (r >= 0 && r < row && c >= 0 && c < col);
    }

}