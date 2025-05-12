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

    public static int[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> result = new ArrayList<>();
        for (String i : terms) {
            term.put(i.split(" ")[0], Integer.parseInt(i.split(" ")[1]));
        }

        int day = Integer.parseInt(today.split("\\.")[0]) * 12 * 28
                + Integer.parseInt(today.split("\\.")[1]) * 28
                + Integer.parseInt(today.split("\\.")[2]);

        for (int i = 0; i < privacies.length; i++) {
            if (calDate(privacies[i]) <= day) {
                result.add(i + 1);
            }
        }
        return result.stream().mapToInt(i -> i).toArray();
    }

    private static int calDate(String privacy) {
        String type = privacy.split(" ")[1];
        String y = privacy.split(" ")[0].split("\\.")[0];
        String m = privacy.split(" ")[0].split("\\.")[1];
        String d = privacy.split(" ")[0].split("\\.")[2];

        int day = Integer.parseInt(y) * 12 * 28 + Integer.parseInt(m) * 28 + Integer.parseInt(d);
        return (day + term.get(type) * 28);
    }
}