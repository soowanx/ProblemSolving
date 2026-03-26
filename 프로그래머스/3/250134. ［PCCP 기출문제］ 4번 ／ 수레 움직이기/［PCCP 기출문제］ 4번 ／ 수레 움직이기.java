/*
dfs

1. 시작, 도착 위치 설정 + 방문 배열 생성
2. dfs
    - 가지치기
    - 도착
    - red, blue 이동
*/

import java.util.*;

class Solution {
    int answer = Integer.MAX_VALUE;
    int n, m;
    int redStartR, redStartC, blueStartR, blueStartC, redEndR, redEndC, blueEndR, blueEndC;
    boolean[][] redVisited, blueVisited;
    int[] dr = {1, -1, 0, 0};
    int[] dc = {0, 0, 1, -1};
    
    
    public int solution(int[][] maze) {
        n = maze.length;
        m = maze[0].length;
        
        redVisited = new boolean[n][m];
        blueVisited = new boolean[n][m];
        
        // 시작, 도착 위치 설정
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (maze[i][j] == 1) {
                    redStartR = i;
                    redStartC = j;
                } else if (maze[i][j] == 2) {
                    blueStartR = i;
                    blueStartC = j;
                } else if (maze[i][j] == 3) {
                    redEndR = i;
                    redEndC = j;
                } else if (maze[i][j] == 4) {
                    blueEndR = i;
                    blueEndC = j;
                }
            }
        }
        
        redVisited[redStartR][redStartC] = true;
        blueVisited[blueStartR][blueStartC] = true;
        
        dfs(maze, redStartR, redStartC, blueStartR, blueStartC, 0);
        
        return answer == Integer.MAX_VALUE ? 0 : answer;
    }
    
    void dfs(int[][] maze, int rr, int rc, int br, int bc, int cnt) {
        if (cnt >= answer) return;
        
        // 도착
        if (rr == redEndR && rc == redEndC && br == blueEndR && bc == blueEndC) {
            answer = cnt;
            return;
        }
        
        int redMoveCount = (rr == redEndR && rc == redEndC) ? 1 : 4;
        int blueMoveCount = (br == blueEndR && bc == blueEndC) ? 1 : 4;
        
        // red 이동
        for (int i = 0; i < redMoveCount; i++) {
            int nrr = rr;
            int nrc = rc;
            
            if (!(rr == redEndR && rc == redEndC)) {
                nrr += dr[i];
                nrc += dc[i];
                
                if (!isValid(maze, nrr, nrc) || redVisited[nrr][nrc]) continue;
            }
            
            
            // blue 이동
            for (int j = 0; j < blueMoveCount; j++) {
                int nbr = br;
                int nbc = bc;
                
                if (!(br == blueEndR && bc == blueEndC)) {
                    nbr += dr[j];
                    nbc += dc[j];
                    
                    if (!isValid(maze, nbr, nbc) || blueVisited[nbr][nbc]) continue;
                }
                
                // 같은 칸, 자리 바꾸기
                if ((nrr == nbr && nrc == nbc) || (nrr == br && nrc == bc && nbr == rr && nbc == rc)) continue;
                
                boolean redMarked = false;
                boolean blueMarked = false;
                
                // 도착하지 않았을 때 (이동했을 때)
                if (!(nrr == rr && nrc == rc)) {
                    redVisited[nrr][nrc] = true;
                    redMarked = true;
                }
                
                if (!(nbr == br && nbc == bc)) {
                    blueVisited[nbr][nbc] = true;
                    blueMarked = true;
                }
                
                dfs(maze, nrr, nrc, nbr, nbc, cnt + 1);
                
                if (redMarked) redVisited[nrr][nrc] = false;
                if (blueMarked) blueVisited[nbr][nbc] = false;
            }
        }
    }
    
    boolean isValid(int[][] maze, int r, int c) {
        return 0 <= r && r < n && 0 <= c && c < m && maze[r][c] != 5;
    }
}