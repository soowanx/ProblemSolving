import java.util.*;
import java.io.*;

public class Main {
    static String solution (int n, int[] order, int m, int[][] edges) {
        boolean[][] graph = new boolean[n + 1][n + 1];
        int[] degree = new int[n + 1];

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                graph[order[i]][order[j]] = true;
                degree[order[j]]++;
            }
        }

        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];

            if (graph[a][b]) {
                graph[a][b] = false;
                graph[b][a] = true;
                degree[b]--;
                degree[a]++;
            } else {
                graph[b][a] = false;
                graph[a][b] = true;
                degree[a]--;
                degree[b]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i <= n; i++) {
            if (degree[i] == 0) {
                queue.add(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        int count = 0;

        while (!queue.isEmpty()) {
            if (queue.size() > 1) return "?";

            int cur = queue.poll();

            sb.append(cur).append(" ");
            count++;

            for (int next = 1; next <= n; next++) {
                if (graph[cur][next]) {
                    degree[next]--;

                    if (degree[next] == 0) {
                        queue.add(next);
                    }
                }
            }
        }

        return count < n ? "IMPOSSIBLE" : sb.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());

            int[] order = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                order[j] = Integer.parseInt(st.nextToken());
            }

            int m = Integer.parseInt(br.readLine());

            int[][] edges = new int[m][2];
            for (int j = 0; j < m; j++) {
                st = new StringTokenizer(br.readLine());
                edges[j][0] = Integer.parseInt(st.nextToken());
                edges[j][1] = Integer.parseInt(st.nextToken());
            }

            sb.append(solution(n, order, m, edges)).append("\n");
        }

        System.out.println(sb);
    }
}
