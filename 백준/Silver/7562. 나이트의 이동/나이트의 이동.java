import java.util.*;
import java.io.*;

public class Main {
    static int[] dx = {-2, -2, -1, -1, 1, 1, 2, 2};
    static int[] dy = {-1, 1, -2, 2, -2, 2, -1, 1};

    static int solution (int l, int[] start, int[] end) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[l][l];

        queue.add(new int[] {start[0], start[1], 0});
        visited[start[0]][start[1]] = true;

        int count = 0;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            int x = cur[0];
            int y = cur[1];
            int cnt = cur[2];

            if (x == end[0] && y == end[1]) {
                count = cnt;
                break;
            }

            for (int i = 0; i < 8; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && nx < l && ny >= 0 && ny < l && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.add(new int[] {nx, ny, cnt + 1});
                }
            }
        }

        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

         /*
         나이트 몇 번 이됭?
         테스트 케이스 개수
            체스판 한 변의 길이 l (4 <= l <= 300)
            현재 칸
            목표 칸
          */

        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < t; i++) {
            int l = Integer.parseInt(br.readLine());

            int[] start = new int[2];
            int[] end = new int[2];

            st = new StringTokenizer(br.readLine());
            start[0] = Integer.parseInt(st.nextToken());
            start[1] = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            end[0] = Integer.parseInt(st.nextToken());
            end[1] = Integer.parseInt(st.nextToken());

            sb.append(solution(l, start, end)).append("\n");
        }

        System.out.println(sb);
    }
}
