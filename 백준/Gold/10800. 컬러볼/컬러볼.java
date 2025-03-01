import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static List<Ball> balls;
    static int[][] accum;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        balls = new LinkedList<>();
        accum = new int[N + 1][2];
        sb = new StringBuilder();

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            balls.add(new Ball(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), i, 0));
        }

        balls.sort(Comparator.comparing(Ball::getSize).thenComparing(Ball::getColor));

        Ball prev = new Ball(-1, -1, -1, -1);
        int sum = 0;
        int prevSum = 0;
        for (Ball b : balls) {

            if (prev.size != b.size) {
                b.ans = sum - accum[b.color][0];
                prevSum = sum;
                accum[b.color][1] = accum[b.color][0];
                
            } else {
                if (prev.color != b.color) {
                    accum[b.color][1] = accum[b.color][0];
                }
                b.ans = prevSum - accum[b.color][1];
            }

            prev = b;
            accum[b.color][0] += b.size;
            sum += b.size;
        }
        balls.sort(Comparator.comparing(Ball::getOriginIdx));
        for (Ball b : balls) {
            sb.append(b.ans).append("\n");
        }
        System.out.println(sb);

        br.close();
    }

    static class Ball {
        int color, size, originIdx, ans;

        public Ball(int color, int size, int originIdx, int ans) {
            this.color = color;
            this.size = size;
            this.originIdx = originIdx;
            this.ans = ans;
        }

        int getColor() {
            return color;
        }

        int getSize() {
            return size;
        }

        int getOriginIdx() {
            return originIdx;
        }
    }
}