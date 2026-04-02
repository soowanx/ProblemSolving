import java.util.*;
import java.io.*;

public class Main {
    static int cnt = 1;

    static void dfs(int r, List<List<Integer>> graph, int[] order, boolean[] visited) {
        visited[r] = true;
        order[r] = cnt++;

        for (int next : graph.get(r)) {
            if (!visited[next]) {
                dfs(next, graph, order, visited);
            }
        }
    }

    static void solution(int n, int m, int r, int[][] dfs) {
        // graph 만들기
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n + 1; i++) graph.add(new ArrayList<>());

        for (int[] d : dfs) {
            graph.get(d[0]).add(d[1]);
            graph.get(d[1]).add(d[0]);
        }

        for (int i = 0; i < n + 1; i++) {
            graph.get(i).sort((a, b) -> {
                return Integer.compare(a, b);
            });
        }

        // 방문 배열
        boolean[] visited = new boolean[n + 1];

        // 순서 배열
        int[] order = new int[n + 1];

        // dfs
        dfs(r, graph, order, visited);

        // return
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < order.length; i++) sb.append(order[i]).append("\n");
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb.toString());
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 정점의 수 n, 간선의 수 m, 시작 정점 r
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        int[][] dfs = new int[m][2];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            // 양방향 간선 u, v
            dfs[i][0] = Integer.parseInt(st.nextToken());
            dfs[i][1] = Integer.parseInt(st.nextToken());
        }

        solution(n, m, r, dfs);
    }
}
