import java.util.*;
import java.io.*;

public class Main {
    static int[][] map;
    static int[][] dp;
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};

    static int dfs(int r, int c) {
        if (r == map.length - 1 && c == map[0].length - 1) {
            return 1;
        }

        if (dp[r][c] != -1) {
            return dp[r][c];
        }

        dp[r][c] = 0;

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (nr < 0 || nr >= map.length || nc < 0 || nc >= map[0].length) {
                continue;
            }

            if (map[nr][nc] < map[r][c]) {
                dp[r][c] +=dfs(nr, nc);
            }
        }

        return dp[r][c];
    }

    static void solution(int r, int c) {
        dp = new int[r][c];

        for (int i = 0; i < r; i++) {
            Arrays.fill(dp[i], -1);
        }

        System.out.println(dfs(0, 0));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        map = new int[m][n];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solution(m, n);
    }
}
