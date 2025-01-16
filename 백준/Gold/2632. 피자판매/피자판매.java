import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N; // 손님이 구매하려는 피자 크기
    static int aSize, bSize; // 각 피자의 조각 수
    static Queue<Integer> A, B;
    static HashMap<Integer, Integer> cntA, cntB; // 각 피자별 만들 수 있는 조각 합 카운팅 배열
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
        cntA = new HashMap<>();
        cntB = new HashMap<>();

        for (int i = 0; i < aSize; i++) {
            A.offer(Integer.parseInt(br.readLine()));
        }
        for (int i = 0; i < bSize; i++) {
            B.offer(Integer.parseInt(br.readLine()));
        }

        cntA.put(A.stream().mapToInt(Integer::intValue).sum(), 1);
        cntB.put(B.stream().mapToInt(Integer::intValue).sum(), 1);

        // A 피자 조각들 합 경우의 수 구하기
        for (int i = 0; i < aSize; i++) {
            int sum = 0;
            for (int j = 0; j < aSize - 1; j++) {
                int out = A.poll();
                sum += out;
                if (cntA.get(sum) != null) {
                    cntA.replace(sum, cntA.get(sum) + 1);
                } else {
                    cntA.put(sum, 1);
                }
                A.offer(out);
            }
        }

        // B 피자 조각들 합 경우의 수 구하기
        for (int i = 0; i < bSize; i++) {
            int sum = 0;
            for (int j = 0; j < bSize - 1; j++) {
                int out = B.poll();
                sum += out;
                if (cntB.get(sum) != null) {
                    cntB.replace(sum, cntB.get(sum) + 1);
                } else {
                    cntB.put(sum, 1);
                }
                B.offer(out);
            }
        }

        for (int i = 0; i <= N; i++) {
            if (cntA.getOrDefault(i, 0) != 0 && cntB.getOrDefault(N - i, 0) != 0) {
                ans += cntA.get(i) * cntB.get(N - i);
            }
        }
        ans += cntA.getOrDefault(N, 0);
        ans += cntB.getOrDefault(N, 0);

        System.out.println(ans);
        br.close();
    }
}