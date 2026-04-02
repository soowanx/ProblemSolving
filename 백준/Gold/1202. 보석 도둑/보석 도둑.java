import java.util.*;
import java.io.*;

public class Main {
    static void solution(int n, int[][] gold, int k, int[] bag) {
        // 보석 무게 순
        Arrays.sort(gold, (a, b) -> {
            if (a[0] == b[0]) return a[1] - b[1];
            return a[0] - b[0];
        });

        // 가방 용량 순
        Arrays.sort(bag);

        // 현재 가방에 담을 수 있는 보석 중 가격이 가장 큰 것 선택
        Queue<Integer> pq = new PriorityQueue<>((a, b) -> {
            return Integer.compare(b, a);
        });

        long answer = 0;
        int idx = 0;

        for (int i = 0; i < k; i++) {
            int capacity = bag[i];

            while (idx < n && gold[idx][0] <= capacity) {
                pq.add(gold[idx][1]);
                idx++;
            }

            if (!pq.isEmpty()) {
                answer += pq.poll();
            }
        }

        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 총 보석 n개. 각 보석은 무게 m, 가격 v
        // 가방 k개. 최대 무게는 c. 최대 1개의 보석만 넣을 수 있음.
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] arr1 = new int[n][2];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            arr1[i][0] = Integer.parseInt(st.nextToken());
            arr1[i][1] = Integer.parseInt(st.nextToken());
        }

        int[] arr2 = new int[k];

        for (int i = 0; i < k; i++) {
            arr2[i] = Integer.parseInt(br.readLine());
        }

        solution(n, arr1, k, arr2);
    }
}
