package week_2;

import java.util.*;
import java.util.stream.Collectors;

public class 프로그래머스_1_같은숫자는싫어_이현진 {
    public static void main(String[] args) {
        int[] arr = {1,1,3,3,0,1,1};
        solution(arr);
    }

    public static int[] solution(int []arr) {
        int[] answer = new int[arr.length];
        answer[0] = arr[0];

        int idx = 1;
        for(int i = 1; i < arr.length; i++){
            if(arr[i-1] == arr[i]) continue;
            answer[idx] = arr[i];
            idx++;
        }

        answer = Arrays.copyOfRange(answer, 0, idx);
        return answer;
    }

    public Stack<Integer> solution2(int []arr) {

        Stack<Integer> stack = new Stack<>();

        for(int num : arr){
            if(stack.isEmpty() || stack.peek() != num){
                stack.push(num);
            }
        }

        return stack;
    }
}
