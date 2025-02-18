import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 1. 궁수 배치
 * 2. 시뮬레이션 (턴수는 최대 세로 길이 만큼만)
 * */

public class Main {
    static int N, M, D;
    static int[][] map;
    static boolean[] bow;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        bow = new boolean[M];
        ans = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        deploy(0, -1);

        System.out.println(ans);
        br.close();
    }

    public static int simulation() {
        int kill = 0;
        int h = N;
        List<Point> deathNote = new ArrayList<>();
        int[][] copyMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                copyMap[i][j] = map[i][j];
            }
        }

        // 최대 N턴 만큼 진행하기
        for (int i = 0; i < N; i++) {
            for (int bowIdx = 0; bowIdx < M; bowIdx++) {
                if (!bow[bowIdx]) continue;

                Point near = null;
                int prevDistance = 0;
                // 각 턴당 D까지의 거리만 보면 됨
                for (int j = 1; j <= D; j++) {
                    int lane = h - j;
                    if (lane < 0) break;
                    for (int enemy = 0; enemy < M; enemy++) {
                        if (copyMap[lane][enemy] == 0) continue;

                        int distance = Math.abs(bowIdx - enemy) + j;
                        if (distance <= D) {
                            if (near == null || distance < prevDistance || (distance == prevDistance && enemy < near.x)) {
                                prevDistance = distance;
                                near = new Point(lane, enemy);
                            }
                        }
                    }
                }

                if (near != null) deathNote.add(near);
            }
            // 화살 발사
            for (Point p : deathNote) {
                if (copyMap[p.y][p.x] == 1) {
                    copyMap[p.y][p.x] = 0;
                    kill++;
                }
            }

            // 궁수 한 줄 올리기
            h--;
            deathNote.clear();
        }

        return kill;
    }

    static class Point {
        int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public static void deploy(int depth, int prevIdx) {
        if (depth == 3) {
            // 실행
            ans = Math.max(ans, simulation());
            return;
        }

        for (int i = prevIdx + 1; i < M; i++) {
            bow[i] = true;
            deploy(depth + 1, i);
            bow[i] = false;
            deploy(depth + 1, prevIdx);
        }
    }

}