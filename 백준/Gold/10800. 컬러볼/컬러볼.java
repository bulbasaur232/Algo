import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static List<Ball> balls;
    static int[] accum;
    static StringBuilder sb;
    static int[] ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        balls = new ArrayList<>();
        accum = new int[N + 1];
        ans = new int[N];
        sb = new StringBuilder();

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            balls.add(new Ball(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), i));
        }

        balls.sort(Comparator.comparing(Ball::getSize));

        int sum = 0;
        int lastIdx = 0;
        for (Ball b : balls) {
            while (b.size > balls.get(lastIdx).size) {
                Ball lastBall = balls.get(lastIdx);
                sum += lastBall.size;
                accum[lastBall.color] += lastBall.size;
                lastIdx++;
            }

            ans[b.originIdx] = sum - accum[b.color];
        }

        for (int a : ans) {
            sb.append(a).append("\n");
        }

        System.out.println(sb);
        br.close();
    }

    static class Ball {
        int color, size, originIdx;

        public Ball(int color, int size, int originIdx) {
            this.color = color;
            this.size = size;
            this.originIdx = originIdx;
        }

        int getSize() {
            return size;
        }

    }
}