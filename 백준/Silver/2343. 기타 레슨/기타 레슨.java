import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N; // 강의의 수
    static int M; // 블루레이 수
    static ArrayList<Integer> len;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        len = new ArrayList<>(N);
        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            len.add(Integer.parseInt(st.nextToken()));
        }

        int low = 1;
        int high = 10000 * N;
        while (low <= high) {
            int mid = (low + high) / 2;
            if(check(mid))
                high = mid - 1;
            else
                low = mid + 1;
        }
        System.out.println(low);

        br.close();
    }

    static boolean check(int capacity) {
        int sum = 0;
        int used = 1;
        for (int i = 0; i < len.size(); i++) {
            int now = len.get(i);
            if (now > capacity) return false;
            if (now + sum <= capacity) {
                sum += now;
            } else if (used < M) {
                used++;
                sum = now;
            } else {
                return false;
            }
        }
        return true;
    }

}