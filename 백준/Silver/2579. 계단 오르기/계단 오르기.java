import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] stair = new int[n + 2];
        for (int i = 0; i < n; i++) {
            stair[i] = Integer.parseInt(br.readLine());
        }

        int[][] dp = new int[n + 2][2];
        dp[0][0] = stair[0];
        dp[1][0] = stair[1];
        for (int i = 0; i < n; i++) {
            dp[i + 1][1] = Math.max(dp[i + 1][1], dp[i][0] + stair[i + 1]);
            dp[i + 2][0] = Math.max(dp[i + 2][0], dp[i][0] + stair[i + 2]);
            dp[i + 2][0] = Math.max(dp[i + 2][0], dp[i][1] + stair[i + 2]);
        }

        System.out.println(Math.max(dp[n - 1][0], dp[n - 1][1]));
        br.close();
    }
}
