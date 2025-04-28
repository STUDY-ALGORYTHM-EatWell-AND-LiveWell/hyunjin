package week_6;

public class 프로그래머스_3_파괴되지않은건물 {
    public static void main(String[] args) {
        int[][] board = {{5,5,5,5,5}, {5,5,5,5,5}, {5,5,5,5,5}, {5,5,5,5,5}};
        int[][] skill = {{1,0,0,3,4,4},{1,2,0,2,3,2},{2,1,0,3,1,2},{1,0,1,3,3,1}};
        System.out.println(solution(board, skill));
    }

    private static int[][] sum;

    public static int solution(int[][] board, int[][] skill) {
        // skill의 각 행은 [type, r1, c1, r2, c2, degree]
        // type이 1일 경우는 적의 공격
        // type이 2일 경우는 아군의 회복 스킬
        int n = board.length, m = board[0].length;
        sum = new int[n+1][m+1];

        for (int[] s : skill) {
            int y1 = s[1], x1 = s[2];
            int y2 = s[3], x2 = s[4];
            int degree = s[5] * (s[0] == 1 ? -1 : 1);

            sum[y1][x1] += degree;
            sum[y1][x2 + 1] += (degree * -1);
            sum[y2 + 1][x1] += (degree * -1);
            sum[y2 + 1][x2 + 1] += degree;
        }

        operate(n, m);

        // 살아남은 건물 확인
        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] + sum[i][j] > 0) answer++;
            }
        }
        return answer;
    }

    // 누적합 계산
    private static void operate(int n, int m) {
        // 상하
        for (int y = 1; y < n; y++) {
            for (int x = 0; x < m; x++) {
                sum[y][x] += sum[y - 1][x];
            }
        }
        // 좌우
        for (int x = 1; x < m; x++) {
            for (int y = 0; y < n; y++) {
                sum[y][x] += sum[y][x - 1];
            }
        }
    }
}
