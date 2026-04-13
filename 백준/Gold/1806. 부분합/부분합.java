import java.util.*;
import java.io.*;

public class Main {
    static void solution(int[] arr, int s) {
        int left = 0;
        int right = 0;

        int answer = arr.length + 1;
        int sum = arr[0];

        while (true) {
            if (sum < s) {
                if (right == arr.length - 1) {
                    break;
                }

                sum += arr[++right];
            } else {
                answer = Math.min(answer, right - left + 1);
                sum -= arr[left++];
            }
        }

        System.out.println(answer == arr.length + 1 ? 0 : answer);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        solution(arr, s);
    }
}
