package week_5;

import java.util.ArrayList;
import java.util.List;

public class 프로그래머스_1_모의고사_이현진 {
    public static void main(String[] args) {
        int[] nums = {1, 3, 2, 4, 2};
        System.out.println(solution(nums));
    }

    public static List<Integer> solution(int[] answers) {
        int[] a = {1, 2, 3, 4, 5};
        int[] b = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] c = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};

        int[] cnt = new int[3];
        for (int i = 0; i < answers.length; i++) {
            if (a[i % a.length] == answers[i]) cnt[0]++;
            if (b[i % b.length] == answers[i]) cnt[1]++;
            if (c[i % c.length] == answers[i]) cnt[2]++;
        }

        ArrayList<Integer> answer = new ArrayList<>();
        int maxP = Math.max(cnt[0], Math.max(cnt[1], cnt[2]));
        if (cnt[0] == maxP) answer.add(1);
        if (cnt[1] == maxP) answer.add(2);
        if (cnt[2] == maxP) answer.add(3);

        answer.stream().sorted();
        return answer;
    }
}
