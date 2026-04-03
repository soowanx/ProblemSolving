import java.util.*;
import java.io.*;

public class Main {
    static List<Integer> list = new ArrayList<>();
    static int[][] house;

    static void bfs(int n, int i, int j) {
        Queue<int[]> queue = new LinkedList<>();

        house[i][j] = 0;
        queue.add(new int[] {i, j});
        int count = 1;

        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            int r = cur[0];
            int c = cur[1];

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr >= 0 && nr < n && nc >= 0 && nc < n && house[nr][nc] == 1) {
                    house[nr][nc] = 0;
                    queue.add(new int[] {nr, nc});
                    count++;
                }
            }
        }

        list.add(count);
    }

    static void solution(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (house[i][j] == 1) {
                    bfs(n, i, j);
                }
            }
        }

        // return
        StringBuilder sb = new StringBuilder();

        sb.append(list.size()).append("\n");

        list.sort((a, b) -> {
            return Integer.compare(a, b);
        });

        for (int l = 0; l < list.size(); l++) {
            sb.append(list.get(l)).append("\n");
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 지도의 크기( 1: 집. 0: 집 x)
        int n = Integer.parseInt(br.readLine());

        // 나중에 boolean으로 바꿔보기
        house = new int[n][n];

        for (int i = 0; i < n; i++) {
            char[] temp = br.readLine().toCharArray();

            for (int j = 0; j < n; j++) {
                if (temp[j] == '1') {
                    house[i][j] = 1;
                }
            }
        }

        solution(n);
    }
}
