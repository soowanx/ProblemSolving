class Solution {
    int n;
    int[][] clockHands;
    int answer = Integer.MAX_VALUE;
    
    int[] dr = {0, 1, -1, 0, 0};
    int[] dc = {0, 0, 0, 1, -1};
    
    void flip(int[][] arr, int r, int c, int times) {
        for (int i = 0; i < 5; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            
            if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
            
            arr[nr][nc] = (arr[nr][nc] + times) % 4;
        }
    }
    
    void dfs(int col, int count) {
        if (count >= answer) return;
        
        if (col == n) {
            int[][] temp = new int[n][n];
            for (int i = 0; i < n; i++) temp[i] = clockHands[i].clone();
            
            int total = count;
            
            // 1행
            for (int r = 1; r < n; r++) {
                for (int c = 0; c < n; c++) {
                    if (temp[r - 1][c] != 0) {
                        int need = 4 - temp[r - 1][c];
                        total += need;
                        flip(temp, r, c, need);
                    }
                }
            }
            
            // 마지막 행
            for (int c = 0; c < n; c++) {
                if (temp[n - 1][c] != 0) return;
            }
            
            answer = Math.min(answer, total);
            
            return;
        }
        
        for (int cnt = 0; cnt < 4; cnt++) {
            flip(clockHands, 0, col, cnt);
            dfs(col + 1, count + cnt);
            flip(clockHands, 0, col, (4 - cnt) % 4);
        }
    }
    
    public int solution(int[][] clockHands) {
        // dfs
//         n = clockHands.length;
//         this.clockHands = clockHands;
        
//         dfs(0, 0);
        
        // bit mask
        n = clockHands.length;
        this.clockHands = clockHands;
        
        // 4^n = 2^(2n)
        int max = 1 << (2 * n);
        
        for (int mask = 0; mask < max; mask++) {
            int[][] arr = new int[n][n];
            int total = 0;
            
            // 1행
            for (int c = 0; c < n; c++) {
                int k = (mask >> (2 * c)) & 3;
                if (k == 0) continue;
                total += k;
                spin(0, c, k, arr);
            }
            
            // greedy
            for (int r = 1; r < n; r++) {
                for (int c = 0; c < n; c++) {
                    int val = (clockHands[r - 1][c] + arr[r - 1][c]) & 3;
                    
                    if (val != 0) {
                        int k = (4 - val) & 3;
                        total += k;
                        spin(r, c, k, arr);
                    }
                }
            }
            
            // 마지막 행
            boolean ok = true;
            for (int c = 0; c < n; c++) {
                if (((clockHands[n - 1][c] + arr[n - 1][c]) & 3) != 0) {
                    ok = false;
                    break;
                }
            }
            if (ok) answer = Math.min(answer, total);
        }
        
        return answer;
    }
    
    void spin(int r, int c, int s, int[][] arr) {
        for (int i = 0; i < 5; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            
            if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
            
            arr[nr][nc] = (arr[nr][nc] + s) & 3;
        }
    }
}