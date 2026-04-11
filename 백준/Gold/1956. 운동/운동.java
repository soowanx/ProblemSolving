import java.util.*;
import java.io.*;

public class Main {
    static int[] dijkstra(List<List<int[]>> graph, int[] dist, int start) {
        Queue<int[]> queue = new PriorityQueue<>((a, b) -> {
            return Integer.compare(a[1], b[1]);
        });

        queue.add(new int[] {start, 0});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int num = cur[0];
            int d = cur[1];

            if (dist[num] < d) {
                continue;
            }

            for (int[] next : graph.get(num)) {
                int nextNum = next[0];
                int nextD = next[1];

                int value = d + nextD;

                if (value < dist[nextNum]) {
                    dist[nextNum] = value;
                    queue.add(new int[] {nextNum, value});
                }
            }
        }

        return dist;
    }

    static void solution(int v, int[][] edges) {
        // 그래프 만들기
        List<List<int[]>> graph = new ArrayList<>();

        for (int i = 0; i <= v; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            graph.get(edge[0]).add(new int[] {edge[1], edge[2]});
        }

        // dist 배열
        int[][] dist = new int[v + 1][v + 1];

        for (int i = 0; i <= v; i++) {
            for (int j = 0; j <= v; j++) {
                dist[i][j] = i == j ? 0 : Integer.MAX_VALUE;
            }
        }

        // 다익스트라
        for (int i = 1; i <= v; i++) {
            dist[i] = dijkstra(graph, dist[i], i);
        }

        // return
        int min = Integer.MAX_VALUE;

        for (int i = 1; i < v; i++) {
            for (int j = i + 1; j <= v; j++) {
                if (dist[i][j] == Integer.MAX_VALUE || dist[j][i] == Integer.MAX_VALUE) {
                    continue;
                }

                int d = dist[i][j] + dist[j][i];

                if (d < min) {
                    min = d;
                }
            }
        }

        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        int[][] edges = new int[e][3];

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            edges[i][0] = Integer.parseInt(st.nextToken());
            edges[i][1] = Integer.parseInt(st.nextToken());
            edges[i][2] = Integer.parseInt(st.nextToken());
        }

        solution(v, edges);
    }
}
