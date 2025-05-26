package week_9;

import java.util.*;

public class 프로그래머스_2_순위검색_이현진 {
    public static void main(String[] args) {
        String[] friends = {"java backend junior pizza 150", "python frontend senior chicken 210", "python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80", "python backend senior chicken 50"};
        String[] gifts = {
                "java and backend and junior and pizza 100", "python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250", "- and backend and senior and - 150", "- and - and - and chicken 100", "- and - and - and - 150"
        };
        System.out.println(Arrays.toString(solution(friends, gifts)));
    }

    static Map<String, List<Integer>> infoMap = new HashMap<>();

    public static int[] solution(String[] info, String[] query) {
        for (String i : info) {
            String[] parts = i.split(" ");
            String[] keys = makeKeys(parts, 0, "");
            int score = Integer.parseInt(parts[4]);

            for (String key : keys) {
                infoMap.computeIfAbsent(key, k -> new ArrayList<>()).add(score);
            }
        }

        // 각 key에 대한 점수 리스트 정렬
        for (List<Integer> scores : infoMap.values()) {
            Collections.sort(scores);
        }

        int[] answer = new int[query.length];

        int idx = 0;
        for (String q : query) {
            String parsed = q.replaceAll(" and ", " ");
            String[] parts = parsed.split(" ");
            String key = parts[0] + parts[1] + parts[2] + parts[3];
            int score = Integer.parseInt(parts[4]);

            if (!infoMap.containsKey(key)) {
                answer[idx++] = 0;
                continue;
            }

            List<Integer> scores = infoMap.get(key);
            int count = lowerBound(scores, score);
            answer[idx++] = scores.size() - count;
        }

        return answer;
    }

    // 가능한 모든 조합을 만들어주는 함수 (총 16개)
    private static String[] makeKeys(String[] parts, int depth, String current) {
        if (depth == 4) {
            return new String[]{current};
        }

        String[] fromDash = makeKeys(parts, depth + 1, current + "-");
        String[] fromValue = makeKeys(parts, depth + 1, current + parts[depth]);

        String[] result = new String[fromDash.length + fromValue.length];
        System.arraycopy(fromDash, 0, result, 0, fromDash.length);
        System.arraycopy(fromValue, 0, result, fromDash.length, fromValue.length);

        return result;
    }

    // 이진 탐색으로 기준 점수 이상인 첫 번째 인덱스를 찾음
    private static int lowerBound(List<Integer> scores, int target) {
        int left = 0, right = scores.size();

        while (left < right) {
            int mid = (left + right) / 2;
            if (scores.get(mid) < target) left = mid + 1;
            else right = mid;
        }

        return left;
    }
}
