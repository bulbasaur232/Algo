import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static int N; // 손님이 구매하려는 피자 크기
    static int aSize, bSize; // 각 피자의 조각 수
    static Queue<Integer> A, B;
    static int[] cntA, cntB; // 각 피자별 만들 수 있는 조각 합 카운팅 배열
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        aSize = Integer.parseInt(st.nextToken());
        bSize = Integer.parseInt(st.nextToken());
        ans = 0;
        A = new ArrayDeque<>();
        B = new ArrayDeque<>();
        cntA = new int[1000 * aSize + 1];
        cntB = new int[1000 * bSize + 1];

        for (int i = 0; i < aSize; i++) {
            A.offer(Integer.parseInt(br.readLine()));
        }
        for (int i = 0; i < bSize; i++) {
            B.offer(Integer.parseInt(br.readLine()));
        }

        cntA[A.stream().mapToInt(Integer::intValue).sum()]++;
        cntB[B.stream().mapToInt(Integer::intValue).sum()]++;

        // A 피자 조각들 합 경우의 수 구하기
        for (int i = 0; i < aSize; i++) {
            int sum = 0;
            for (int j = 0; j < aSize - 1; j++) {
                int out = A.poll();
                sum += out;
                cntA[sum]++;
                A.offer(out);
            }
        }

        // B 피자 조각들 합 경우의 수 구하기
        for (int i = 0; i < bSize; i++) {
            int sum = 0;
            for (int j = 0; j < bSize - 1; j++) {
                int out = B.poll();
                sum += out;
                cntB[sum]++;
                B.offer(out);
            }
        }

        for (int i = 0; i <= N; i++) {
            if (cntA[i] != 0 && cntB[N - i] != 0) {
                ans += cntA[i] * cntB[N - i];
            }
        }
        ans += cntA[N];
        ans += cntB[N];

        System.out.println(ans);
        br.close();
    }
}