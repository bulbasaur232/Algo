import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
1. % 60해서 나온값과 그거에 +1한 값을 구한다음에 +10,10 둘 중에 어느걸로 가는게 빠른지 십의자리 대충 맞추기
2. 10 덜누르고  1로 가는게 빠른지 계산

 */
public class Main {
    static int T;
    static int[] Ns;
    static int[][] ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        Ns = new int[T];
        ans = new int[T][5];

        for (int i = 0; i < T; i++) {
            Ns[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 0; i < T; i++) {
            int n = Ns[i];
            ans[i][0] = n / 60;

            int left = n % 60;
            int digit10 = left / 10;
            int digit1 = left % 10;

            if (left <= 35) {
                if (digit1 <= 5) {
                    ans[i][1] = digit10;
                    ans[i][3] = digit1;
                } else {
                    ans[i][1] = digit10 + 1;
                    ans[i][4] = 10 - digit1;
                }
            } else {
                ans[i][0]++;
                if (digit1 < 5) {
                    ans[i][2] = 6 - digit10;
                    ans[i][3] = digit1;
                } else {
                    ans[i][2] = 6 - digit10 - 1;
                    ans[i][4] = 10 - digit1;
                }
            }
        }

        for (int i = 0; i < T; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(ans[i][j] + " ");
            }
            System.out.println();
        }

        br.close();

    }
}