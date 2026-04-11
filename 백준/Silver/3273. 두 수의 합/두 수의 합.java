import java.util.*;
import java.io.*;

public class Main {
    static void solution(int n, int[] arr, int x) {
        // 정렬
        Arrays.sort(arr);

        // 투포인터
        int left = 0;
        int right = n - 1;

        int count = 0;

        while (left < right) {
            int value = arr[left] + arr[right];

            if (value < x) {
                left++;
            } else if (value > x) {
                right--;
            } else {
                left++;
                right--;
                count++;
            }
        }

        System.out.println(count);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int x = Integer.parseInt(br.readLine());

        solution(n, arr, x);
    }
}
