package week_9;

import java.util.*;

public class 프로그래머스_1_가장많이받은선물_이현진 {
    public static void main(String[] args) {
        String[] friends = {"a", "b", "c"};
        String[] gifts = {
                "a b", "a c", "b a", "b c"
        };
        System.out.println(solution(friends, gifts));
    }

    public static int solution(String[] friends, String[] gifts) {
        int size = friends.length;
        List<String> friend = new ArrayList<>(Arrays.asList(friends));

        // 선물 지수 표
        int[][] giftHistory = new int[size][size];

        // 자기 자신은 0으로 초기화
        for (int i = 0; i < size; i++) {
            giftHistory[i][i] = 0;
        }

        for (String gift : gifts) {
            String[] name = gift.split(" ");
            // 준 사람 찾기
            int a = friend.indexOf(name[0]);

            // 받은 사람 찾기
            int b = friend.indexOf(name[1]);

            // 선물 줬다고 표시
            giftHistory[a][b]++;
        }

        int[] giftCnt = new int[size];
        for (int i = 0; i < size; i++) {
            int sum = 0;
            // 준 선물
            for (int j = 0; j < size; j++) {
                sum += giftHistory[i][j];
            }

            // 받은 선물
            for (int j = 0; j < size; j++) {
                sum -= giftHistory[j][i];
            }

            giftCnt[i] = sum;
        }

        int[] cnt = new int[size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == j) continue;

                // 서로 주고 받은 선물지수 (선물의 수 - 받은 선물의 수)
                if (giftHistory[i][j] == giftHistory[j][i]) { // 둘이 똑같이 주고 받았다면
                    if (giftCnt[i] > giftCnt[j]) cnt[i]++;
                } else if (giftHistory[i][j] > giftHistory[j][i]) { // a가 더 많이 줬다면
                    cnt[i]++;
                }
            }
        }
        return Arrays.stream(cnt).max().getAsInt();
    }
}
