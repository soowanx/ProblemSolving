import java.util.*;
import java.io.*;

public class Main {
    static int[] dijkstra(int n, List<List<int[]>> graph, int start) {
        Queue<int[]> queue = new PriorityQueue<>((a, b) -> {
            return Integer.compare(a[1], b[1]);
        });

        queue.add(new int[] {start, 0});

        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int pos = cur[0];
            int cost = cur[1];

            if (cost > dist[pos]) {
                continue;
            }

            for (int[] next : graph.get(pos)) {
                int nextPos = next[0];
                int nextCost = next[1];

                int total = cost + nextCost;

                if (total < dist[nextPos]) {
                    dist[nextPos] = total;
                    queue.add(new int[] {nextPos, total});
                }
            }
        }

        return dist;
    }

    static String solution(int n, int s, int g, int h, int[][] edges, List<Integer> dest) {
        // 그래프 생성
        List<List<int[]>> graph = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        int gh = 0;
        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            int c = edge[2];

            graph.get(a).add(new int[] {b, c});
            graph.get(b).add(new int[] {a, c});

            if ((a == g && b == h) || (a == h && b == g)) {
                gh = c;
            }
        }

        // s -> g, h
        int[] distS = dijkstra(n, graph, s);

        // g -> dest
        int[] distG = dijkstra(n, graph, g);

        // h -> dest
        int[] distH = dijkstra(n, graph, h);

        List<Integer> answer = new ArrayList<>();

        for (int x : dest) {
            if (distS[x] == Integer.MAX_VALUE || distS[g] == Integer.MAX_VALUE || distS[h] == Integer.MAX_VALUE || distH[x] == Integer.MAX_VALUE || distG[x] == Integer.MAX_VALUE) {
                continue;
            }

            if (distS[x] == distS[g] + gh + distH[x] || distS[x] == distS[h] + gh + distG[x]) {
                answer.add(x);
            }
        }

        StringBuilder sb = new StringBuilder();

        answer.sort((a, b) -> {
            return Integer.compare(a, b);
        });

        for (int a : answer) {
            sb.append(a).append(" ");
        }

        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int test = Integer.parseInt(br.readLine());

        for (int i = 0; i < test; i++) {
            // 교차로, 도로, 목적지 후보
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            // 출발지, 지나간 교차로
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            int[][] edges = new int[m][3];
            for (int j = 0; j < m; j++) {
                // a, b 사이 길이 d 양방향 도로
                st = new StringTokenizer(br.readLine());
                edges[j][0] = Integer.parseInt(st.nextToken());
                edges[j][1] = Integer.parseInt(st.nextToken());
                edges[j][2] = Integer.parseInt(st.nextToken());
            }

            List<Integer> dest = new ArrayList<>();
            for (int j = 0; j < t; j++) {
                // 목적지 후보
                dest.add(Integer.parseInt(br.readLine()));
            }

            sb.append(solution(n, s, g, h, edges, dest)).append("\n");
        }

        System.out.print(sb);
    }
}
