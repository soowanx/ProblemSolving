import java.util.*;
import java.io.*;

public class Main {
    static boolean[] getPrime(int n) {
        boolean[] prime = new boolean[n + 1];
        Arrays.fill(prime, true);

        prime[0] = false;
        prime[1] = false;

        for (int i = 2; i * i <= n; i++) {
            if (prime[i]) {
                for (int j = i * i; j <= n; j += i) {
                    prime[j] = false;
                }
            }
        }

        return prime;
    }

    static void solution(int n) {
        if (n == 1) {
            System.out.println(0);
            return;
        }

        // 소수 구하기
        boolean[] prime = getPrime(n);

        // 배열 변환
        List<Integer> list = new ArrayList<>();

        for (int i = 2; i <= n; i++) {
            if (prime[i]) {
                list.add(i);
            }
        }

        int[] arr = new int[list.size()];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = list.get(i);
        }

        // 투포인터
        int left = 0;
        int right = 0;

        int sum = arr[left];
        int answer = 0;

        while (true) {
            if (sum < n) {
                if (right >= arr.length - 1) {
                    break;
                }

                sum += arr[++right];
            } else if (sum > n) {
                sum -= arr[left++];
            } else {
                answer++;
                sum -= arr[left++];
            }
        }

        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        solution(n);
    }
}
