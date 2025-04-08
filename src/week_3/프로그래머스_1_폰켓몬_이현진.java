package week_3;

import java.util.*;

public class 프로그래머스_1_폰켓몬_이현진 {
    public static void main(String[] args) {
        int[] nums = {3,1,2,3};
        System.out.println(solution(nums));
    }

     /*
    N마리 폰켓몬의 종류 번호가 담긴 배열 nums가 매개변수로 주어질 때,
    N/2마리의 폰켓몬을 선택하는 방법 중,
    가장 많은 종류의 폰켓몬을 선택하는 방법
     */
    public static int solution(int[] nums) {
        HashSet<Integer> list = new HashSet<>();
        for (int i : nums) {
            list.add(i);
        }

        return Math.min(list.size(), nums.length / 2);
    }
}
