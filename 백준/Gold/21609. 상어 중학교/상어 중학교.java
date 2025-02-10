import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N; // 격자의 크기
    static int M; // 색상의 개수
    static int[][] map;
    static int score;
    static int[] my = {-1, 1, 0, 0};
    static int[] mx = {0, 0, -1, 1};

    static boolean[][] visited;
    static Point bigPoint;
    static int prevBlockCnt;
    static int prevRainbowCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        score = 0;

        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
        }

        autoPlay();

        System.out.println(score);
        br.close();
    }

    static void autoPlay() {

        while (true) {
            // 1. 가장 큰 블록 그룹 찾기
            Point point = findBiggest();

            if (point == null)
                break;

            // 2. 터뜨려서 점수에 반영
            explode(point);

            // 3. 중력
            gravitation();

            // 4. 반시계 회전
            rotate();

            // 5. 중력
            gravitation();
        }
    }

    static Point findBiggest() {
        bigPoint = null;
        prevBlockCnt = 0;
        prevRainbowCnt = 0;
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && map[i][j] > 0 && map[i][j] <= M) {
                    bfs(new Point(i, j));
                }
            }
        }

        return bigPoint;
    }

    static void bfs(Point point) {
        Queue<Point> q = new ArrayDeque<>();
        q.add(point);
        boolean[][] rainbowVisited = new boolean[N][N];

        visited[point.y][point.x] = true;
        int blockCnt = 0;
        int rainbowCnt = 0;
        int color = map[point.y][point.x];

        while (!q.isEmpty()) {
            Point now = q.poll();
            if (map[now.y][now.x] == 0) {
                rainbowCnt++;
            } else {
                blockCnt++;
            }

            for (int i = 0; i < 4; i++) {
                int ny = now.y + my[i];
                int nx = now.x + mx[i];

                // 일반 블록의 색은 모두 같아야 한다. 또는 무지개 블럭이면 감
                if (isSafe(ny, nx) && map[ny][nx] != -1 && map[ny][nx] != Integer.MAX_VALUE) {
                    if (map[ny][nx] == color && !visited[ny][nx]) {
                        visited[ny][nx] = true;
                        q.offer(new Point(ny, nx));
                    } else if (map[ny][nx] == 0 && !rainbowVisited[ny][nx]) {
                        rainbowVisited[ny][nx] = true;
                        q.offer(new Point(ny, nx));
                    }
                }
            }
        }

        if (blockCnt + rainbowCnt < 2)
            return;

        // 큰 블록 비교
        int nowSum = blockCnt + rainbowCnt;
        int prevSum = prevBlockCnt + prevRainbowCnt;
        if (nowSum > prevSum) {
            bigPoint = point;
            prevBlockCnt = blockCnt;
            prevRainbowCnt = rainbowCnt;
        } else if (nowSum == prevSum && rainbowCnt >= prevRainbowCnt) {
            bigPoint = point;
            prevBlockCnt = blockCnt;
            prevRainbowCnt = rainbowCnt;
        }
    }

    static void explode(Point point) {
        Queue<Point> q = new ArrayDeque<>();
        q.offer(point);
        visited = new boolean[N][N];
        int cnt = 0;
        int color = map[point.y][point.x];

        while (!q.isEmpty()) {
            Point p = q.poll();
            cnt++;
            map[p.y][p.x] = Integer.MAX_VALUE;

            for (int i = 0; i < 4; i++) {
                int ny = p.y + my[i];
                int nx = p.x + mx[i];

                if (isSafe(ny, nx) && !visited[ny][nx] && (map[ny][nx] == color || map[ny][nx] == 0)) {
                    visited[ny][nx] = true;
                    q.offer(new Point(ny, nx));
                }
            }
        }

        score += cnt * cnt;
    }

    static void gravitation() {

        for (int i = 0; i < N; i++) {
            Stack<Integer> stack = new Stack<>();

            for (int j = 0; j < N; j++) {
                if (map[j][i] == -1) {
                    int idx = j - 1;
                    int sSize = stack.size();
                    for (int k = 0; k < sSize; k++) {
                        while (!stack.isEmpty() && stack.peek() == Integer.MAX_VALUE) {
                            stack.pop();
                        }

                        if (stack.isEmpty()) break;

                        map[idx--][i] = stack.pop();
                    }
                } else {
                    stack.push(map[j][i]);
                    map[j][i] = Integer.MAX_VALUE;
                }
            }

            int idx = N - 1;
            while (!stack.isEmpty()) {
                while (!stack.isEmpty() && stack.peek() == Integer.MAX_VALUE) {
                    stack.pop();
                }

                if (stack.isEmpty()) break;

                map[idx--][i] = stack.pop();
            }
        }
    }

    static void rotate() {
        int[][] copy = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                copy[N - j - 1][i] = map[i][j];
            }
        }

        map = copy;
    }

    static boolean isSafe(int y, int x) {
        return y >= 0 && y < N && x >= 0 && x < N;
    }

    static class Point {
        int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

}

