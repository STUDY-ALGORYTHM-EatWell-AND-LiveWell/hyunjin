package week_7;

import java.util.*;

public class 백준_1753_최단경로_이현진 {
    private static ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
    private static int V, E, K;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        V = sc.nextInt();
        E = sc.nextInt();
        K = sc.nextInt();

        for (int i = 0; i < V + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            int u, v, w;
            u = sc.nextInt();
            v = sc.nextInt();
            w = sc.nextInt();
            graph.get(u).add(new int[]{v, w});
        }

        int[] shortPath = dijkstra();
        for (int i = 1; i <= V; i++) {
            if (shortPath[i] == Integer.MAX_VALUE) System.out.println("INF");
            else System.out.println(shortPath[i]);
        }
    }

    public static int[] dijkstra() {
        int[] dist = new int[V + 1]; // 도착지점, 가중치
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[K] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1])); // 가중치 기준 정렬
        pq.offer(new int[]{K, 0});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            if (dist[current[0]] < current[1]) continue;

            for (int[] next : graph.get(current[0])) {
                // 저장된 다음 정점까지의 가중치 값 > 현재까지의 가중치 + 다음 정점까지의 가중치
                if (dist[next[0]] > dist[current[0]] + next[1]) {
                    dist[next[0]] = dist[current[0]] + next[1];
                    pq.offer(new int[]{next[0], dist[next[0]]}); // 다음 정점으로 연결
                }
            }
        }

        return dist;
    }
}
