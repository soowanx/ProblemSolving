import java.util.*;
import java.io.*;

public class Main {
    static void solution(int n, int k, int[] coin) {
        int[] dp = new int[k + 1];
        dp[0] = 1;

        for (int c : coin) {
            for (int i = c; i <= k; i++) {
                dp[i] += dp[i - c];
            }
        }

        System.out.println(dp[k]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] coin = new int[n];

        for (int i = 0; i < n; i++) {
            coin[i] = Integer.parseInt(br.readLine());
        }

        solution(n, k, coin);
    }
}
