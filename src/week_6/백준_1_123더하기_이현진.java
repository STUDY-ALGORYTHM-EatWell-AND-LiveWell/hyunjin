package week_6;

import java.util.*;

public class 백준_1_123더하기_이현진 {
    public static void main(String[] args) {
        int[] sum = new int[12];
        sum[0] = 0;
        sum[1] = 1;
        sum[2] = 2;
        sum[3] = 4;

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for(int i = 0; i < n; i++){
            int test = sc.nextInt();
            for(int j = 4; j <= test; j++){
                sum[j] = sum[j-1] + sum[j-2] + sum[j-3];
            }
            System.out.println(sum[test]);
        }
    }
}
