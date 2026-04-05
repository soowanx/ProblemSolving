import java.awt.AlphaComposite;
import java.util.*;
import java.io.*;

public class Main {
    static void solution (int n, int k) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[] visited = new boolean[210_000];

        queue.add(new int[] {n, 0});
        visited[n] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            int pos = cur[0];
            int time = cur[1];

            if (pos == k) {
                System.out.println(time);
                break;
            }

            int next = pos - 1;
            if (next >= 0 && !visited[next]) {
                visited[next] = true;
                queue.add(new int[] {next, time + 1});
            }

            next = pos + 1;
            if (!visited[next]) {
                visited[next] = true;
                queue.add(new int[] {next, time + 1});
            }

            next = pos * 2;
            if (pos < k && !visited[next]) {
                visited[next] = true;
                queue.add(new int[] {next, time + 1});
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

         /*
         수빈: n (0 <= n <= 100,000)
         동생: k (0 <= k <= 100,000)

         수빈: x -> x-1 or x+1 or 2*x
          */

        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        solution(n, k);
    }
}
