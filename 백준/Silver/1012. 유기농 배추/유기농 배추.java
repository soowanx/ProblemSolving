import java.util.*;
import java.io.*;

public class Main {
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};

    static void bfs(int m, int n, int i, int j, boolean[][] arr) {
        Queue<int[]> queue = new LinkedList<>();

        queue.add(new int[] {i, j});
        arr[i][j] = false;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            int r = cur[0];
            int c = cur[1];

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr >= 0 && nr < m && nc >= 0 && nc < n && arr[nr][nc]) {
                    arr[nr][nc] = false;
                    queue.add(new int[] {nr, nc});
                }
            }
        }
    }

    static int solution (int m, int n, boolean[][] arr) {
        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j]) {
                    bfs(m, n, i, j, arr);
                    count++;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

         /*
         상하좌우 이동
         0: 배추x. 1: 배추o
         테스트 케이스 개수 T
         가로 m (1 <= m <= 50)
         세로 n (1 <= n <= 50)
         배추 위치의 개수 k (1 <= k <= 2500)
         k줄: 배추의 위치 X (0 <= X <= M - 1), Y (0 <= Y <= N - 1)
          */

        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());

            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            boolean[][] arr = new boolean[m][n];

            for (int j = 0; j < k; j++) {
                st = new StringTokenizer(br.readLine());
                arr[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = true;
            }

            sb.append(solution(m, n, arr)).append("\n");
        }

        System.out.println(sb);
    }
}
