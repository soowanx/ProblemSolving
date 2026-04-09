import java.util.*;
import java.io.*;

public class Main {
    static String solution (int vCount, int eCount, int start, int[][] edges) {
        List<List<int[]>> graph = new ArrayList<>();

        for (int i = 0; i <= vCount; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            graph.get(u).add(new int[] {v, w});
        }

        int[] dist = new int[vCount + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;


        Queue<int[]> queue = new PriorityQueue<>((a, b) -> {
            return Integer.compare(a[1], b[1]);
        });

        queue.add(new int[] {start, 0});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int curNum = cur[0];
            int curCost = cur[1];

            for (int[] next : graph.get(curNum)) {
                int nextNum = next[0];
                int nextCost = next[1];

                int cost = curCost + nextCost;

                if (cost < dist[nextNum]) {
                    dist[nextNum] = cost;
                    queue.add(new int[] {nextNum, cost});
                }
            }
        }


        StringBuilder sb = new StringBuilder();

        for (int i = 1; i < dist.length; i++) {
            sb.append(dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]).append("\n");
        }

        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int vCount = Integer.parseInt(st.nextToken());
        int eCount = Integer.parseInt(st.nextToken());

        int k = Integer.parseInt(br.readLine());

        int[][] edges = new int[eCount][3];

        for (int i = 0; i < eCount; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            edges[i][0] = u;
            edges[i][1] = v;
            edges[i][2] = w;
        }

        System.out.println(solution(vCount, eCount, k, edges));
    }
}
