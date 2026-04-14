import java.util.*;
import java.io.*;

public class Main {
    static void solution(int n, int[][] size) {
        int[][] dp = new int[n + 1][n + 1];

        for (int len = 2; len <= n; len++) {
            for (int s = 1; s + len - 1 <= n; s++) {
                int e = s + len - 1;
                dp[s][e] = Integer.MAX_VALUE;

                for (int m = s; m < e; m++) {
                    int value = dp[s][m] + dp[m + 1][e] + size[s - 1][0] * size[m][0] * size[e - 1][1];
                    dp[s][e] = Math.min(dp[s][e], value);
                }
            }
        }

        System.out.println(dp[1][n]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[][] size = new int[n][2];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            size[i][0] = Integer.parseInt(st.nextToken());
            size[i][1] = Integer.parseInt(st.nextToken());
        }

        solution(n, size);
    }
}
