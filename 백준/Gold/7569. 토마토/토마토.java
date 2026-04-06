import java.util.*;
import java.io.*;

public class Main {
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};
    static int[] dh = {-1, 1};

    static int solution (int n, int m, int h, int[][][] arr) {
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    if (arr[i][j][k] == 1) {
                        queue.add(new int[] {i, j, k, 0});
                    }
                }
            }
        }

        int time = 0;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            int height = cur[0];
            int r = cur[1];
            int c = cur[2];
            time = cur[3];

            // 상하좌우
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (nr >= 0 && nr < n && nc >= 0 && nc < m && arr[height][nr][nc] == 0) {
                    arr[height][nr][nc] = 1;
                    queue.add(new int[] {height, nr, nc, time + 1});
                }
            }

            // 위아래
            for (int i = 0; i < 2; i++) {
                int nh = height + dh[i];

                if (nh >= 0 && nh < h && arr[nh][r][c] == 0) {
                    arr[nh][r][c] = 1;
                    queue.add(new int[] {nh, r, c, time + 1});
                }
            }
        }

        // 모두 익지 못하는 상황
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    if (arr[i][j][k] == 0) return -1;
                }
            }
        }

        return time;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

         /*
         가로 칸 m, 세로 칸 n, 높이 h (2 <= m, n <= 100. 1 <= h <= 100)
         1: 익음. 0: 익지 않음. -1: 없음
         모두 익을 때까지 최소 날짜. 모두 익어있으면 0, 익지 못하면 -1
         */

        st = new StringTokenizer(br.readLine());

        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        int[][][] arr = new int[h][n][m];

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < n; j++) {
                st = new StringTokenizer(br.readLine());

                for (int k = 0; k < m; k++) {
                    arr[i][j][k] = Integer.parseInt(st.nextToken());
                }
            }
        }

        System.out.println(solution(n, m, h, arr));
    }
}
