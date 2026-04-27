import java.util.*;

class Solution {
    int answer = Integer.MAX_VALUE;
    
    Queue<Integer> pq = new PriorityQueue<>((a, b) -> {
        return a - b;
    });
    
    void getAnswer(int[] count, int[][] reqs) {
        int[] wait = new int[count.length];
        
        for (int i = 0; i < count.length; i++) {
            for (int[] req : reqs) {
                int start = req[0], time = req[1], part = req[2];
                
                if (part != i + 1) continue;
                
                if (pq.size() < count[i]) {
                    pq.add(start + time);
                } else {
                    int end = pq.poll();
                    
                    if (start < end) {
                        wait[i] += end - start;
                        pq.add(end + time);
                    } else {
                        pq.add(start + time);
                    }
                }
            }
            
            pq.clear();
        }
        
        int sum = 0;
        
        for (int w : wait) {
            sum += w;
            if (sum >= answer) return;
        }
        
        answer = Math.min(answer, sum);
    }
    
    void combination(int start, int depth, int k, int n, int[] count, int[][] reqs) {
        if (depth == n) {
            getAnswer(count, reqs);
            return;
        }
        
        for (int i = start; i < k; i++) {
            count[i]++;
            combination(i, depth + 1, k, n, count, reqs);
            count[i]--;
        }
    }
    
    public int solution(int k, int n, int[][] reqs) {
        // 1명씩 배치
        int[] count = new int[k];
        Arrays.fill(count, 1);
        n -= k;
        
        // 조합
        combination(0, 0, k, n, count, reqs);
        
        return answer;
    }
}