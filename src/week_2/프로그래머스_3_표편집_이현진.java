package week_2;

import java.util.Arrays;
import java.util.Stack;

public class 프로그래머스_3_표편집_이현진 {
    public static void main(String[] args) {
        String[] cmd = {"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z", "U 1", "C"};
        System.out.println(solution(8, 2, cmd));
    }

    public static String solution(int n, int k, String[] cmd) {
        int[] prev = new int[n];
        int[] next = new int[n];
        boolean[] deleted = new boolean[n];
        Stack<Integer> deleteStack = new Stack<>();

        for (int i = 0; i < n; i++) {
            prev[i] = i - 1;
            next[i] = i + 1;
        }
        next[n - 1] = -1; // 마지막 행은 다음 없음

        // (행, 비고) 스택 생성
        int[] arr = new int[n];
        Arrays.fill(arr, 1);

        for (String c : cmd) {
            String[] parts = c.split(" ");
            String op = parts[0];

            if (op.equals("U")) {
                int x = Integer.parseInt(parts[1]);
                for (int i = 0; i < x; i++) {
                    k = prev[k];
                }
            } else if (op.equals("D")) {
                int x = Integer.parseInt(parts[1]);
                for (int i = 0; i < x; i++) {
                    k = next[k];
                }
            } else if (op.equals("C")) {
                deleted[k] = true;
                deleteStack.push(k);

                // 연결 끊기
                if (prev[k] != -1) next[prev[k]] = next[k];
                if (next[k] != -1) prev[next[k]] = prev[k];

                // 커서 이동
                if (next[k] != -1) {
                    k = next[k];
                } else {
                    k = prev[k];
                }
            } else if (op.equals("Z")) {
                int restore = deleteStack.pop();
                deleted[restore] = false;

                // 연결 복원
                if (prev[restore] != -1) next[prev[restore]] = restore;
                if (next[restore] != -1) prev[next[restore]] = restore;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(deleted[i] ? 'X' : 'O');
        }
        return sb.toString();
    }
}
