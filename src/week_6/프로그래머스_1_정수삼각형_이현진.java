package week_6;

public class 프로그래머스_1_정수삼각형_이현진 {
    public static void main(String[] args) {
        int[][] nums = {{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}};
        System.out.println(solution(nums));
    }

    public static int solution(int[][] triangle) {
        int n = triangle.length;
        int[][] dp = new int[n][n];

        // 가장 마지막 행을 복사
        for (int i = 0; i < triangle[n - 1].length; i++) {
            dp[n - 1][i] = triangle[n - 1][i];
        }

        // 마지막부터 더해서 위로 올라오기
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j < triangle[i].length; j++) {
                dp[i][j] = triangle[i][j] + Math.max(dp[i + 1][j], dp[i + 1][j + 1]);
            }
        }

        return dp[0][0];
    }
}
