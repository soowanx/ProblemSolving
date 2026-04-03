import java.util.*;
import java.io.*;

public class Main {
    static void solution(int n, int m, int[][] pair) {
        // graph 만들기
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n + 1; i++) graph.add(new ArrayList<>());

        for (int[] p : pair) {
            graph.get(p[0]).add(p[1]);
            graph.get(p[1]).add(p[0]);
        }

        int count = 0;

        // bfs
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];

        queue.add(1);
        visited[1] = true;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int next : graph.get(cur)) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.add(next);
                    count++;
                }
            }
        }

        // return
        System.out.println(count);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 컴퓨터의 수 (100이하인 양의 정수) (1번~)
        int n = Integer.parseInt(br.readLine());

        // 컴퓨터 쌍의 수
        int m = Integer.parseInt(br.readLine());

        int[][] pair = new int[m][2];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            pair[i][0] = Integer.parseInt(st.nextToken());
            pair[i][1] = Integer.parseInt(st.nextToken());
        }

        // 1번 컴퓨터 바이러ㅣ스
        solution(n, m, pair);
    }
}
