import java.util.*;
import java.io.*;

public class Main {
    static StringBuilder sb = new StringBuilder();

    static void bfs (int v, List<List<Integer>> graph, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();

        queue.add(v);
        visited[v] = true;

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            sb.append(cur).append(" ");

            for (int next : graph.get(cur)) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.add(next);
                }
            }
        }
    }

    static void dfs(int v, List<List<Integer>> graph, boolean[] visited) {
        visited[v] = true;
        sb.append(v).append(" ");

        for (int next : graph.get(v)) {
            if (!visited[next]) {
                dfs(next, graph, visited);
            }
        }
    }

    static void solution(int n, int v, int[][] pair) {
        // graph 만들기
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n + 1; i++) graph.add(new ArrayList<>());

        for (int[] p : pair) {
            graph.get(p[0]).add(p[1]);
            graph.get(p[1]).add(p[0]);
        }

        for (int i = 0; i < n + 1; i++) {
            graph.get(i).sort((a, b) -> {
                return Integer.compare(a, b);
            });
        }

        dfs(v, graph, new boolean[n + 1]);
        sb.deleteCharAt(sb.length() - 1);
        sb.append("\n");

        bfs(v, graph, new boolean[n + 1]);
        sb.deleteCharAt(sb.length() - 1);

        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 정점의 개수 n, 간선의 개수 m, 시작 정점 v
        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());

        int[][] pair = new int[m][2];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            pair[i][0] = Integer.parseInt(st.nextToken());
            pair[i][1] = Integer.parseInt(st.nextToken());
        }

        // dfs, bfs
        solution(n, v, pair);
    }
}
