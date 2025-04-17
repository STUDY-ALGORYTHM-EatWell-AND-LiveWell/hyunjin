package week_4;

import java.util.*;

public class 프로그래머스_3_불량사용자_이현진 {
    public static void main(String[] args) {
        String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
        String[] banned_id = {"*rodo", "*rodo", "******"};
        System.out.println(solution(user_id, banned_id));
    }

    static int U, B;
    static boolean[][] match; // match[b][u] : banned b와 user u가 매칭되는가
    static boolean[] visited;
    static Set<String> combos = new HashSet<>();

    public static int solution(String[] user_id, String[] banned_id) {
        U = user_id.length;
        B = banned_id.length;
        match = new boolean[B][U];
        visited = new boolean[U];

        for (int b = 0; b < B; b++) {
            for (int u = 0; u < U; u++) {
                match[b][u] = isMatch(user_id[u], banned_id[b]);
            }
        }

        dfs(0, new ArrayList<>(), user_id);
        return combos.size();
    }

    static void dfs(int depth, List<String> picked, String[] user_id) {
        // 모든 banned 를 찾았다면
        if (depth == B) {
            List<String> tmp = new ArrayList<>(picked);
            Collections.sort(tmp);
            combos.add(String.join(",", tmp)); // 해시이기 때문에 겹치는 조합이라면 add 안됨
            return;
        }

        for (int u = 0; u < U; u++) {
            if (visited[u] || !match[depth][u]) continue;
            visited[u] = true;
            picked.add(user_id[u]);

            dfs(depth + 1, picked, user_id);

            picked.remove(picked.size() - 1);
            visited[u] = false;
        }
    }

    private static boolean isMatch(String userId, String bannedId) {
        if (userId.length() != bannedId.length()) return false;
        for (int i = 0; i < userId.length(); i++) {
            char c = bannedId.charAt(i);
            if (c == '*') continue;
            if (c != userId.charAt(i)) return false;
        }
        return true;
    }
}
