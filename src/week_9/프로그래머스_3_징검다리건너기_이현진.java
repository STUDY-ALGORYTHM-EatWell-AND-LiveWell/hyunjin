package week_9;

import java.util.Arrays;

public class 프로그래머스_3_징검다리건너기_이현진 {
    public static void main(String[] args) {
        int[] stones = {2, 4, 5, 3, 2, 1, 4, 2, 5, 1};
        System.out.println(solution(stones, 3));
    }

    public static int solution(int[] stones, int k) {
        int left = 1;
        int right = Arrays.stream(stones).max().getAsInt(); // 최대 stone값까지

        int answer = 0;

        while (left <= right) {
            int mid = (left + right) / 2; // 중간 인원

            canCross(stones, k, mid);

            if (canCross(stones, k, mid)) {
                answer = mid;      // mid 명 이상으로 건널 수 있음
                left = mid + 1;
            } else {
                right = mid - 1;   // mid 명 이하로 건널 수 있음
            }

        }
        return answer;
    }

    // mid명이 건널 수 다리를 건널 수 있는가?
    private static boolean canCross(int[] stones, int k, int people) {
        int count = 0;

        for (int stone : stones) {
            if (stone < people) { // 돌의 크기가 사람 크기 보다 작으면
                count++;
                if (count >= k) return false; // 돌의 크가 연속 3번으로 사람 크기 보다 작으면
            } else {
                count = 0;
            }
        }

        return true;
    }
}
