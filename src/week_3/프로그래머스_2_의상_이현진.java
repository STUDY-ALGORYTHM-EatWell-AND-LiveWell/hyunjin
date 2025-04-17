package week_3;

import java.util.Arrays;

import static java.util.stream.Collectors.*;

public class 프로그래머스_2_의상_이현진 {
    public static void main(String[] args) {
        String[][] clothes = {{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}};
        System.out.println(solution(clothes));
    }

    public static int solution(String[][] clothes) {
        return Arrays.stream(clothes)
                .collect(groupingBy(p -> p[1], mapping(p -> p[0], counting())))
                .values()
                .stream()
                .map(count -> count+1)
                .reduce(1L, (a, b) -> a * b).intValue() -1;
    }
}
