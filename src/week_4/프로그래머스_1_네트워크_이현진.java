package week_4;

import java.util.LinkedList;
import java.util.Queue;

public class 프로그래머스_1_네트워크_이현진 {
    public static void main(String[] args) {
        int[][] computers = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        System.out.println(solution(3, computers));
    }

    static boolean [] visited; // 방문여부

    public static int solution(int n, int[][] computers) {
        int answer = 0;
        visited = new boolean[n];

        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                if(computers[i][j] == 1 && !visited[i]){ // 연결되어있고, 방문한 적이 없다면
                    visited[i] = true; // 해당 컴퓨터는 방문함.
                    answer++; // 네트워크 수 증가
                    /*
                    해당 컴퓨터와 관련있는 곳들 탐색
                    연결되어 있는 컴퓨터는 같은 네트워크라고 쳐야 함.
                    */
                    bfs(i, n, computers);
                }
            }
        }
        return answer;
    }

    public static void bfs(int start, int n, int[][]computers){
        Queue<Integer> q = new LinkedList<>();
        q.add(start); // 탐색할 컴퓨터의 번호 넣음

        while(!q.isEmpty()){
            int current = q.poll(); // 탐색할 컴퓨터
            for(int i = 0; i<n; i++){
                if(computers[current][i] == 1 && !visited[i]){
                    visited[i] = true;
                    q.add(i);
                }
            }
        }
    }
}