class Solution {
    int[] dr = {1, -1, 0, 0};
    int[] dc = {0, 0, 1, -1};
    
    int n;
    int m;
    
    int[] dfs(int[][] board, int cr, int cc, int or, int oc) {
        // System.out.println("ENTER: cur=(" + cr + "," + cc + "), op=(" + or + "," + oc + ")");
        
        if (board[cr][cc] == 0) {
            // System.out.println("LOSE immediately at (" + cr + "," + cc + ")");
            return new int[] {0, 0};
        }
        
        board[cr][cc] = 0;
        
        int minWin = Integer.MAX_VALUE;
        int maxLose = 0;
        
        boolean canWin = false;
        
        for (int i = 0; i < 4; i++) {
            int nr = cr + dr[i];
            int nc = cc + dc[i];
            
            if (nr < 0 || nr >= n || nc < 0 || nc >= m || board[nr][nc] == 0) {
                continue;
            }
            
            int[] res = dfs(board, or, oc, nr, nc);
            
            if (res[0] == 0) { // 상대 패배
                canWin = true;
                minWin = Math.min(minWin, res[1] + 1);
            } else { // 상대 승리
                maxLose = Math.max(maxLose, res[1] + 1);
            }
        }
        
        board[cr][cc] = 1;
        
        return canWin ? new int[] {1, minWin} : new int[] {0, maxLose};
    }
    
    public int solution(int[][] board, int[] aloc, int[] bloc) {
        n = board.length;
        m = board[0].length;
        
        int[] answer = dfs(board, aloc[0], aloc[1], bloc[0], bloc[1]);
        return answer[1];
    }
}