/*
2배 확장 + bfs

1. 2배 확장한 map 생성
2. bfs
*/

import java.util.*;

class Solution {
    // 나중에 boolean으로 변경
    boolean[][] map = new boolean[101][101];
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        // 2배 확장
        for (int[] r : rectangle) fill(r[0] * 2, r[1] * 2, r[2] * 2, r[3] * 2);
        for (int[] r : rectangle) erase(r[0] * 2, r[1] * 2, r[2] * 2, r[3] * 2);
        
        characterX *= 2;
        characterY *= 2;
        itemX *= 2;
        itemY *= 2;
        
        // bfs
        boolean[][] visited = new boolean[101][101];

        int[] directX = {1, -1, 0, 0};
        int[] directY = {0, 0, 1, -1};
        
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {characterX, characterY, 0});
        visited[characterX][characterY] = true;
        
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int curX = cur[0];
            int curY = cur[1];
            int curDist = cur[2];
            
            if (curX == itemX && curY == itemY) return curDist / 2;
            
            for (int i = 0; i < 4; i++) {
                int nextX = curX + directX[i];
                int nextY = curY + directY[i];
                
                if (nextX < 0 || nextY < 0 || nextX > 100 || nextY > 100) continue;
                
                if (!visited[nextX][nextY] && map[nextX][nextY]) {
                    queue.add(new int[] {nextX, nextY, curDist + 1});
                    visited[nextX][nextY] = true;
                }
            }
        }
        
        return 0;
    }
    
    void fill(int x1, int y1, int x2, int y2) {
        for (int x = x1; x <= x2; x++) {
            for (int y = y1; y <= y2; y++) map[x][y] = true;
        }
    }
    
    void erase(int x1, int y1, int x2, int y2) {
        for (int x = x1 + 1; x <= x2 - 1; x++) {
            for (int y = y1 + 1; y <= y2 - 1; y++) map[x][y] = false;
        }
    }
}