package week_11;

import java.util.*;

public class 백준_2293_동전1_이현진 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n+1];
        for (int i = 1; i <= n; i++) {
            arr[i] = sc.nextInt();
        }
        int[] dp = new int[k+1];
        dp[0] = 1;

        for(int i = 1; i <= n; i++) {
            for(int j = arr[i]; j <= k; j++) {
                dp[j] += dp[j - arr[i]];
                System.out.println("j = " + j + " dp = " + dp[j]);
            }
        }

        System.out.println(dp[k]);
    }
}
