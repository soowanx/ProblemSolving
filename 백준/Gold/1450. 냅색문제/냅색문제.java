import java.util.*;
import java.io.*;

public class Main {
    static int getCount(List<Integer> list, int remain) {
        int left = 0;
        int right = list.size();

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (list.get(mid) <= remain) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    static void dfs(int idx, int sum, int[] arr, List<Integer> list, int c) {
        if (sum > c) {
            return;
        }
        
        if (idx == arr.length) {
            list.add(sum);
            return;
        }

        // 안넣음
        dfs(idx + 1, sum, arr, list, c);

        // 넣음
        dfs(idx + 1, sum + arr[idx], arr, list, c);
    }

    static void solution(int n, int[] arr, int c) {
        // 반으로 나누기
        int mid = n / 2;
        int[] leftArr = Arrays.copyOfRange(arr, 0, mid);
        int[] rightArr = Arrays.copyOfRange(arr, mid, n);

        // 부분집합 합
        List<Integer> leftSum = new ArrayList<>();
        List<Integer> rightSum = new ArrayList<>();

        dfs(0, 0, leftArr, leftSum, c);
        dfs(0, 0, rightArr, rightSum, c);

        // 정렬 후 이분탐색
        rightSum.sort((a, b) -> {
            return Integer.compare(a, b);
        });

        long count = 0;

        for (int left : leftSum) {
            int remain = c - left;
            count += getCount(rightSum, remain);
        }

        System.out.println(count);
    }

    public static void main(String[] args) throws IOException {
        /*
        n개의 물건. 최대 c 무게를 담는 가방.
        n개의 물건을 가방에 넣는 방법의 수
         */

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        solution(n, arr, c);
    }
}
