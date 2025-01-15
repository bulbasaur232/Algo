import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, K;
    static List<Jewel> jewels;
    static List<Integer> capacities;
    static PriorityQueue<Integer> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        jewels = new ArrayList<>();
        capacities = new ArrayList<>();
        pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            jewels.add(new Jewel(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        for (int i = 0; i < K; i++) {
            capacities.add(Integer.parseInt(br.readLine()));
        }

        jewels.sort(Comparator.naturalOrder());
        capacities.sort(Comparator.reverseOrder());

        int fillIdx = 0;
        for (int i = 0; i < jewels.size(); i++) {
            Jewel jewel = jewels.get(i);
            if (fillIdx < K && jewel.weight <= capacities.get(fillIdx)) {
                pq.offer(jewels.get(i).price);
                fillIdx++;
            } else if (!pq.isEmpty()) {
                if (pq.peek() < jewel.price) {
                    pq.poll();
                    pq.offer(jewel.price);
                }
            }
        }
        long sum = 0;
        while (!pq.isEmpty()) {
            sum += pq.poll();
        }
        System.out.println(sum);
        br.close();
    }

    static class Jewel implements Comparable<Jewel> {
        int weight;
        int price;

        public Jewel(int weight, int price) {
            this.weight = weight;
            this.price = price;
        }

        public int compareTo(Jewel j) {
            return j.weight - this.weight;
        }
    }
}