package week_6;

public class 프로그래머스_2_등굣길 {
    public int solution(int m, int n, int[][] puddles) {
        int[][] dp = new int[m][n];

        // 물 웅덩이 먼저 표시
        for (int[] puddle : puddles) {
            int mm = puddle[0], nn = puddle[1];
            dp[mm - 1][nn - 1] = -1;
        }

        // 시작점
        dp[0][0] = 1;

        // 경로 저장
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 물 웅덩이면 0으로 처리하고 건너뛰기
                if (dp[i][j] == -1) {
                    dp[i][j] = 0;
                    continue;
                }

                if (i > 0) dp[i][j] += dp[i - 1][j];
                if (j > 0) dp[i][j] += dp[i][j - 1];
                dp[i][j] %= 1000000007;
            }
        }

        return dp[m - 1][n - 1];
    }
}
