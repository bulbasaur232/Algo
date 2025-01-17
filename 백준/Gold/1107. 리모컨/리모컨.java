import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N; // 이동하려고 하는 채널
    static int M; // 고장난 버튼 개수
    static boolean[] isBroken;
    static int ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        isBroken = new boolean[10];
        ans = Integer.MAX_VALUE;
        if (M > 0) {
        StringTokenizer st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                isBroken[Integer.parseInt(st.nextToken())] = true;
            }
        }

        // 1. 처음부터 100번인 경우
        if (N == 100) {
            System.out.println(0);
            return;
        }

        // 2. 모든 버튼이 고장난 경우
        if (M == 10) {
            System.out.println(Math.abs(N - 100));
            return;
        }

        // 3. 숫자 눌러서 이동하는 경우
        if (isPossible(N)) {
            ans = String.valueOf(N).length();
        }

        // 4. +, -로 이동하는 경우
        ans = Math.min(ans, Math.abs(N - 100));

        // 5. 숫자 누르고 +, -로 이동하는 경우
        if (ans > String.valueOf(N).length()) {
            int differ = 1;
            while (true) {
                int target = N - differ;
                if (target >= 0 && isPossible(target)) {
                    ans = Math.min(ans, String.valueOf(target).length() + differ);
                    break;
                }

                target = N + differ;
                if (isPossible(target)) {
                    ans = Math.min(ans, String.valueOf(target).length() + differ);
                    break;
                }

                differ++;
            }
        }

        System.out.println(ans);
        br.close();
    }

    static boolean isPossible(int n) {
        String sNum = String.valueOf(n);
        for (int i = 0; i < sNum.length(); i++) {
            if (isBroken[sNum.charAt(i) - '0'])
                return false;
        }
        return true;
    }
}