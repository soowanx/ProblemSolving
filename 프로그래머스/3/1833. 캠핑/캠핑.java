import java.util.*;

class Solution {
    static int[] bit;
    static int size;

    static void update(int i, int val) {
        for (; i <= size; i += i & -i) bit[i] += val;
    }

    static int query(int i) {
        int s = 0;
        for (; i > 0; i -= i & -i) s += bit[i];
        return s;
    }

    static int query(int l, int r) {
        if (l > r) return 0;
        return query(r) - query(l - 1);
    }

    public int solution(int n, int[][] data) {
        int answer = 0;

        // x 오름차순 -> y 오름차순 정렬
        Arrays.sort(data, (a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);

        // y 좌표 압축
        int[] ys = Arrays.stream(data).mapToInt(d -> d[1]).distinct().sorted().toArray();
        size = ys.length;
        Map<Integer, Integer> yRank = new HashMap<>();
        for (int i = 0; i < size; i++) yRank.put(ys[i], i + 1); // 1-indexed

        for (int i = 0; i < n - 1; i++) {
            bit = new int[size + 1];

            int iy = yRank.get(data[i][1]);

            // i와 같은 x그룹은 BIT에 미리 추가 (i 제외)
            // i보다 큰 x그룹부터 순차적으로 처리
            int j = i + 1;
            // i와 같은 x인 점들 스킵 (다른 x부터 시작)
            while (j < n && data[j][0] == data[i][0]) j++;

            while (j < n) {
                int groupStart = j;
                int curX = data[j][0];

                // 현재 x 그룹 끝 찾기
                while (j < n && data[j][0] == curX) j++;

                // 현재 그룹의 점들과 i 비교
                for (int k = groupStart; k < j; k++) {
                    int ky = yRank.get(data[k][1]);

                    if (data[i][1] == data[k][1]) continue; // y 같으면 스킵

                    int lo = Math.min(iy, ky) + 1;
                    int hi = Math.max(iy, ky) - 1;

                    // (lo, hi) 구간에 BIT에 등록된 y값 없으면 카운트
                    if (lo > hi || query(lo, hi) == 0) answer++;
                }

                // 현재 그룹을 BIT에 추가
                for (int k = groupStart; k < j; k++) {
                    update(yRank.get(data[k][1]), 1);
                }
            }
        }

        return answer;
    }
}