import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static int[] cnt;
    static List<Lecture> lectures;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        sb = new StringBuilder();
        cnt = new int[N + 1];
        Arrays.fill(cnt, 1);
        lectures = new LinkedList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            lectures.add(new Lecture(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        lectures.sort(Comparator.comparing(Lecture::getBefore));

        for (Lecture l : lectures) {
            cnt[l.after] = Math.max(cnt[l.after], cnt[l.before] + 1);
        }

        for (int i = 1; i <= N; i++) {
            sb.append(cnt[i]).append(" ");
        }

        System.out.println(sb);
        br.close();
    }

    static class Lecture {
        int before;
        int after;

        public Lecture(int before, int after) {
            this.before = before;
            this.after = after;
        }

        public int getBefore() {
            return this.before;
        }
    }
}