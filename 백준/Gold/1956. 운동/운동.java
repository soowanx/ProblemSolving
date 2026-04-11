import java.util.*;
import java.io.*;

public class Main {
    static int MAX = Integer.MAX_VALUE;

    static void solution(int v, int[][] edges) {
        // dist 배열
        int[][] dist = new int[v + 1][v + 1];

        for (int i = 0; i <= v; i++) {
            for (int j = 0; j <= v; j++) {
                dist[i][j] = MAX;
            }
        }

        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            int c = edge[2];

            dist[a][b] = Math.min(dist[a][b], c);
        }

        // 플로이드 워셜
        for (int k = 1; k <= v; k++) {
            for (int i = 1; i <= v; i++) {
                for (int j = 1; j <= v; j++) {
                    if (dist[i][k] == MAX || dist[k][j] == MAX) {
                        continue;
                    }

                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        // return
        int min = MAX;

        for (int i = 1; i <= v; i++) {
            if (dist[i][i] == MAX) {
                continue;
            }

            min = Integer.min(min, dist[i][i]);
        }

        System.out.println(min == MAX ? -1 : min);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        int[][] edges = new int[e][3];

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            edges[i][0] = Integer.parseInt(st.nextToken());
            edges[i][1] = Integer.parseInt(st.nextToken());
            edges[i][2] = Integer.parseInt(st.nextToken());
        }

        solution(v, edges);
    }
}
