import java.util.*;
import java.io.*;

public class Main {
    static String solution (int vCount, int eCount, int start, int[][] edges) {
        // 그래프 초기화
        List<List<int[]>> graph = new ArrayList<>();

        for (int i = 0; i <= vCount; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            graph.get(edge[0]).add(new int[] {edge[1], edge[2]});
        }

        // dist 배열 초기화
        int[] dist = new int[vCount + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        // 우선순위 큐: 비용 낮은 순
        Queue<int[]> queue = new PriorityQueue<>((a, b) -> {
            return Integer.compare(a[1], b[1]);
        });

        queue.add(new int[] {start, 0});

        // 다익스트라
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int curNum = cur[0];
            int curCost = cur[1];

            if (curCost > dist[curNum]) continue;

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
        int[][] edges = new int[eCount][3];

        int k = Integer.parseInt(br.readLine());

        for (int i = 0; i < eCount; i++) {
            st = new StringTokenizer(br.readLine());

            edges[i][0] = Integer.parseInt(st.nextToken());
            edges[i][1] = Integer.parseInt(st.nextToken());
            edges[i][2] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(vCount, eCount, k, edges));
    }
}
