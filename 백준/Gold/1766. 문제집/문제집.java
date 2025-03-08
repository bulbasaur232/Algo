import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 1. 차수가 0인 문제를 큐에 삽입
 * 2. 큐에서 문제를 하나씩 꺼내면서 연결된 문제 차수 줄여줌
 * 3. 차수가 0이 된 문제를 다시 큐에 삽입
 * 4. 2, 3 반복
 * 우선순위큐를 쓰면 쉬운 문제부터 풀게 된다.
 */
public class Main {
    static int N, M;
    static int[] parent;
    static List<Integer>[] child;

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parent = new int[N + 1];
        child = new List[N + 1];

        for (int i = 1; i <= N; i++) {
            child[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            parent[c]++;
            child[p].add(c);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 1; i <= N; i++) {
            if (parent[i] == 0) pq.offer(i);
        }

        while (!pq.isEmpty()) {
            int now = pq.poll();

            for (int n : child[now]) {
                parent[n]--;
                if (parent[n] == 0) pq.offer(n);
            }
            sb.append(now).append(" ");
        }

        System.out.println(sb);
        br.close();
    }
}