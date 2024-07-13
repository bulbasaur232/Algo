
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
1. 그람 안줍고 가기
2. 그람 줍고 벽 부수며 가기\
*/

public class Main {
    static int N, M;
    static int T;
    static int result;
    static int[][] map;
    static boolean[][] visited;
    static int[] my = {-1, 1, 0, 0};
    static int[] mx = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        result = Integer.MAX_VALUE;
        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bfs(); // 벽 안 부술 때
        breakingWall();

        if (result <= T)
            System.out.println(result);
        else
            System.out.println("Fail");

        br.close();
    }

    static void bfs() {
        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(0, 0, 0));
        visited[0][0] = true;

        while (!q.isEmpty()) {
            Point now = q.poll();

            for (int i = 0; i < my.length; i++) {
                int ny = now.y + my[i];
                int nx = now.x + mx[i];

                if (isSafe(ny, nx) && !visited[ny][nx] && map[ny][nx] != 1) {
                    if (ny == N - 1 && nx == M - 1) {
                        result = Math.min(result, now.cnt + 1);
                        return;
                    }

                    q.offer(new Point(ny, nx, now.cnt + 1));
                    visited[ny][nx] = true;
                }
            }
        }
    }

    static void breakingWall() {
        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(0, 0, 0));
        visited = new boolean[N][M];
        visited[0][0] = true;

        while (!q.isEmpty()) {
            Point now = q.poll();

            for (int i = 0; i < my.length; i++) {
                int ny = now.y + my[i];
                int nx = now.x + mx[i];

                if (isSafe(ny, nx) && !visited[ny][nx] && map[ny][nx] != 1) {
                    if (map[ny][nx] == 2) {
                        result = Math.min(result, (now.cnt + 1) + (N - ny - 1) + (M - nx - 1));
                        return;
                    }

                    q.offer(new Point(ny, nx, now.cnt + 1));
                    visited[ny][nx] = true;
                }
            }
        }
    }

    static class Point {
        int y, x, cnt;

        public Point(int y, int x, int cnt) {
            this.y = y;
            this.x = x;
            this.cnt = cnt;
        }
    }

    static boolean isSafe(int y, int x) {
        return y >= 0 && y < N && x >= 0 && x < M;
    }
}

