import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
1. x1 좌표가 작은것 기준으로 정렬
2. 정렬된 통나무를 2개씩 보면서 이동할 수 있으면 union
 */

public class Main {
    static int N, Q;
    static PriorityQueue<Log> pq;
    static int[] parent, rank;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        pq = new PriorityQueue<>(Comparator.comparing(Log::getStart).thenComparing(Log::getEnd));
        parent = new int[N + 1];
        rank = new int[N + 1];
        init();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            pq.add(new Log(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), i + 1));
        }

        Log now = pq.poll();
        while (!pq.isEmpty()) {
            Log next = pq.poll();
            if (now.getEnd() >= next.getStart()) {
                union(now.idx, next.idx);
            }

            if(now.getEnd() < next.getEnd()){
                now = next;
            }
        }

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            int p1 = find(from);
            int p2 = find(to);

            if (p1 == p2)
                System.out.println(1);
            else
                System.out.println(0);
        }

        br.close();
    }

    static void init() {
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }
    }

    static int find(int node) {
        if (parent[node] == node) {
            return node;
        }

        return parent[node] = find(parent[node]);
    }

    static void union(int n1, int n2) {
        int p1 = find(n1);
        int p2 = find(n2);

        if (rank[p1] > rank[p2]) {
            parent[n2] = p1;
        } else {
            parent[n1] = p2;
        }

        if (rank[p1] == rank[p2]) {
            rank[p2]++;
        }
    }

    static class Log {
        int start, end;
        int idx;

        public Log(int start, int end, int idx) {
            this.start = start;
            this.end = end;
            this.idx = idx;
        }

        public int getStart() {
            return this.start;
        }

        public int getEnd() {
            return this.end;
        }
    }
}