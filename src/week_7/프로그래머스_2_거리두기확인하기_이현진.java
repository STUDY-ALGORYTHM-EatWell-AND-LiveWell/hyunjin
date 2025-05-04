package week_7;

import java.util.Arrays;

public class 프로그래머스_2_거리두기확인하기_이현진 {
    public static void main(String[] args) {
        String[][] places = {
                {"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
                {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},
                {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"},
                {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},
                {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}
        };
        System.out.println(Arrays.toString(solution(places)));
    }

    public static int[] solution(String[][] places) {
        int[] result = new int[5];

        for (int i = 0; i < 5; i++) {
            String[] place = places[i];
            char[][] map = new char[5][5];

            // 문자열 배열을 문자 배열로 변환
            for (int r = 0; r < 5; r++) {
                map[r] = place[r].toCharArray();
            }

            // 각 좌표 탐색
            if (check(map)) result[i] = 1;
            else result[i] = 0;
        }

        return result;
    }

    public static boolean check(char[][] map) {
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                if (map[r][c] != 'P') continue;

                int[] dr = {-1, 1, 0, 0};
                int[] dc = {0, 0, -1, 1};

                int[] dr2 = {-2, 2, 0, 0};
                int[] dc2 = {0, 0, -2, 2};

                // 상하좌우 거리
                for (int i = 0; i < 4; i++) { // 나를 기준으로 네 방향 탐색
                    int nr = r + dr[i];
                    int nc = c + dc[i];
                    if (inRange(nr, nc) && map[nr][nc] == 'P') { // 사람이 있으면 탈락
                        return false;
                    }

                    int nr2 = r + dr2[i];
                    int nc2 = c + dc2[i];
                    if(inRange(nr2, nc2) && map[nr2][nc2] == 'P' && map[nr][nc] != 'X'){ // 사람이 있으면 탈락
                        return false;
                    }
                }

                // 대각선
                int[][] drc = {{-1,-1}, {-1,1}, {1,-1}, {1,1}};
                for (int[] d : drc) {
                    int nr = r + d[0];
                    int nc = c + d[1];
                    if(!inRange(nr, nc) || map[nr][nc] != 'P') continue;
                    if(map[r][nc] != 'X' || map[nr][c] != 'X'){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private static boolean inRange(int r, int c) {
        return r >= 0 && r < 5 && c >= 0 && c < 5;
    }
}