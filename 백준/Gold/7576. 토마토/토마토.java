import java.util.*;
import java.io.*;

public class Main {
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};

    static int solution (int n, int m, int[][] arr) {
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 1) {
                    queue.add(new int[] {i, j, 0});
                }
            }
        }

        int time = 0;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            int r = cur[0];
            int c = cur[1];
            time = cur[2];

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (nr >= 0 && nr < n && nc >= 0 && nc < m && arr[nr][nc] == 0) {
                    arr[nr][nc] = 1;
                    queue.add(new int[] {nr, nc, time + 1});
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 0) {
                    return -1;
                }
            }
        }

        return time;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

         /*
         가로 칸 m, 세로 칸 n (2 <= m, n <= 1000)
         1: 익음. 0: 익지 않음. -1: 없음
         모두 익을 때까지 최소 날짜. 모두 익어있으면 0, 익지 못하면 -1
         */

        st = new StringTokenizer(br.readLine());

        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int[][] arr = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solution(n, m, arr));
    }
}
