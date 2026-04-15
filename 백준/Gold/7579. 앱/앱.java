import java.util.*;
import java.io.*;

public class Main {
    static void solution(int n, int m, int[] memory, int[] cost) {
        int[] dp = new int[10001];

        for (int i = 0; i < n; i++) {
            int mem = memory[i];
            int c = cost[i];

            for (int j = 10000; j >= c; j--) {
                dp[j] = Math.max(dp[j], dp[j - c] + mem);
            }
        }

        for (int i = 0; i < dp.length; i++) {
            if (dp[i] >= m) {
                System.out.println(i);
                return;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] memory = new int[n];
        int[] cost = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            memory[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
        }

        solution(n, m, memory, cost);
    }
}
