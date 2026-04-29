import java.util.*;

class Solution {
    int[] dr = {1, -1, 0, 0};
    int[] dc = {0, 0, 1, -1};
    
    int n;
    int[][] board;
    int answer = Integer.MAX_VALUE;
    
    void flip(int[][] arr, int r, int c, int need) {
        // 자기 자신
        arr[r][c] = (arr[r][c] + need) % 4;
        
        // 인접 
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            
            if (nr < 0 || nr  >= n || nc < 0 || nc >= n) {
                continue;
            }
            
            arr[nr][nc] = (arr[nr][nc] + need) % 4;
        }
    }
    
    void dfs(int col, int count) {
        if (count >= answer) {
            return;
        }
        
        if (col == n) {
            // temp 복사
            int[][] temp = new int[n][n];
            
            for (int i = 0; i < n; i++) {
                temp[i] = board[i].clone();
            }
            
            int total = count;
            
            // 1행부터 r-1 행 탐색 후 뒤집기
            for (int r = 1; r < n; r++) {
                for (int c = 0; c < n; c++) {
                    if (temp[r - 1][c] != 0) {
                        int need = (4 - temp[r - 1][c]);
                        
                        total += need;
                        
                        flip(temp, r, c, need);
                    }
                }
            }
            
            // 마지막 행이 모두 0이라면 성공
            for (int c = 0; c < n; c++) {
                if (temp[n - 1][c] != 0) return;
            }
            
            answer = Math.min(answer, total);
            
            return;
        }
        
        // 뒤집기 - dfs - 복구
        for (int cnt = 0; cnt < 4; cnt++) {
            flip(board, 0, col, cnt);
            
            dfs(col + 1, count + cnt);
            
            flip(board, 0, col, (4 - cnt) % 4);
        }
    }
    
    public int solution(int[][] clockHands) {
        n = clockHands.length;
        
        // board 복사
        board = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = clockHands[i][j];
            }
        }
        
        // 1행 0열부터 시작
        dfs(0, 0);

        return answer;
    }
}