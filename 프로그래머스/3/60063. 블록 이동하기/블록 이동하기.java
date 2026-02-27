/*
bfs + 이동/회전
1. Queue, boolean 배열 생성
2. bfs
3. 상하좌우 + 가로/세로일 때 회전
*/

import java.util.*;

class Solution {
    public int solution(int[][] board) {
        int n = board.length;
        
        // bfs. [x1, y1, x2, y2, time]
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {0, 0, 0, 1, 0});
        
        boolean[][][][] visited = new boolean[n][n][n][n];
        visited[0][0][0][1] = true;
        visited[0][1][0][0] = true;
        
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x1 = cur[0], y1 = cur[1], x2 = cur[2], y2 = cur[3], time = cur[4];
            
            // 도착 체크
            if ((x1 == n - 1 && y1 == n - 1) || (x2 == n - 1 && y2 == n - 1)) return time;
            
            // 1. 상하좌우
            for (int i = 0; i < 4; i++) {
                int nx1 = x1 + dx[i], ny1 = y1 + dy[i];
                int nx2 = x2 + dx[i], ny2 = y2 + dy[i];
                
                if (inRange(nx1, ny1, n) && inRange(nx2, ny2, n) &&
                   board[nx1][ny1] == 0 && board[nx2][ny2] == 0 && !visited[nx1][ny1][nx2][ny2]) {
                    add(nx1, ny1, nx2, ny2, time + 1, visited, queue);
                }
            }
            
            // 2. 회전
            if (x1 == x2) { // 2.1. 가로: 세로 회전 (위, 아래)
                for (int d : new int[] {-1, 1}) {
                    if (inRange(x1 + d, y1, n) && inRange(x2 + d, y2, n) &&
                       board[x1 + d][y1] == 0 && board[x2 + d][y2] == 0) {
                        // x1 고정
                        if (!visited[x1][y1][x1 + d][y1]) add(x1, y1, x1 + d, y1, time + 1, visited, queue);
                        
                        // x2 고정
                        if (!visited[x2][y2][x2 + d][y2]) add(x2, y2, x2 + d, y2, time + 1, visited, queue);
                    }
                }
            } else { // 2.2. 세로: 가로 회전 (왼, 오른)
                for (int d : new int[] {-1, 1}) {
                    if (inRange(x1, y1 + d, n) && inRange(x2, y2 + d, n) &&
                       board[x1][y1 + d] == 0 && board[x2][y2 + d] == 0) {
                        // y1 고정
                        if (!visited[x1][y1][x1][y1 + d]) add(x1, y1, x1, y1 + d, time + 1, visited, queue);
                        
                        // y2 고정
                        if (!visited[x2][y2][x2][y2 + d]) add(x2, y2, x2, y2 + d, time + 1, visited, queue);
                    }
                }
            }
        }
        
        return 0;
    }
    
    void add(int x1, int y1, int x2, int y2, int time, boolean[][][][] visited, Queue<int[]> queue) {
        visited[x1][y1][x2][y2] = true;
        visited[x2][y2][x1][y1] = true;
        queue.add(new int[] {x1, y1, x2, y2, time});
    }
    
    boolean inRange(int x, int y, int n) {
        return x >= 0 && y >= 0 && x < n && y < n;
    }
}