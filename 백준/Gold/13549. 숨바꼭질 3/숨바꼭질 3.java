import java.util.*;
import java.io.*;

public class Main {
    static int[] dp = {-1, 1};

    static int solution(int n, int k) {
        boolean[] visited = new boolean[500_000];
        visited[n] = true;

        // 우선순위 큐: 비용 낮은 순
        Deque<int[]> queue = new ArrayDeque<>();
        queue.addFirst(new int[] {n, 0});

        // 다익스트라 + 지나온 경로 저장
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int pos = cur[0];
            int time = cur[1];

            if (pos == k) {
                return time;
            }

            int next = pos * 2;
            if (next <= 200000 && !visited[next]) {
                visited[next] = true;
                queue.addFirst(new int[] {next, time});
            }

            for (int d : dp) {
                next = pos + d;
                if (next >= 0 && next <= 200000 && !visited[next]) {
                    visited[next] = true;
                    queue.addLast(new int[] {next, time + 1});
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        System.out.println(solution(n, k));
    }
}
