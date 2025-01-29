import java.io.*;
import java.util.*;

/*
 * 1. 음수 사이클 있으면 -1 출력하기.
 * 2. 가중치를 출력하되 이어지지 않으면 -1 출력하기.
 * 3. @@@ 음수 사이클이 있으면 언더플로우가 날 가능성이 높은 문제다!! @@@
 */
public class Main {
	static int N, M;
	static Edge[] edge;
	static int start = 1;
	static long[] cost;
	static boolean isPossible;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		edge = new Edge[M];
		cost = new long[N];
		Arrays.fill(cost, Integer.MAX_VALUE);
		cost[0] = 0;
		isPossible = true;

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			edge[i] = new Edge(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1,
					Integer.parseInt(st.nextToken()));
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				Edge e = edge[j];
				if (cost[e.from] == Integer.MAX_VALUE)
					continue;

				if (cost[e.to] > cost[e.from] + e.cost) {
					if (i == N - 1) {
						isPossible = false;
						break;
					}
					cost[e.to] = cost[e.from] + e.cost;
				}
			}
		}

		if (!isPossible) {
			System.out.println(-1);
		} else {
			for (int i = 1; i < N; i++) {
				if (cost[i] == Integer.MAX_VALUE) {
					System.out.println(-1);
				} else {
					System.out.println(cost[i]);
				}
			}
		}

		br.close();
	}

	static class Edge {
		int from, to, cost;

		public Edge(int from, int to, int cost) {
			super();
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
	}
}