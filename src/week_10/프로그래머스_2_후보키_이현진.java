package week_10;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class 프로그래머스_2_후보키_이현진 {
    public static void main(String[] args) {
        String[][] relation = {
                {"100", "ryan", "music", "2"},
                {"200", "apeach", "math", "2"},
                {"300", "tube", "computer", "3"},
                {"400", "con", "computer", "4"},
                {"500", "muzi", "music", "3"},
                {"600", "apeach", "music", "2"}
        };

        System.out.println(solution(relation));
    }

    static List<Integer> candidateKeys = new ArrayList<>();
    static int r, c;

    public static int solution(String[][] relation) {
        int answer = 0;
        r = relation.length; // 가로
        c = relation[0].length; // 세로

        // 모든 컬럼 조합에 대해 확인 (1부터 2^c - 1까지)
        for (int bitmask = 1; bitmask < (1 << c); bitmask++) {
            if (!isUnique(relation, bitmask)) continue;
            if (!isMinimal(bitmask)) continue;

            candidateKeys.add(bitmask);
            answer++;
        }

        return answer;
    }

    // 유일성 검사
    private static boolean isUnique(String[][] relation, int bitmask) {
        Set<String> hash = new HashSet<>();

        for (String[] row : relation) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < c; i++) {
                if ((bitmask & (1 << i)) != 0) {
                    sb.append(row[i]).append(",");
                }
            }
            hash.add(sb.toString());
        }

        return hash.size() == relation.length;
    }

    // 최소성 검사
    private static boolean isMinimal(int bitmask) {
        for (int key : candidateKeys) {
            if ((bitmask & key) == key) return false; // 기존 키를 포함하면 최소성 위배
        }
        return true;
    }
}