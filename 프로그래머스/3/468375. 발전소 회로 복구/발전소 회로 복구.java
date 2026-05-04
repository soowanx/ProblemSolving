import java.util.*;

class Solution {
    int[][] panels;
    char[][] board;
    int[][] dist;
    int pLen;
    int[] dr = {1, -1, 0, 0};
    int[] dc = {0, 0, 1, -1};
    int n, m;

    // bfs
    int[][] bfs(int sr, int sc) {
        int[][] d = new int[n][m];
        for (int[] row : d) Arrays.fill(row, -1);

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{sr, sc});
        d[sr][sc] = 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i], nc = c + dc[i];

                if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                if (board[nr][nc] == '#' || d[nr][nc] != -1) continue;

                d[nr][nc] = d[r][c] + 1;
                q.add(new int[]{nr, nc});
            }
        }
        
        return d;
    }

    // 엘리베이터 거리
    int[][] buildElevDist() {
        int[][] elev = new int[n][m];
        for (int[] row : elev) Arrays.fill(row, -1);

        int er = -1, ec = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == '@') {
                    er = i; ec = j;
                }
            }
        }

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{er, ec});
        elev[er][ec] = 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                if (board[nr][nc] == '#' || elev[nr][nc] != -1) continue;

                elev[nr][nc] = elev[r][c] + 1;
                q.add(new int[]{nr, nc});
            }
        }
        
        return elev;
    }

    // 패널 간 거리
    void buildDist(int[][] elevDist) {
        dist = new int[pLen + 1][pLen + 1];

        for (int i = 0; i <= pLen; i++) {
            int f1, r1, c1;

            if (i == 0) {
                f1 = panels[0][0];
                r1 = panels[0][1] - 1;
                c1 = panels[0][2] - 1;
            } else {
                f1 = panels[i - 1][0];
                r1 = panels[i - 1][1] - 1;
                c1 = panels[i - 1][2] - 1;
            }

            int[][] d = bfs(r1, c1);

            for (int j = 1; j <= pLen; j++) {
                int f2 = panels[j - 1][0];
                int r2 = panels[j - 1][1] - 1;
                int c2 = panels[j - 1][2] - 1;

                if (f1 == f2) {
                    dist[i][j] = d[r2][c2];
                } else {
                    dist[i][j] = elevDist[r1][c1] + Math.abs(f1 - f2) + elevDist[r2][c2];
                }
            }
        }
    }

    // 선행조건
    int[] buildPre(int[][] seqs) {
        int[] pre = new int[pLen];
        
        for (int[] s : seqs) {
            int a = s[0] - 1;
            int b = s[1] - 1;
            
            pre[b] |= (1 << a);
        }
        
        return pre;
    }

    // dp
    int solveDP(int[] pre) {
        int INF = Integer.MAX_VALUE / 2;
        int[][] dp = new int[1 << pLen][pLen + 1];
        for (int[] row : dp) Arrays.fill(row, INF);

        dp[0][0] = 0;

        for (int mask = 0; mask < (1 << pLen); mask++) {
            for (int last = 0; last <= pLen; last++) {
                if (dp[mask][last] == INF) continue;

                for (int next = 1; next <= pLen; next++) {
                    int bit = 1 << (next - 1);

                    if ((mask & bit) != 0) continue;
                    if ((mask & pre[next - 1]) != pre[next - 1]) continue;

                    int nextMask = mask | bit;

                    dp[nextMask][next] = Math.min(
                        dp[nextMask][next],
                        dp[mask][last] + dist[last][next]
                    );
                }
            }
        }

        int full = (1 << pLen) - 1;
        int ans = INF;
        for (int i = 1; i <= pLen; i++) ans = Math.min(ans, dp[full][i]);
        return ans;
    }

    public int solution(int h, String[] grid, int[][] panels, int[][] seqs) {
        this.panels = panels;
        this.pLen = panels.length;

        n = grid.length;
        m = grid[0].length();
        
        // grid -> board
        board = new char[n][m];
        for (int i = 0; i < n; i++) board[i] = grid[i].toCharArray();

        int[][] elevDist = buildElevDist();
        buildDist(elevDist);
        int[] pre = buildPre(seqs);

        return solveDP(pre);
    }
}