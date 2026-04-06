import java.util.*;
import java.io.*;

public class Main {
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};

    static int solution (int n, int m, int[][] arr) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][][] visited = new boolean[n][m][2];

        // row, col, 이동 횟수, 벽 부순 횟수
        queue.add(new int[] {0, 0, 1, 0});
        visited[0][0][0] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            int r = cur[0];
            int c = cur[1];
            int cnt = cur[2];
            int wall = cur[3];

            if (r == n - 1 && c == m - 1) {
                return cnt;
            }

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (nr >= 0 && nr < n && nc >= 0 && nc < m) {
                    if (wall == 0) {
                        if (arr[nr][nc] == 0 && !visited[nr][nc][0]) {
                            queue.add(new int[] {nr, nc, cnt + 1, wall});
                            visited[nr][nc][0] = true;
                        } else if (arr[nr][nc] == 1 && !visited[nr][nc][1]) {
                            queue.add(new int[] {nr, nc, cnt + 1, wall + 1});
                            visited[nr][nc][1] = true;
                        }
                    } else {
                        if (arr[nr][nc] == 0 && !visited[nr][nc][1]) {
                            queue.add(new int[] {nr, nc, cnt + 1, wall});
                            visited[nr][nc][1] = true;
                        }
                    }
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

         /*
         nxm
         0: 이동 o. 1: 이동 x 벽
         (1, 1) -> (n, m)
         최단 경로: 시작 칸, 끝 칸 포함
         최대 벽 1개 부수기
         (1 <= n, m <= 1000)
         불가능하면 -1
         */

        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] arr = new int[n][m];

        for (int i = 0; i < n; i++) {
            String temp = br.readLine();

            for (int j = 0; j < m; j++) {
                arr[i][j] = temp.charAt(j) - '0';
            }
        }

        if (n == 1 && m == 1) {
            System.out.println(1);
            return;
        }

        System.out.println(solution(n, m, arr));
    }
}
