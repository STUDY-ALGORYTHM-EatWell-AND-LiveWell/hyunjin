package week_7;

import java.util.Collections;
import java.util.LinkedList;

public class 프로그래머스_힙_이중우선순위큐 {
    public static void main(String[] args) {
        String[] operations = {
                "I -45",
                "I 653",
                "D 1",
                "I -642",
                "I 45",
                "I 97",
                "D 1",
                "D -1",
                "I 333"
        };
        System.out.println(solution(operations));
    }

    public static int[] solution(String[] operations) {
        LinkedList<Integer> list = new LinkedList<>();

        for (String operation : operations) {
            String o = operation.split(" ")[0];
            int v = Integer.parseInt(operation.split(" ")[1]);

            if (o.equals("I")) {
                list.add(v);
                Collections.sort(list);
            } else if (o.equals("D") && v == -1 && !list.isEmpty()) {
                list.removeFirst();
            } else if (o.equals("D") && v == 1 && !list.isEmpty()) {
                list.removeLast();
            }
        }

        if (list.isEmpty()) return new int[]{0, 0};
        return new int[]{list.getLast(), list.getFirst()};
    }
}

