package week_7;

import java.util.*;

public class 프로그래머스_3_합승택시요금_이현진 {
    public static void main(String[] args) {
        int n = 6;
        int s = 4;
        int a = 6;
        int b = 2;
        int[][] fares = {
                {4, 1, 10},
                {3, 5, 24},
                {5, 6, 2},
                {3, 1, 41},
                {5, 1, 24},
                {4, 6, 50},
                {2, 4, 66},
                {2, 3, 22},
                {1, 6, 25}
        };

        System.out.println(solution(n, s, a, b, fares));
    }

    static ArrayList<ArrayList<int[]>> graph = new ArrayList<>();

    // 합승요금 보다 개별 요금이 싸면 합승 안해도 됨.
    public static int solution(int n, int s, int a, int b, int[][] fares) {
        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }

        // 노드 연결
        for (int[] fare : fares) {
            graph.get(fare[0]).add(new int[]{fare[1], fare[2]});
            graph.get(fare[1]).add(new int[]{fare[0], fare[2]});
        }

        int[] distFromS = dijkstra(n, s);
        int[] distFromA = dijkstra(n, a);
        int[] distFromB = dijkstra(n, b);

        int answer = Integer.MAX_VALUE;

        for (int i = 1; i <= n; i++) {
            if (distFromS[i] == Integer.MAX_VALUE ||
                    distFromA[i] == Integer.MAX_VALUE ||
                    distFromB[i] == Integer.MAX_VALUE) continue;

            int cost = distFromS[i] + distFromA[i] + distFromB[i];
            answer = Math.min(answer, cost);
        }

        return answer;
    }

    // 노드의 수, 시작 지점
    private static int[] dijkstra(int n, int start) {
        int[] dist = new int[n + 1]; // 도착지점, 비용
        Arrays.fill(dist, Integer.MAX_VALUE); // 최대값으로 설정
        dist[start] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        pq.offer(new int[]{start, 0});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            if (dist[current[0]] < current[1]) continue;

            for (int[] next : graph.get(current[0])) {
                if (dist[next[0]] > dist[current[0]] + next[1]) {
                    dist[next[0]] = dist[current[0]] + next[1];
                    pq.offer(new int[]{next[0], dist[next[0]]});
                }
            }
        }

        return dist;
    }
}