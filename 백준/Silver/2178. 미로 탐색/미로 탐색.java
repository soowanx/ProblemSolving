import java.util.*;
import java.io.*;

public class Main {
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};

    static void solution (int n, int m, boolean[][] arr) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {0, 0, 1});

        int count = 0;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            int r = cur[0];
            int c = cur[1];
            int cnt = cur[2];

            if (r == n - 1 && c == m - 1) {
                count = cnt;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (nr >= 0 && nr < n && nc >= 0 && nc < m && arr[nr][nc]) {
                    arr[nr][nc] = false;
                    queue.add(new int[] {nr, nc, cnt + 1});
                }
            }
        }

        System.out.println(count);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

         /*
         NxM 크기 미로
         1: 이동 o. 0: 이동 x.
         (1, 1) -> (n, m) 지나는 최소 칸 수
         n, m (2 <= n, m <= 100)
          */

        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        boolean[][] arr = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            char[] chArr = br.readLine().toCharArray();

            for (int j = 0; j < m; j++) {
                if (chArr[j] == '1') {
                    arr[i][j] = true;
                }
            }
        }

        solution(n, m, arr);
    }
}
