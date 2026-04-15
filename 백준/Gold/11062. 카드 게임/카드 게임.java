import java.util.*;
import java.io.*;

public class Main {
    static int solution(int n, int[] card) {
        int[][] dp = new int[n][n];
        int[] prefix = new int[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + card[i];
            dp[i][i] = card[i];
        }

        for (int len = 2; len <= n; len++) {
            for (int start = 0; start + len - 1 < n; start++) {
                int end = start + len - 1;
                int sum = prefix[end + 1] - prefix[start];

                dp[start][end] = Math.max(
                    sum - dp[start + 1][end],
                    sum - dp[start][end - 1]
                );
            }
        }

        return dp[0][n - 1];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            int[] card = new int[n];

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                card[j] = Integer.parseInt(st.nextToken());
            }

            sb.append(solution(n, card)).append("\n");
        }

        System.out.println(sb);
    }
}
