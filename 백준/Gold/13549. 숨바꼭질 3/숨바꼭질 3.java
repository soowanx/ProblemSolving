import java.util.*;
import java.io.*;

public class Main {
    static int[] dp = {-1, 1};

    static int solution(int n, int k) {
        // dist 배열
        int[] dist = new int[500000];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[n] = 0;

        // 우선순위 큐: 비용 낮은 순
        Queue<int[]> queue = new PriorityQueue<>((a, b) -> {
            return Integer.compare(a[1], b[1]);
        });

        queue.add(new int[] {n, 0});

        // 다익스트라 + 지나온 경로 저장
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int pos = cur[0];
            int time = cur[1];

            if (pos == k) {
                return time;
            }

            if (time > dist[pos]) {
                continue;
            }

            int next = pos * 2;
            if (next <= 200000 && time < dist[next]) {
                dist[next] = time;
                queue.add(new int[] {next, time});
            }

            for (int d : dp) {
                next = pos + d;
                if (next >= 0 && time + 1 < dist[next]) {
                    dist[next] = time + 1;
                    queue.add(new int[] {next, time + 1});
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
