import java.util.*;
import java.io.*;

public class Main {
    static int solution(int k, int[] arr) {
        int[][] dp = new int[k + 1][k + 1];
        int[] sum = new int[k + 1];

        for (int i = 0; i < arr.length; i++) {
            sum[i + 1] = sum[i] + arr[i];
        }

        for (int len = 2; len <= k; len++) {
            for (int start = 1; start + len - 1 <= k; start++) {
                int end = start + len - 1;
                dp[start][end] = Integer.MAX_VALUE;

                for (int mid = start; mid < end; mid++) {
                    dp[start][end] = Math.min(dp[start][end],
                        dp[start][mid] + dp[mid + 1][end] + sum[end] - sum[start - 1]);
                }
            }
        }

        return dp[1][k];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            int k = Integer.parseInt(br.readLine());
            int[] arr = new int[k];

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < k; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
            }

            sb.append(solution(k, arr)).append("\n");
        }

        System.out.println(sb);
    }
}
