package week_5;

import java.util.HashSet;

public class 프로그래머스_2_소수찾기_이현진 {
    public static void main(String[] args) {
        System.out.println(solution("143"));
    }

    private static HashSet<Integer> primeN = new HashSet<>();
    private static boolean[] visited;

    public static int solution(String numbers) {
        String[] number = numbers.split("");
        visited = new boolean[number.length];
        dfs("", number);
        return primeN.size();
    }

    private static void dfs(String current, String[] number) {
        if (!current.equals("")) {
            if (isPrime(current)) primeN.add(Integer.parseInt(current));
        }

        for (int i = 0; i < number.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(current + number[i], number);
                visited[i] = false;
            }
        }
    }

    private static boolean isPrime(String num) {
        int n = Integer.parseInt(num);

        if (n <= 1) return false;
        if (n == 2) return true;

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}