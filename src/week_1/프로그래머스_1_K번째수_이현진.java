package week_1;

import java.util.Arrays;

import static java.util.Arrays.sort;

/*
입출력 예시
1. array
[1, 5, 2, 6, 3, 7, 4]

2. commands
[[2, 5, 3], [4, 4, 1], [1, 7, 3]]

-> return
[5, 6, 3]
 */

public class 프로그래머스_1_K번째수_이현진 {
    public static void main(String[] args) {
        int[] array = {1, 5, 2, 6, 3, 7, 4};
        int[][] commands = { {2, 5, 3}, {4, 4, 1}, {1, 7, 3} };
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.solution(array, commands)));
    }
}

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];

        for(int i = 0; i<commands.length; i++){
            // Arrays.copyOfRange(arr, 시작인덱스, 종료인덱스 + 1)
            int[] list = Arrays.copyOfRange(array, commands[i][0]-1, commands[i][1]);
            sort(list);
            answer[i] = list[commands[i][2] - 1];

        }
        return answer;
    }
}