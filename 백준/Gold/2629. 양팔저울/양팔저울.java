import java.util.*;
import java.io.*;

public class Main {
    static void solution(int[] iron, int[] ball) {
        int n = iron.length;

        int sum = 0;
        for (int x : iron) sum += x;

        boolean[][] dp = new boolean[n + 1][sum + 1];
        dp[0][0] = true;

        for (int i = 0; i < n; i++) {
            for (int w = 0; w <= sum; w++) {
                if (!dp[i][w]) continue;

                // 사용 안함
                dp[i + 1][w] = true;

                // 더함
                dp[i + 1][w + iron[i]] = true;

                // 뺌
                dp[i + 1][Math.abs(w - iron[i])] = true;
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int b : ball) {
            if (b > sum || !dp[n][b]) sb.append("N ");
            else sb.append("Y ");
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int count1 = Integer.parseInt(br.readLine());
        int[] arr1 = new int[count1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < count1; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }

        int count2 = Integer.parseInt(br.readLine());
        int[] arr2 = new int[count2];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < count2; i++) {
            arr2[i] = Integer.parseInt(st.nextToken());
        }

        solution(arr1, arr2);
    }
}
