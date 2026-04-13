import java.util.*;
import java.io.*;

public class Main {
    static void solution(int n, int[] arr) {
        int[][] dp = new int[2][n];

        // 앞 -> 뒤
        dp[0][0] = 1;

        for (int i = 1; i < n; i++) {
            dp[0][i] = 1;

            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    dp[0][i] = Math.max(dp[0][i], dp[0][j] + 1);
                }
            }
        }

        // 뒤 -> 앞
        dp[1][n - 1] = 1;

        for (int i = n - 2; i >= 0; i--) {
            dp[1][i] = 1;

            for (int j = i + 1; j < n; j++) {
                if (arr[i] > arr[j]) {
                    dp[1][i] = Math.max(dp[1][i], dp[1][j] + 1);
                }
            }
        }

        // 출력
        int max = 0;

        for (int i = 0; i < n; i++) {
            max = Math.max(max, dp[0][i] + dp[1][i]);
        }

        System.out.println(max - 1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        solution(n, arr);
    }
}
