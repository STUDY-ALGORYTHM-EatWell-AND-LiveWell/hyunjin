package week_11;

import java.util.Arrays;

public class 프로그래머스_숫자게임_이현진 {
    public int solution(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);
        int aIdx = 0;
        int bIdx = 0;
        int cnt = 0;

        while (aIdx < A.length && bIdx < B.length) {
            if (B[bIdx] > A[aIdx]) {
                cnt++;
                aIdx++;
            }
            bIdx++;
        }
        return cnt;
    }
}
