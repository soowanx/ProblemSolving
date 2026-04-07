import java.util.*;
import java.io.*;

public class Main {
    static String solution (int n, int m, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        int[] degree = new int[n + 1];

        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];

            graph.get(from).add(to);
            degree[to]++;
        }

        Queue<Integer> queue = new PriorityQueue<>((a, b) -> {
            return Integer.compare(a, b);
        });
        for (int i = 1; i <= n; i++) {
            if (degree[i] == 0) {
                queue.add(i);
            }
        }

        StringBuilder sb = new StringBuilder();

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            sb.append(cur).append(" ");

            for (int next : graph.get(cur)) {
                degree[next]--;

                if (degree[next] == 0) {
                    queue.add(next);
                }
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] edges = new int[m][2];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            edges[i][0] = Integer.parseInt(st.nextToken());
            edges[i][1] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(n, m, edges));
    }
}
