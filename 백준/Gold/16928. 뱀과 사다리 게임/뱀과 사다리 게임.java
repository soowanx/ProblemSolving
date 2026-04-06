import java.util.*;
import java.io.*;

public class Main {
    static int solution (Map<Integer, Integer> map) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[] visited = new boolean[101];

        queue.add(new int[] {1, 0});
        visited[1] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            int num = cur[0];
            int cnt = cur[1];

            if (num == 100) {
                return cnt;
            }

            for (int i = 1; i <= 6; i++) {
                int next = num + i;

                if (next <= 100 && !visited[next]) {
                    if (map.containsKey(next)) {
                        next = map.get(next);
                    }

                    if (!visited[next]) {
                        visited[next] = true;
                        queue.add(new int[] {next, cnt + 1});
                    }
                }
            }
        }

        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

         /*
         주사위 1~6
         10x10, 100칸: 1~100 하나씩
         1번 -> 100번
         주사위를 굴리를 횟수의 최솟값
         사다리의 수 n, 뱀의 수 m (1 <= n, m <= 15)
         사다리: x -> y로 이동
         뱀: u -> v로 이동
         */

        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < n + m; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            map.put(from, to);
        }

        System.out.println(solution(map));
    }
}
