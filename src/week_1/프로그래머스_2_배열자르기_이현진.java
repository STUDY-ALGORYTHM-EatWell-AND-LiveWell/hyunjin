package week_1;

import java.util.Arrays;

public class 프로그래머스_2_배열자르기_이현진 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(4, 7, 14)));
    }

    // row = i/n + 1
    // col = i%n + 1
    // arr[row][col] = row + (col-row) if(col>row) 형태임

    public static int[] solution(int n, long left, long right) {
        int len = (int) (right - left + 1);
        int[] answer = new int[len];
        int idx = 0;
        for (long i = left; i <= right; i++) {
            long row = i / n + 1;
            long col = i % n + 1;
            answer[idx] = (int) row;
            if (col > row) answer[idx] += (int) (col - row);
            idx++;
        }
        return answer;
    }
}
