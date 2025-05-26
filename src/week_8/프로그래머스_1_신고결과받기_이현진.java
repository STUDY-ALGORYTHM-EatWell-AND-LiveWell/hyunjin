package week_8;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class 프로그래머스_1_신고결과받기_이현진 {
    public static void main(String[] args) {
        String[] id_list = {"muzi", "frodo", "apeach", "neo"};
        String[] report = {"muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"};
        int k = 2;
        System.out.println(Arrays.toString(solution(id_list, report, k)));
    }

    public static int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];

        // 유저, 받을 메일 개수
        Map<String, Integer> mailCount = new HashMap<>();

        // 신고당한 사람, 신고한 사람 해시
        Map<String, HashSet<String>> reportMap = new HashMap<>();

        // 초기화
        for (String id : id_list) {
            mailCount.put(id, 0);
            reportMap.put(id, new HashSet<>());
        }

        // 신고당한 개수 세기
        for (String re : report) {
            String apply = re.split(" ")[0];
            String receive = re.split(" ")[1];
            reportMap.get(receive).add(apply); // 신고한 사람 해시에 신고 유저 추가
        }

        // 메일 개수 세기
        for (String user : reportMap.keySet()) {
            HashSet<String> reporters = reportMap.get(user);
            if (reporters.size() >= k) {
                for (String reporter : reporters) {
                    mailCount.put(reporter, mailCount.get(reporter) + 1);
                }
            }
        }

        for (int i = 0; i < id_list.length; i++) {
            answer[i] = mailCount.get(id_list[i]);
        }
        return answer;
    }
}