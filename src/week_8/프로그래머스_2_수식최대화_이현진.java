package week_8;

import java.util.LinkedList;
import java.util.List;

public class 프로그래머스_2_수식최대화_이현진 {

    static char[] oper = {'+', '-', '*'};
    static char[] output = new char[3];
    static boolean[] check = new boolean[3];

    static List<Long> numList = new LinkedList<>();
    static List<Character> opList = new LinkedList<>();

    static long answer = 0;

    public long solution(String expression) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            if (Character.isDigit(ch)) {
                sb.append(ch);
            } else {
                numList.add(Long.parseLong(sb.toString()));
                sb.setLength(0); // 초기화
                opList.add(ch);
            }
        }
        numList.add(Long.parseLong(sb.toString())); // 마지막 숫자 처리

        dfs(0);

        return answer;
    }

    // 다양한 연산자 조합
    private static void dfs(int depth) {
        if (depth == 3) {
            answer = solve();
            return;
        }

        for (int i = 0; i < 3; i++) {
            if (!check[i]) {
                check[i] = true;
                output[depth] = oper[i];
                dfs(depth + 1);
                check[i] = false;
            }
        }
    }

    private static long solve() {
        List<Long> copyNum = new LinkedList<>();
        copyNum.addAll(numList);
        List<Character> copyOp = new LinkedList<>();
        copyOp.addAll(opList);

        for (int i = 0; i < 3; i++) {
            char nowOp = output[i];

            for (int j = 0; j < copyOp.size(); j++) {
                if (copyOp.get(j) == nowOp) {
                    long result = cal(copyNum.get(j), copyNum.get(j + 1), nowOp);
                    copyNum.remove(j + 1);
                    copyNum.remove(j);
                    copyOp.remove(j);

                    copyNum.add(j, result);
                    j--;
                }
            }
        }
        return Math.max(answer, Math.abs(copyNum.get(0)));
    }

    private static long cal(long a, long b, char c) {
        return switch (c) {
            case '+' -> a + b;
            case '-' -> a - b;
            case '*' -> a * b;
            default -> 0;
        };
    }
}