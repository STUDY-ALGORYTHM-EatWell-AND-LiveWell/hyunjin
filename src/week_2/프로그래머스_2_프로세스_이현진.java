package week_2;

import java.util.*;

public class 프로그래머스_2_프로세스_이현진 {
    public static void main(String[] args) {
        int[] priorities = {2, 1, 3, 2};
        int location = 2;
        System.out.println(solution(priorities, location));
    }

    public static int solution(int[] priorities, int location) {
        int answer = 1;

        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < priorities.length; i++) {
            queue.offer(new int[]{i, priorities[i]});
        }

        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            boolean hasHigher = queue.stream().anyMatch(p -> p[1] > current[1]);

            if (hasHigher) {
                queue.offer(current);
            } else {
                if (current[0] == location) return answer;
                answer++;
            }
        }
        return -1;
    }

    /*
    public static int solution2(int[] priorities, int location) {
        int answer = 1;

        Queue<int[]> queue = new LinkedList<>();

        for(int i = 0; i<priorities.length; i++){
            queue.offer(new int[] {i, priorities[i]});
        }

        while(!queue.isEmpty()){
            int[] current = queue.poll();

            if(queue.stream().max(Comparator.comparingInt(a -> a[1])).get()[1] > current[1]){
                queue.offer(current);
            } else {
                if (current[0] == location) return answer;
                answer++;
            }
        }
        return -1;
    }
     */
}
