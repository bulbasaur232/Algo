import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[][] nt;
    static int[] std;
    static int minState;
    static int[] dp;
    static int ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nt = new int[N][5];
        std = new int[4];
        dp = new int[1 << (N + 1)];
        ans = Integer.MAX_VALUE;
        Arrays.fill(dp, -1);
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            std[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                nt[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0, 0, 0, 0, 0);

        if (ans == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(ans);
            for (int i = 0; i < N; i++) {
                if ((minState & (1 << i)) > 0) {
                    System.out.print((i + 1) + " ");
                }
            }
        }
        br.close();
    }

    static int dfs(int state, int price, int n1, int n2, int n3, int n4) {
        if (dp[state] != -1) return dp[state];

        if (n1 >= std[0] && n2 >= std[1] && n3 >= std[2] && n4 >= std[3]) {
            return price;
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            if ((state & (1 << i)) == 0) {
                min = Math.min(min, dfs(state | (1 << i), price + nt[i][4], n1 + nt[i][0], n2 + nt[i][1], n3 + nt[i][2], n4 + nt[i][3]));
                dp[state] = min;

                if (min < ans) {
                    ans = min;
                    minState = state | (1 << i);
                }
            }
        }

        return min;
    }
}