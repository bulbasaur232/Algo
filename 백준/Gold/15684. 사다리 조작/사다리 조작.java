import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 1. 사다리 놓는 함수 하나
 * 2. 가능한지 판단하는 시뮬레이션 함수 하나
 * 단!! 사다리 개수는 최대 3까지만 보면 됨
 * */

public class Main {
    static int N, M, H;
    static boolean[][] ladder;
    static int ans;
    static int limit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        ladder = new boolean[H][N];
        ans = 100;
        limit = 0;

        // 사다리 입력 받기
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            ladder[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = true;
        }

        // 사다리 놓아보기 0, 1, 2, 3개 까지
        for (int i = 0; i <= 3; i++) {
            limit = i;
            set(0);
            if (ans <= 3) break;
        }

        if (ans <= 3)
            System.out.println(ans);
        else
            System.out.println(-1);
        br.close();
    }

    static void set(int depth) {
        if (depth == limit) {
            if (simulation()) ans = limit;
            return;
        }

        for (int height = 0; height < H; height++) {
            for (int lane = 0; lane < N - 1; lane++) {
                if (!ladder[height][lane]) {
                    ladder[height][lane] = true;
                    set(depth + 1);
                    ladder[height][lane] = false;
                }
            }
        }
    }

    // 놓은 사다리 내려가보기 i -> i
    static boolean simulation() {
        for (int i = 0; i < N; i++) {
            int nowHeight = 0;
            int nowLane = i;
            while (nowHeight <= H) {
                if (nowHeight == H) {
                    if (nowLane != i)
                        return false;
                    else break;
                }

                if (nowLane - 1 >= 0 && ladder[nowHeight][nowLane - 1]) {
                    nowLane -= 1;
                } else if (nowLane + 1 < N && ladder[nowHeight][nowLane]) {
                    nowLane += 1;
                }
                nowHeight++;
            }
        }

        return true;
    }
}