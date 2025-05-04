package week_7;

import java.util.*;

public class 프로그래머스_1_개인정보수집유효시간_이현진 {
    public static void main(String[] args) {
        String today = "2022.11.28";
        String[] terms = {"A 12"};
        String[] privacies = {
                "2021.11.28 A"
        };
        System.out.println(Arrays.toString(solution(today, terms, privacies)));
    }

    public static Map<String, Integer> term = new HashMap<>();
    public static int[] todayN = new int[3];

    public static int[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            todayN[i] = Integer.parseInt(today.split("\\.")[i]);
        }

        for (String i : terms) {
            term.put(i.split(" ")[0], Integer.parseInt(i.split(" ")[1]));
        }

        for (int i = 0; i < privacies.length; i++) {
            String type = privacies[i].split(" ")[1];
            String date = privacies[i].split(" ")[0];
            int y = Integer.parseInt(date.split("\\.")[0]);
            int m = Integer.parseInt(date.split("\\.")[1]);
            int d = Integer.parseInt(date.split("\\.")[2]);

            if (!calculateDate(y, m, d, type)) result.add(i + 1);
        }

        return result.stream().mapToInt(i -> i).toArray();
    }

    private static boolean calculateDate(int y, int m, int d, String type) {
        int plusM = term.get(type);
        m += plusM;

        if (m > 12) {
            y = y + (m - 1) / 12;
            m = (m - 1) % 12 + 1;
        }

        if (todayN[0] < y) return true;
        else if (todayN[0] == y && todayN[1] < m) return true;
        else if (todayN[0] == y && todayN[1] == m && todayN[2] < d) return true;
        return false;
    }
}