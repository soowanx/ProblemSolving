import java.util.*;
import java.io.*;

public class Main {
    static int[] solution (int n, int eCount, int[][] edges, int start, int end1, int end2) {
        // 그래프 초기화
        List<List<int[]>> graph = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            graph.get(edge[0]).add(new int[] {edge[1], edge[2]});
            graph.get(edge[1]).add(new int[] {edge[0], edge[2]});
        }

        // dist 배열 초기화
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        // 우선순위 큐: 비용 낮은 순
        Queue<int[]> queue = new PriorityQueue<>((a, b) -> {
            return Integer.compare(a[1], b[1]);
        });

        queue.add(new int[] {start, 0});

        // 다익스트라 + 지나온 경로 저장
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

        return new int[] {dist[end1], dist[end2]};
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int eCount = Integer.parseInt(st.nextToken());
        int[][] edges = new int[eCount][3];

        for (int i = 0; i < eCount; i++) {
            st = new StringTokenizer(br.readLine());

            edges[i][0] = Integer.parseInt(st.nextToken());
            edges[i][1] = Integer.parseInt(st.nextToken());
            edges[i][2] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        // 1 -> v1 -> v2 -> n
        int dist1[] = new int[3];

        // 1 -> v2 -> v1 -> n
        int dist2[] = new int[3];

        // 1 -> v1, v2
        int[] answer;
        answer = solution(n, eCount, edges, 1, v1, v2);
        dist1[0] = answer[0];
        dist2[0] = answer[1];

        // v1 -> v2, n
        answer = solution(n, eCount, edges, v1, v2, n);
        dist1[1] = answer[0];
        dist2[2] = answer[1];

        // v2 -> v1, n
        answer = solution(n, eCount, edges, v2, v1, n);
        dist2[1] = answer[0];
        dist1[2] = answer[1];

        int answer1 = 0;
        for (int d : dist1) {
            if (d == Integer.MAX_VALUE) {
                answer1 = -1;
                break;
            } else {
                answer1 += d;
            }
        }

        int answer2 = 0;
        for (int d : dist2) {
            if (d == Integer.MAX_VALUE) {
                answer2 = -1;
                break;
            } else {
                answer2 += d;
            }
        }

        if (answer1 == -1 && answer2 == -1) {
            System.out.println(-1);
        } else if (answer1 == -1) {
            System.out.println(answer2);
        } else if (answer2 == -1) {
            System.out.println(answer1);
        } else {
            System.out.println(Math.min(answer1, answer2));
        }
    }
}
