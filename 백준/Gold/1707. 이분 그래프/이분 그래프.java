import java.util.*;
import java.io.*;

public class Main {
    static String solution (int v, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < v + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];

            graph.get(from).add(to);
            graph.get(to).add(from);
        }

        // 0 -> 1, -1
        int[] color = new int[v + 1];

        for (int start = 1; start <= v; start++) {
            if (color[start] != 0) continue;

            Queue<Integer> queue = new LinkedList<>();
            queue.add(start);
            color[start] = 1;

            while (!queue.isEmpty()) {
                int cur = queue.poll();

                for (int next : graph.get(cur)) {
                    if (color[next] == 0) {
                        color[next] = -color[cur];
                        queue.add(next);
                    } else if (color[next] == color[cur]) {
                        return "NO";
                    }
                }
            }
        }

        return "YES";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

         /*
         테스트 케이스 개수 k
         정점 개수 v, 간선 개수 e
         */

        StringBuilder sb = new StringBuilder();

        int k = Integer.parseInt(br.readLine());

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());

            int v = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            int[][] edges = new int[e][2];

            for (int j = 0; j < e; j++) {
                st = new StringTokenizer(br.readLine());

                int v1 = Integer.parseInt(st.nextToken());
                int v2 = Integer.parseInt(st.nextToken());

                edges[j][0] = v1;
                edges[j][1] = v2;
            }

            sb.append(solution(v, edges)).append("\n");
        }

        System.out.println(sb);
    }
}
